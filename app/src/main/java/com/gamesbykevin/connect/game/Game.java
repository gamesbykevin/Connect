package com.gamesbykevin.connect.game;

import android.view.MotionEvent;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.activity.GameActivity.Screen;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.board.BoardHelper;
import com.gamesbykevin.connect.opengl.OpenGLRenderer;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.game.GameHelper.FRAMES;
import static com.gamesbykevin.connect.game.GameHelper.GAME_OVER;
import static com.gamesbykevin.connect.game.GameHelper.GAME_OVER_DELAY_FRAMES;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.LOADED;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;

/**
 * Created by Kevin on 7/19/2017.
 */
public class Game implements IGame {

    //store activity reference
    private final GameActivity activity;

    //our zoom ration should the user zoom in/out
    public static float ZOOM_SCALE_RENDER_X, ZOOM_SCALE_RENDER_Y;

    //store the zoom for motion events as well
    public static float ZOOM_SCALE_MOTION_X, ZOOM_SCALE_MOTION_Y;

    //do we reset the zoom factor
    public static boolean RESET_ZOOM = true;

    //are we pressing on the screen
    private boolean press = false;

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

    private Board board;

    public Game(GameActivity activity) {

        //store activity reference
        this.activity = activity;

        //default to loading
        STEP = Step.Loading;
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

        if (getBoard() != null)
            getBoard().dispose();

        //create a new board
        this.board = new Board();

        //set the type of shape we will be playing with
        getBoard().setType((Board.Shape)activity.getObjectValue(R.string.game_shape_file_key, Board.Shape.class));
        getBoard().reset();
    }

    public void update() throws Exception {

        switch (STEP) {

            //we are loading
            case Loading:

                //if the textures have finished loading
                if (LOADED) {

                    //if loaded display level select screen
                    //activity.setScreen(Screen.LevelSelect);

                    //go to start step
                    STEP = Step.Reset;
                }
                break;

            //once the ui is updated correctly we can continue
            case Start:

                //if loading is still displayed, don't render anything
                if (activity.getScreen() == Screen.Loading)
                    return;

                //all is good, we can start updating
                STEP = Step.Updating;
                break;

            //we are resetting the board
            case Reset:

                //flag game over false
                GAME_OVER = false;

                //reset the zoom for a new level
                OpenGLRenderer.resetZoom();

                //reset level
                reset();

                //after resetting, next step is updating
                STEP = Step.Start;

                //we can go to ready now
                activity.setScreen(Screen.Ready);
                break;

            case Updating:

                //if the game is over, move to the next step
                if (GAME_OVER) {

                    //reset frames count
                    FRAMES = 0;

                    //move to game over step
                    STEP = Step.GameOver;

                    //hide board background
                    BoardHelper.setVisible(getBoard(), false);

                    //vibrate the phone
                    activity.vibrate();

                } else {
                    getBoard().update(activity);
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

    }

    public boolean onTouchEvent(final int action, float x, float y) {

        //don't continue if we aren't ready yet
        if (STEP != Step.Updating && STEP != Step.Start)
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

    public void render(GL10 openGL) {

        //don't display if we are still loading
        if (STEP != Step.Updating && STEP != Step.GameOver)
            return;

        //render everything on screen
        GameHelper.render(openGL);
    }
}