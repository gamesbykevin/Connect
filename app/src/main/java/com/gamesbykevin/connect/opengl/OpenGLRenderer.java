package com.gamesbykevin.connect.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.androidframeworkv2.util.UtilityHelper.DEBUG;
import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.game.Game.ZOOM_SCALE_RENDER_X;
import static com.gamesbykevin.connect.game.Game.ZOOM_SCALE_RENDER_Y;
import static com.gamesbykevin.connect.game.Game.ZOOM_SCALE_MOTION_X;
import static com.gamesbykevin.connect.game.Game.ZOOM_SCALE_MOTION_Y;

import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.FRAME_DURATION;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.OFFSET_X;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.OFFSET_Y;

/**
 * Created by Kevin on 6/1/2017.
 */
public class OpenGLRenderer implements Renderer {

    /**
     * How far do we zoom in/out
     */
    private float zoomRatio = 1.0f;

    /**
     * How much can we adjust the zoom at one time
     */
    public static float ZOOM_RATIO_ADJUST = 0.05f;

    /**
     * The maximum amount we can zoom out
     */
    private static float ZOOM_RATIO_MAX = 2.0f;

    /**
     * The minimum amount we can zoom in
     */
    private static float ZOOM_RATIO_MIN = 0.5f;

    //get the ratio of the users screen compared to the default dimensions for the render
    private static float originalScaleRenderX, originalScaleRenderY;

    //get the ratio of the users screen compared to the default dimensions for the motion event
    private static float originalScaleMotionX = 0, originalScaleMotionY = 0;

    //the actual dimensions of the users phone
    private int screenWidth, screenHeight;

    /**
     * Have all textures been loaded?
     */
    public static boolean LOADED = false;

    //object containing all the texture ids
    private Textures textures;

    public OpenGLRenderer(Context activity) {

        //create object for reference to textures
        this.textures = new Textures(activity);

        //flag the textures loaded as false
        LOADED = false;
    }

    public void onPause() {
        //do we do anything here?
    }

    public void onResume() {
        //re-load the textures if needed?
    }

    /**
     * Called once to set up the view's OpenGL ES environment
     * @param gl Open gl object for rendering
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //display the version of open gl
        //UtilityHelper.logEvent("OpenGL Version: " + gl.glGetString(GL10.GL_VERSION));
    }

    /**
     * Restore the scale values as when the surface was first created
     */
    public static void resetZoom() {

        //store the zoom variables as the same
        ZOOM_SCALE_MOTION_X = originalScaleMotionX;
        ZOOM_SCALE_MOTION_Y = originalScaleMotionY;
        ZOOM_SCALE_RENDER_X = originalScaleRenderX;
        ZOOM_SCALE_RENDER_Y = originalScaleRenderY;
        OFFSET_X = 0;
        OFFSET_Y = 0;
    }

    /**
     * Adjust the zoom
     * @param adjust The ratio amount to adjust our screen dimensions
     */
    public void adjustZoom(final float adjust) {

        this.zoomRatio += adjust;

        //keep the zoom within the boundary
        if (zoomRatio > ZOOM_RATIO_MAX)
            this.zoomRatio = ZOOM_RATIO_MAX;
        if (zoomRatio < ZOOM_RATIO_MIN)
            this.zoomRatio = ZOOM_RATIO_MIN;

        //store the ratio for the render
        ZOOM_SCALE_RENDER_X = screenWidth / (float) (WIDTH * zoomRatio);
        ZOOM_SCALE_RENDER_Y = screenHeight / (float) (HEIGHT * zoomRatio);

        //store the ratio when touching the screen
        ZOOM_SCALE_MOTION_X = (float) (WIDTH * zoomRatio) / screenWidth;
        ZOOM_SCALE_MOTION_Y = (float) (HEIGHT * zoomRatio) / screenHeight;
    }

    /**
     *  Called if the geometry of the view changes.<br>
     *  For example when the device's screen orientation changes
     * @param gl OpenGL object
     * @param width pixel width of surface
     * @param height pixel height of surface
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        //flag that we have not yet loaded the textures
        LOADED = false;

        //store screen dimensions
        this.screenWidth = width;
        this.screenHeight = height;

        //store the ratio for the render
        this.originalScaleRenderX = screenWidth / (float) WIDTH;
        this.originalScaleRenderY = screenHeight / (float) HEIGHT;

        //store the ratio when touching the screen
        this.originalScaleMotionX = (float) WIDTH / screenWidth;
        this.originalScaleMotionY = (float) HEIGHT / screenHeight;

        //set the zoom values same as original
        resetZoom();

        //sets the current view port to the new size of the screen
        gl.glViewport(0, 0, screenWidth, screenHeight);

        //reset the projection matrix back to its default state
        gl.glLoadIdentity();

        //select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);

        //set rendering dimensions
        gl.glOrthof(0.0f, screenWidth, screenHeight, 0.0f, 1.0f, -1.0f);

        //select the model view matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        //enable 2d textures
        gl.glEnable(GL10.GL_TEXTURE_2D);

        //enable smooth shading
        gl.glEnableClientState(GL10.GL_SMOOTH);

        //load our textures
        this.textures.loadTextures(gl);

        //flag that we have loaded the textures
        LOADED = true;
    }

    /**
     * Called for each redraw of the view
     * @param gl Object used for rendering textures
     */
    @Override
    public void onDrawFrame(GL10 gl) {

        //get the current time
        long time = System.currentTimeMillis();

        //clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        //reset the projection matrix
        gl.glLoadIdentity();

        //offset the board in case the user is scrolling
        gl.glTranslatef(OFFSET_X, OFFSET_Y, 0);

        //scale to our game dimensions to match the users screen
        gl.glScalef(ZOOM_SCALE_RENDER_X, ZOOM_SCALE_RENDER_Y, 0.0f);

        //render game objects
        getGame().render(gl);

        if (DEBUG && !true) {

            //calculate how long it took to render a single frame
            long duration = System.currentTimeMillis() - time;

            //if it took too long, notify command line
            if (duration > FRAME_DURATION)
                UtilityHelper.logEvent("Single render duration: " + (System.currentTimeMillis() - time));
        }
    }
}