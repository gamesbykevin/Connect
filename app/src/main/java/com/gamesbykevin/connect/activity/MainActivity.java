package com.gamesbykevin.connect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //flag debug true
        UtilityHelper.DEBUG = true;

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

        //start game
        Intent intent = new Intent(this, GameActivity.class);
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