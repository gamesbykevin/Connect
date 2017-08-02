package com.gamesbykevin.connect.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.R;

import java.util.HashMap;

/**
 * Created by Kevin on 5/22/2017.
 */
public abstract class BaseActivity extends com.gamesbykevin.androidframeworkv2.activity.BaseActivity {

    //our social media urls etc....
    public static final String URL_YOUTUBE = "https://youtube.com/gamesbykevin";
    public static final String URL_FACEBOOK = "https://facebook.com/gamesbykevin";
    public static final String URL_TWITTER = "https://twitter.com/gamesbykevin";
    public static final String URL_INSTAGRAM = "https://www.instagram.com/gamesbykevin";
    public static final String URL_WEBSITE = "http://gamesbykevin.com";
    public static final String URL_RATE = "https://play.google.com/store/apps/details?id=com.gamesbykevin.connect";

    //collection of music
    private static HashMap<Integer, MediaPlayer> SOUND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //call parent
        super.onCreate(savedInstanceState);

        //if null
        if (SOUND == null) {

            //create new list
            SOUND = new HashMap<>();
            //loadSound(R.raw.ballbounce);
        }
    }

    private void loadSound(final int resId) {
        SOUND.put(resId, MediaPlayer.create(this, resId));
    }

    public void playSong(final int resId) {
        //stopSound(R.raw.theme);
        playSound(resId, false, true);
    }

    public void playSoundEffect(final int resId) {
        playSound(resId, false, false);
    }

    public void playSound(final int resId, boolean restart, boolean loop) {

        try {
            //if there is no sound, we can't play it
            if (SOUND == null || SOUND.isEmpty())
                return;

            //we can't play if the sound is not enabled
            if (!getBooleanValue(R.string.sound_file_key))
                return;

            //if restarting go to beginning of sound
            if (restart)
                SOUND.get(resId).seekTo(0);

            //do we want our sound to loop
            SOUND.get(resId).setLooping(loop);

            //resume playing
            SOUND.get(resId).start();
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    /**
     * Release all resources in BaseActivity
     */
    public void dispose() {
        try {
            //recycle parent
            super.dispose();

            //stop, kill all sound
            destroySound();
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    private void destroySound() {
        if (SOUND != null) {
            for (Integer resId : SOUND.keySet()) {
                stopSound(resId);
                SOUND.get(resId).release();
            }

            SOUND.clear();
            SOUND = null;
        }
    }

    public void stopSound() {
        if (SOUND != null) {
            for (Integer key : SOUND.keySet()) {
                stopSound(key);
            }
        }
    }

    public void stopSound(final int resId) {
        try {
            if (SOUND != null && !SOUND.isEmpty() && SOUND.get(resId) != null) {
                //get the song and stop if playing
                if (SOUND.get(resId).isPlaying() || SOUND.get(resId).isLooping())
                    SOUND.get(resId).pause();
            }
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    /**
     * Open the youtube web page
     * @param view Current view
     */
    public void onClickYoutube(View view) {
        openUrl(URL_YOUTUBE);
    }

    /**
     * Open the facebook web page
     * @param view Current view
     */
    public void onClickFacebook(View view) {
        openUrl(URL_FACEBOOK);
    }

    /**
     * Open the twitter web page
     * @param view Current view
     */
    public void onClickTwitter(View view) {
        openUrl(URL_TWITTER);
    }

    /**
     * Open the twitter web page
     * @param view Current view
     */
    public void onClickInstagram(View view) {
        openUrl(URL_INSTAGRAM);
    }

    public void onClickRate(View view) {
        openUrl(URL_RATE);
    }

    public void onClickMore(View view) {
        openUrl(URL_WEBSITE);
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
    }

    @Override
    protected void onPause() {

        //call parent
        super.onPause();
    }

    @Override
    protected void onResume() {

        //call parent
        super.onResume();
    }
}