package com.gamesbykevin.connect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gamesbykevin.connect.R;

public class SplashActivity extends BaseActivity {

    /**
     * The amount of time to display the splash screen (in milliseconds)
     */
    public static final long SPLASH_DELAY = 2500L;

    /**
     * After we initialize how long should we delay
     */
    public static final long DEFAULT_DELAY = 333L;

    /**
     * Is this the first time we came here
     */
    public static volatile boolean INITIALIZE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //call parent
        super.onCreate(savedInstanceState);

        //assign our layout
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onResume() {

        //call parent
        super.onResume();

        //how long do we display the screen
        final long delay = (INITIALIZE) ? DEFAULT_DELAY : SPLASH_DELAY;

        //delay a couple seconds before going to main page
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                //start the new activity
                startActivity(new Intent(SplashActivity.this, (INITIALIZE) ? LevelSelectActivity.class : MainActivity.class));

                //close the activity
                finish();
            }

        }, delay);
    }

    @Override
    public void onBackPressed() {

        //don't allow user to press back button
        return;
    }
}