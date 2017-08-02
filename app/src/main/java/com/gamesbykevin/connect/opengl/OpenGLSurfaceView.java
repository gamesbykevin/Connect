package com.gamesbykevin.connect.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;

import static com.gamesbykevin.androidframeworkv2.util.UtilityHelper.DEBUG;
import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.LOADED;

/**
 * Created by Kevin on 6/1/2017.
 */
public class OpenGLSurfaceView extends GLSurfaceView implements Runnable {

    /**
     * Frames per second
     */
    public static final int FPS = 90;

    /**
     * The version of open GL we are using
     */
    public static final int OPEN_GL_VERSION = 1;

    /**
     * Our object where we render our pixel data
     */
    private OpenGLRenderer openGlRenderer;

    //our game mechanics will run on this thread
    private Thread thread;

    //keep our thread running
    private volatile boolean running = true;

    //track the time to keep a steady game speed
    private long previous;
    private long previousUpdate;
    private long previousDraw;
    private long postUpdate;
    private long postDraw;

    /**
     * The duration of each frame (milliseconds)
     */
    public static final long FRAME_DURATION = (long)(1000.0d / FPS);

    /**
     * How many milliseconds per second
     */
    public static final long MILLISECONDS_PER_SECOND = 1000L;

    /**
     * Count the number of frames for debugging purposes
     */
    private int frames = 0;

    //keep track of time for debug purposes
    private long timestamp = System.currentTimeMillis();

    /**
     * Default dimensions this game was designed for
     */
    public static final int WIDTH = 480;

    /**
     * Default dimensions this game was designed for
     */
    public static final int HEIGHT = 800;

    //store context to access resources
    private final Context activity;

    public OpenGLSurfaceView(Context activity) {

        //call overloaded constructor
        this(activity, null);
    }

    public OpenGLSurfaceView(Context activity, AttributeSet attrs) {

        //call parent constructor
        super(activity, attrs);

        //store our activity reference
        this.activity = activity;

        //create an OpenGL ES 1.0 context.
        setEGLContextClientVersion(OPEN_GL_VERSION);

        //create a new instance of our renderer
        this.openGlRenderer = new OpenGLRenderer(this.activity);

        //set the renderer for drawing on the gl surface view
        setRenderer(getOpenGlRenderer());

        //set render mode to only draw when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    /**
     * Get our OpenGL Renderer
     * @return Object used for all texture mapping
     */
    private OpenGLRenderer getOpenGlRenderer() {
        return this.openGlRenderer;
    }

    /**
     * If the game is paused stop our thread
     */
    @Override
    public void onPause() {

        //call parent function
        super.onPause();

        //pause the open gl renderer
        getOpenGlRenderer().onPause();

        //flag that we don't want our thread to continue running
        this.running = false;

        try {

            //wait for thread to finish
            this.thread.join();

        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    /**
     * Once we resume start a new thread again
     */
    @Override
    public void onResume() {

        //call parent function
        super.onResume();

        //resume the open gl renderer
        getOpenGlRenderer().onResume();

        //flag running true
        this.running = true;

        //create the thread
        this.thread = new Thread(this);

        //start the thread
        this.thread.start();
    }

    @Override
    public void run() {

        //do we continue to loop
        while (running) {

            try {

                //get the current time
                this.previous = System.currentTimeMillis();

                //update the game state
                update();

                //render the image
                draw();

                //control the game speed
                control();

            } catch (Exception e) {
                UtilityHelper.handleException(e);
            }
        }
    }

    /**
     * Make sure we maintain game speed
     * @throws InterruptedException
     */
    private void control() throws InterruptedException {

        //calculate how much time had passed
        final long duration = System.currentTimeMillis() - this.previous;

        //we want each loop to have the same duration to maintain fps
        long remaining = FRAME_DURATION - duration;

        //log event id this loop is running slow
        if (DEBUG && remaining <= 0) {
            UtilityHelper.logEvent("Slow: " + remaining);
            UtilityHelper.logEvent("Update duration: " + (this.postUpdate - this.previousUpdate));
            UtilityHelper.logEvent("Draw   duration: " + (this.postDraw - this.previousDraw));
        }

        //make sure we sleep at least 1 millisecond
        if (remaining < 1)
            remaining = 1;

        //make sure time remaining is a valid number
        remaining = (remaining <= 0) ? 1 : remaining;

        //sleep the thread to maintain a steady game speed
        this.thread.sleep(remaining);

        //if debugging track performance
        if (DEBUG)
            trackProgress();
    }

    /**
     * Track progress for debugging purposes
     */
    private void trackProgress() {

        //keep track of the frames
        frames++;

        //if 1 second has passed, print fps counter
        if (System.currentTimeMillis() - timestamp >= MILLISECONDS_PER_SECOND) {

            //print progress
            UtilityHelper.logEvent("FPS: " + frames);

            //reset timer for next update
            timestamp = System.currentTimeMillis();

            //reset frame count
            frames = 0;
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        try
        {
            //we can't continue if the textures have not yet loaded
            if (!LOADED)
                return true;

            //adjust the coordinates where touch event occurred
            final float x = event.getRawX() * getOpenGlRenderer().scaleMotionX;
            final float y = event.getRawY() * getOpenGlRenderer().scaleMotionY;

            //update game accordingly
            getGame().onTouchEvent(event.getAction(), x, y);
        }
        catch (Exception e)
        {
            UtilityHelper.handleException(e);
        }

        //return true to keep receiving touch events
        return true;
    }

    /**
     * Update the game state
     */
    private void update() throws Exception {

        //track time before update
        this.previousUpdate = System.currentTimeMillis();

        //update game logic here
        getGame().update();

        //track time after update
        this.postUpdate = System.currentTimeMillis();
    }

    /**
     * Render the game image to screen surface
     */
    private void draw() {

        //track time before draw
        this.previousDraw = System.currentTimeMillis();

        try {
            //render game objects
            requestRender();

        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }

        //track time after draw
        this.postDraw = System.currentTimeMillis();
    }
}