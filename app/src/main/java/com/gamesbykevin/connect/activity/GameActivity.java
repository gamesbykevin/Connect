package com.gamesbykevin.connect.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.gamesbykevin.androidframeworkv2.base.Disposable;
import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.activity.LevelSelectActivity.Level;
import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.game.Game;
import com.gamesbykevin.connect.game.Game.Step;
import com.gamesbykevin.connect.opengl.OpenGLSurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.gamesbykevin.connect.activity.LevelSelectActivity.PAGES;
import static com.gamesbykevin.connect.game.Game.STEP;
import static com.gamesbykevin.androidframeworkv2.util.UtilityHelper.DEBUG;
import static com.gamesbykevin.connect.activity.LevelSelectActivity.CURRENT_PAGE;

public class GameActivity extends BaseActivity implements Disposable {

    //our open GL surface view
    private GLSurfaceView glSurfaceView;

    /**
     * Create a random object which the seed as the current time stamp
     */
    private static Random RANDOM;

    //Our game manager class
    private static Game GAME;

    //has the activity been paused
    private boolean paused = false;

    //our layout parameters
    private LinearLayout.LayoutParams layoutParams;

    //a list of layouts on the game screen, separate from open gl layout
    private List<ViewGroup> layouts;

    /**
     * Different steps in the game
     */
    public enum Screen {
        Loading,
        Ready,
        GameOver
    }

    //current screen we are on
    private Screen screen = Screen.Loading;

    //our auto rotate button reference
    private ToggleButton buttonAutoRotate;

    //the images that make up our time
    private ImageView time1, time2, time3, time4;

    //keep track of the time
    private int value1 = 0, value2 = 0, value3 = 0, value4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //call parent
        super.onCreate(savedInstanceState);

        //create our game manager
        GAME = new Game(this);

        //set the content view
        setContentView(R.layout.activity_game);

        //obtain our open gl surface view object for reference
        this.glSurfaceView = (OpenGLSurfaceView)findViewById(R.id.openglView);

        //update button according to boolean value setting
        updateAutoRotateButton();

        //add the layouts to our list
        this.layouts = new ArrayList<>();
        this.layouts.add((LinearLayout)findViewById(R.id.gameOverLayoutDefault));
        this.layouts.add((LinearLayout)findViewById(R.id.loadingScreenLayout));
        this.layouts.add((LinearLayout)findViewById(R.id.layoutGameControls));

        //out time image references
        this.time1 = (ImageView)findViewById(R.id.time1);
        this.time2 = (ImageView)findViewById(R.id.time2);
        this.time3 = (ImageView)findViewById(R.id.time3);
        this.time4 = (ImageView)findViewById(R.id.time4);
    }

    public static Game getGame() {
        return GAME;
    }

    /**
     * Get our random object.<br>
     * If object is null a new instance will be instantiated
     * @return Random object used to generate random events
     */
    public static Random getRandomObject() {

        //create the object if null
        if (RANDOM == null) {

            //get the current timestamp
            final long time = System.nanoTime();

            //create our Random object
            RANDOM = new Random(time);

            if (DEBUG)
                UtilityHelper.logEvent("Random seed: " + time);
        }

        return RANDOM;
    }

    @Override
    protected void onStart() {

        //call parent
        super.onStart();
    }

    @Override
    protected void onDestroy() {

        //call parent
        super.onDestroy();

        //cleanup resources
        if (GAME != null) {
            try {
                GAME.dispose();
            } catch (Exception e) {
                UtilityHelper.handleException(e);
            }
        }

        if (layouts != null) {

            for (ViewGroup view : layouts) {
                if (view != null) {
                    try {
                        view.removeAllViews();
                        view = null;
                    } catch (Exception e) {
                        UtilityHelper.handleException(e);
                    }
                }
            }

            layouts.clear();
            layouts = null;
        }

        glSurfaceView = null;
        layoutParams = null;
        time1 = null;
        time2 = null;
        time3 = null;
        time4 = null;
    }

    @Override
    protected void onPause() {

        //call parent
        super.onPause();

        //pause the game
        getGame().onPause();

        //flag paused true
        this.paused = true;

        //pause the game view
        glSurfaceView.onPause();

        //flag for recycling
        glSurfaceView = null;

        //stop all sound
        stopSound();
    }

    @Override
    protected void onResume() {

        //call parent
        super.onResume();

        //resume the game object
        getGame().onResume();

        //resume sound playing
        //super.playSong(R.raw.theme);

        //if the game was previously paused we need to re-initialize the views
        if (this.paused) {

            //flag paused false
            this.paused = false;

            //create a new OpenGL surface view
            glSurfaceView = new OpenGLSurfaceView(this);

            //resume the game view
            glSurfaceView.onResume();

            //remove layouts from the parent view
            for (int i = 0; i < layouts.size(); i++) {
                ((ViewGroup)layouts.get(i).getParent()).removeView(layouts.get(i));
            }

            //set the content view for our open gl surface view
            setContentView(glSurfaceView);

            //add the layouts to the current content view
            for (int i = 0; i < layouts.size(); i++) {
                super.addContentView(layouts.get(i), getLayoutParams());
            }

        } else {

            //resume the game view
            glSurfaceView.onResume();
        }

        //determine what screen(s) are displayed
        setScreen(getScreen());
    }

    public Screen getScreen() {
        return this.screen;
    }

    public void setScreen(final Screen screen) {

        //default all layouts to hidden
        for (int i = 0; i < layouts.size(); i++) {
            setLayoutVisibility(layouts.get(i), false);
        }

        //only display the correct screens
        switch (screen) {

            //show loading screen
            case Loading:
                setLayoutVisibility((ViewGroup)findViewById(R.id.loadingScreenLayout), true);
                break;

            //decide which game over screen is displayed
            case GameOver:
                setLayoutVisibility((ViewGroup)findViewById(R.id.gameOverLayoutDefault), true);
                break;

            //don't re-enable anything
            case Ready:
                setLayoutVisibility((ViewGroup)findViewById(R.id.layoutGameControls), true);
                break;
        }

        //assign screen to view
        this.screen = screen;
    }

    private LinearLayout.LayoutParams getLayoutParams() {

        if (this.layoutParams == null)
            this.layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);

        return this.layoutParams;
    }

    @Override
    public void onBackPressed() {

        //show loading screen while we reset
        setScreen(Screen.Loading);

        //move step to do nothing
        STEP = Step.Start;

        //call parent
        super.onBackPressed();
    }

    public void onClickAutoRotate(View view) {

        if (this.buttonAutoRotate != null) {

            //assign auto rotate value
            getGame().AUTO_ROTATE = buttonAutoRotate.isChecked();

            //update button according to boolean value setting
            updateAutoRotateButton();
        }
    }

    private void updateAutoRotateButton() {

        //get the button if not existing
        if (buttonAutoRotate == null)
            buttonAutoRotate = (ToggleButton)findViewById(R.id.ButtonAutoRotate);

        if (buttonAutoRotate != null) {
            buttonAutoRotate.setChecked(getGame().AUTO_ROTATE);
            buttonAutoRotate.setBackgroundResource((buttonAutoRotate.isChecked()) ? R.drawable.rotate_on : R.drawable.rotate_off);
        }
    }

    public void onClickMenu(View view) {

        //go back to the main game menu
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onClickNext(View view) {

        //increase the current page
        CURRENT_PAGE++;

        //if the page is at the end, start over
        if (CURRENT_PAGE >= PAGES)
            CURRENT_PAGE = 0;

        Level level = Level.values()[CURRENT_PAGE];

        //update the board size
        Board.BOARD_COLS = level.getCols();
        Board.BOARD_ROWS = level.getRows();

        //show loading screen while we reset
        setScreen(Screen.Loading);

        //reset the game board
        STEP = Step.Reset;
    }

    public void resetTimer() {
        value1 = 0;
        value2 = 0;
        value3 = 0;
        value4 = 0;
    }

    /**
     * Update the game timer
     */
    public void updateTimer() {

        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = true;

        //increase the seconds
        value4++;

        //10 seconds
        if (value4 > 9) {

            //reset
            value4 = 0;

            //increase the tens (seconds)
            value3++;

            //flag change
            flag3 = true;
        }

        //60 seconds
        if (value3 > 5) {

            //reset
            value3 = 0;

            //increase the minutes
            value2++;

            //flag change
            flag2 = true;
        }

        //10 minutes
        if (value2 > 9) {

            //reset
            value2 = 0;

            //increase the tens (minutes)
            value1++;

            //flag change
            flag1 = true;
        }

        //only update when necessary
        if (flag1)
            updateImageViewTimer(value1, time1);
        if (flag2)
            updateImageViewTimer(value2, time2);
        if (flag3)
            updateImageViewTimer(value3, time3);
        if (flag4)
            updateImageViewTimer(value4, time4);
    }

    /**
     * Get the seconds
     * @return total number of seconds elapsed
     */
    public int getSeconds() {
        return (value4 + (value3 * 10) + (value2 * 60) + (value1 * 60 * 10));
    }

    private void updateImageViewTimer(final int value, final ImageView imageView) {

        //resource id
        final int resId;

        //if we are at the max displayed value, everything will be 9
        if (value1 > 9) {

            resId = R.drawable.nine;

        } else {

            //find the appropriate drawable
            switch (value) {

                case 0:
                    resId = R.drawable.zero;
                    break;

                case 1:
                    resId = R.drawable.one;
                    break;

                case 2:
                    resId = R.drawable.two;
                    break;

                case 3:
                    resId = R.drawable.three;
                    break;

                case 4:
                    resId = R.drawable.four;
                    break;

                case 5:
                    resId = R.drawable.five;
                    break;

                case 6:
                    resId = R.drawable.six;
                    break;

                case 7:
                    resId = R.drawable.seven;
                    break;

                case 8:
                    resId = R.drawable.eight;
                    break;

                case 9:
                    resId = R.drawable.nine;
                    break;

                default:
                    throw new RuntimeException("value not defined: " + value);
            }
        }

        //run on ui thread
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //update bitmap accordingly
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
            }
        });
    }
}