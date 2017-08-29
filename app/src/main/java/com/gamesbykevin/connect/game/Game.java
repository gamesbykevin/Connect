package com.gamesbykevin.connect.game;

import android.view.MotionEvent;
import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.activity.GameActivity.Screen;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.opengl.OpenGLRenderer;
import com.gamesbykevin.connect.opengl.OpenGLSurfaceView;

import static com.gamesbykevin.connect.activity.LevelSelectActivity.CURRENT_PAGE;
import static com.gamesbykevin.connect.activity.MainActivity.getBoards;
import static com.gamesbykevin.connect.game.GameHelper.FRAMES;
import static com.gamesbykevin.connect.game.GameHelper.GAME_OVER;
import static com.gamesbykevin.connect.game.GameHelper.GAME_OVER_DELAY_FRAMES;
import static com.gamesbykevin.connect.game.GameHelper.zoomOut;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.LOADED;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.ZOOM_DEFAULT;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.adjustZoom;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.mx;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.my;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.resetZoom;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.OFFSET_X;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.OFFSET_Y;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.LEFT;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.RIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.TOP;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.BOTTOM;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;


/**
 * Created by Kevin on 7/19/2017.
 */
public class Game implements IGame {

    //store activity reference
    private final GameActivity activity;

    //do we reset the zoom factor
    public static boolean RESET_ZOOM = true;

    //store the zoom for motion events as well
    public static float ZOOM_SCALE_MOTION_X, ZOOM_SCALE_MOTION_Y;

    //are we pressing on the screen
    private boolean press = false;

    //did we perform the first render
    private boolean initialRender = false;

    /**
     * The list of steps in the game
     */
    public enum Step {
        Start,
        Reset,
        Loading,
        GameOver,
        Updating
    }

    //what is the current step that we are on
    public static Step STEP = Step.Loading;

    //the board containing all our pieces
    private Board board;

    //do we rotate until we connect to something?
    public static boolean AUTO_ROTATE = false;

    //amount of time elapsed (milli seconds)
    private long elapsed = 0;

    public Game(GameActivity activity) {

        //store activity reference
        this.activity = activity;

        //default to loading
        STEP = Step.Loading;

        //reset zoom
        RESET_ZOOM = true;
    }

    public Board getBoard() {
        return this.board;
    }

    public void onPause() {
        //do we need to pause anything here?
        RESET_ZOOM = false;
    }

    public void onResume() {
        //do we need to resume anything
    }

    public void reset() throws Exception {

        if (getBoard() != null) {
            board.dispose();
            board = null;
        }

        //reset timer
        elapsed = 0;

        //flag game over false
        GAME_OVER = false;

        //create a new board
        this.board = new Board(Board.BOARD_COLS, Board.BOARD_ROWS);

        //set the type of shape we will be playing with
        getBoard().setType((Board.Shape)activity.getObjectValue(R.string.game_shape_file_key, Board.Shape.class));

        //reset the board
        getBoard().reset();
    }

    public void update() throws Exception {

        switch (STEP) {

            //we are loading
            case Loading:

                //if the textures have finished loading
                if (LOADED) {

                    //go to start step
                    STEP = Step.Reset;
                }
                break;

            //do nothing
            case Start:
                break;

            //we are resetting the board
            case Reset:

                //reset level
                reset();

                //reset the game timer
                activity.resetTimer();

                //after resetting, next step is updating
                STEP = Step.Updating;

                //we can go to ready now
                //activity.setScreen(Screen.Ready);
                break;

            case Updating:

                //if the game is over, move to the next step
                if (GAME_OVER) {

                    //save the best time for the current level
                    getBoards().update(getBoard().getType(), CURRENT_PAGE, activity.getSeconds());

                    //zoom out so we can see the whole level
                    zoomOut(getBoard());

                    //reset frames count
                    FRAMES = 0;

                    //move to game over step
                    STEP = Step.GameOver;

                    //vibrate the phone
                    activity.vibrate();

                } else {

                    boolean generated = getBoard().getMaze().isGenerated();

                    //update the board
                    getBoard().update(activity);

                    //if the board wasn't generated previously and is now, set the zoom
                    if (!generated && getBoard().getMaze().isGenerated())
                        zoomOut(getBoard());

                    //keep track of the time
                    elapsed += OpenGLSurfaceView.FRAME_DURATION;

                    //if 1 second passed, update timer
                    if (elapsed >= OpenGLSurfaceView.MILLISECONDS_PER_SECOND) {

                        //reset elapsed
                        elapsed = 0;

                        //update the game timer
                        activity.updateTimer();
                    }

                    //if we already rendered the board then lets display it
                    if (initialRender && activity.getScreen() == Screen.Loading)
                        activity.setScreen(Screen.Ready);
                }
                break;

            case GameOver:

                //keep track of elapsed frames
                FRAMES++;

                //switch to game over screen if enough time passed and we haven't set yet
                if (FRAMES >= GAME_OVER_DELAY_FRAMES && activity.getScreen() != Screen.GameOver)
                    activity.setScreen(Screen.GameOver);
                break;
        }
    }

    /**
     * Recycle objects
     */
    public void dispose() {

        if (board != null) {
            board.dispose();
            board = null;
        }

        GameHelper.dispose();
    }

    public boolean onTouchEvent(final int action, float x, float y) {

        //don't continue if we aren't ready yet
        if (STEP != Step.Updating)
            return true;

        if (action == MotionEvent.ACTION_UP)
        {
            //check the board for rotations
            if (this.press)
                getBoard().touch(x, y);

            //un-flag press
            this.press = false;
        }
        else if (action == MotionEvent.ACTION_DOWN)
        {
            //flag that we pressed down
            this.press = true;
        }
        else if (action == MotionEvent.ACTION_MOVE)
        {
            //flag press
            this.press = true;
        }

        //return true to keep receiving events
        return true;
    }

    public void render(float[] m) {

        //don't display if we aren't ready
        if (STEP != Step.Updating && STEP != Step.GameOver)
            return;

        //render everything on screen
        GameHelper.render(m);

        //we have performed the initial render
        initialRender = true;
    }
}