package com.gamesbykevin.connect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.game.Game;
import com.gamesbykevin.connect.opengl.OpenGLRenderer;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //recycle resources
        super.dispose();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //stop all sound
        super.stopSound();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //super.playSong(R.raw.menu);
    }

    @Override
    public void onBackPressed() {
        //don't do anything, force user to hit android home button or exit button
        return;
    }

    public void onClickStart(View view) {

        //reset the zoom, for every new game started
        Game.RESET_ZOOM = true;

        //store our shape selection
        OptionsActivity.OPTION_BOARD_SHAPE = (Board.Shape)super.getObjectValue(R.string.game_shape_file_key, Board.Shape.class);

        //reset to first page
        LevelSelectActivity.CURRENT_PAGE = 0;

        //start game
        Intent intent = new Intent(this, LevelSelectActivity.class);
        startActivity(intent);
    }

    public void onClickOptions(View view) {

        //start options activity
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void onClickExit(View view) {

        //finish activity
        super.finish();

        //close all activities
        ActivityCompat.finishAffinity(this);
    }
}