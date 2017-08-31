package com.gamesbykevin.connect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.board.Boards;
import com.gamesbykevin.connect.game.Game;
import com.gamesbykevin.connect.services.BaseGameActivity;

public class MainActivity extends BaseGameActivity {

    //keep track of our stats and the levels we have completed
    private static Boards BOARDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //call parent
        super.onCreate(savedInstanceState);

        //assign main layout
        setContentView(R.layout.activity_main);

        if (BOARDS == null)
            BOARDS = new Boards(this);
    }

    @Override
    protected void onStart() {

        //call parent
        super.onStart();
    }

    @Override
    protected void onStop() {

        //call parent
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        //call parent
        super.onDestroy();

        //recycle resources
        super.dispose();

        if (BOARDS != null) {
            BOARDS.dispose();
            BOARDS = null;
        }
    }

    @Override
    protected void onPause() {

        //call parent
        super.onPause();

        //stop all sound
        super.stopSound();
    }

    @Override
    protected void onResume() {

        //call parent
        super.onResume();

        //stop all sound
        super.stopSound();

        //resume audio
        super.playSong(R.raw.menu);
    }

    @Override
    public void onBackPressed() {

        //don't do anything, force user to hit android home button or exit button to leave
        return;
    }

    public void onClickStart(View view) {

        //reset the zoom, for every new game started
        Game.RESET_ZOOM = true;

        //store our shape selection
        OptionsActivity.OPTION_BOARD_SHAPE = (Board.Shape)getObjectValue(R.string.game_shape_file_key, Board.Shape.class);

        //play the main theme
        playTheme();

        //reset to first page
        LevelSelectActivity.CURRENT_PAGE = 0;

        //start game
        startActivity(new Intent(this, SplashActivity.class));
    }

    public void onClickTutorial(View view) {

        //start the tutorial
        startActivity(new Intent(this, TutorialActivity.class));
    }

    public void onClickOptions(View view) {

        //start options activity
        startActivity(new Intent(this, OptionsActivity.class));
    }

    public void onClickExit(View view) {

        //no need to bypass login in the future
        BYPASS_LOGIN = false;

        //flag false
        SplashActivity.INITIALIZE = false;

        //finish activity
        super.finish();

        //close all activities
        ActivityCompat.finishAffinity(this);

        //sign out of google play services
        super.signOut();
    }

    /**
     * Our object to track our best level duration(s)
     * @return object to track our best level duration(s)
     */
    public static Boards getBoards() {
        return BOARDS;
    }
}