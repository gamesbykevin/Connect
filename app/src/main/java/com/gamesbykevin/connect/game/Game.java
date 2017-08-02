package com.gamesbykevin.connect.game;

import android.view.MotionEvent;

import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.activity.GameActivity.Screen;
import com.gamesbykevin.connect.board.Board;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.opengl.OpenGLRenderer.LOADED;

/**
 * Created by Kevin on 7/19/2017.
 */
public class Game implements IGame {

    //store activity reference
    private final GameActivity activity;

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

    public void pause() {

    }

    public void reset() {

        if (getBoard() == null)
            board = new Board();

        getBoard().reset();
    }

    public void update() {

        switch (STEP) {

            //we are loading
            case Loading:

                //if the textures have finished loading
                if (LOADED) {

                    //if loaded display level select screen
                    activity.setScreen(Screen.LevelSelect);

                    //go to start step
                    STEP = Step.Reset;
                }
                break;

            //don't do anything
            case Start:
                break;

            //we are resetting the board
            case Reset:

                //reset level
                reset();

                //after resetting, next step is updating
                STEP = Step.Updating;

                //we can go to ready now
                activity.setScreen(Screen.Ready);
                break;

            case Updating:

                //if the game is over, move to the next step
                if (GameHelper.isGameOver()) {

                    //move to game over step
                    STEP = Step.GameOver;

                    //vibrate the phone
                    activity.vibrate();
                } else {
                    getBoard().update(activity);
                }
                break;

            case GameOver:

                activity.setScreen(Screen.GameOver);
                break;
        }
    }

    /**
     * Recycle objects
     */
    public void dispose() {

    }

    public boolean onTouchEvent(final int action, final float x, final float y) {

        //don't continue if we aren't ready yet
        if (STEP != Step.Updating && STEP != Step.Start)
            return true;

        if (action == MotionEvent.ACTION_UP)
        {
            //check the board for rotations
            if (this.press) {
                getBoard().touch(x, y);
            }

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