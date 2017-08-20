package com.gamesbykevin.connect.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.androidframeworkv2.util.UtilityHelper.DEBUG;
import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.game.Game.RESET_ZOOM;

import static com.gamesbykevin.connect.game.Game.ZOOM_SCALE_MOTION_X;
import static com.gamesbykevin.connect.game.Game.ZOOM_SCALE_MOTION_Y;
import static com.gamesbykevin.connect.game.GameHelper.getEntity;
import static com.gamesbykevin.connect.game.GameHelper.getSquare;
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
    private static float ZOOM_RATIO_MAX = 5.0f;

    /**
     * The minimum amount we can zoom in
     */
    private static float ZOOM_RATIO_MIN = 0.2f;

    //keep track of the offset
    private static float PAN_X = 0f, PAN_Y = 0f;

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

    //our matrices
    private final float[] mtrxProjection = new float[16];
    private final float[] mtrxView = new float[16];
    private final float[] mtrxProjectionAndView = new float[16];

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
     * Restore the scale values as when the surface was first created
     */
    public static void resetZoom() {

        //store the zoom variables as the same
        ZOOM_SCALE_MOTION_X = originalScaleMotionX;
        ZOOM_SCALE_MOTION_Y = originalScaleMotionY;
        OFFSET_X = 0;
        OFFSET_Y = 0;
        PAN_X = 0f;
        PAN_Y = 0f;

        //flag false
        RESET_ZOOM = false;
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

        //store the ratio when touching the screen
        ZOOM_SCALE_MOTION_X = (WIDTH * zoomRatio) / screenWidth;
        ZOOM_SCALE_MOTION_Y = (HEIGHT * zoomRatio) / screenHeight;

        //every time we zoom, reset the offset (x, y)
        OFFSET_X = 0f;
        OFFSET_Y = 0f;
        PAN_X = 0f;
        PAN_Y = 0f;

        //adjust the zoom on the matrix
        Matrix.orthoM(mtrxProjection, 0, 0f, WIDTH * zoomRatio, HEIGHT * zoomRatio, 0f, 0f, 50f);

        //calculate the projection and view transformation
        Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);
    }

    public void adjustPan(float x, float y) {

        //keep track of the panning
        PAN_X += x;
        PAN_Y += y;

        //offset the screen
        Matrix.translateM(mtrxProjection, 0, x, y, 0.0f);

        //calculate the projection and view transformation
        Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);
    }

    /**
     * Called once to set up the view's OpenGL ES environment
     */
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        //init square used for rendering
        getSquare();

        //set the clear color to black
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1);

        //create the shaders, solid color
        int vertexShader = riGraphicTools.loadShader(GLES20.GL_VERTEX_SHADER, riGraphicTools.vs_SolidColor);
        int fragmentShader = riGraphicTools.loadShader(GLES20.GL_FRAGMENT_SHADER, riGraphicTools.fs_SolidColor);
        riGraphicTools.sp_SolidColor = GLES20.glCreateProgram();    // create empty OpenGL ES Program
        GLES20.glAttachShader(riGraphicTools.sp_SolidColor, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(riGraphicTools.sp_SolidColor, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(riGraphicTools.sp_SolidColor);                  // creates OpenGL ES program executables

        //create the shaders, images
        vertexShader = riGraphicTools.loadShader(GLES20.GL_VERTEX_SHADER, riGraphicTools.vs_Image);
        fragmentShader = riGraphicTools.loadShader(GLES20.GL_FRAGMENT_SHADER, riGraphicTools.fs_Image);
        riGraphicTools.sp_Image = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(riGraphicTools.sp_Image, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(riGraphicTools.sp_Image, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(riGraphicTools.sp_Image);                  // creates OpenGL ES program executables

        //set our shader program
        GLES20.glUseProgram(riGraphicTools.sp_Image);

        //flag that we have not yet loaded the textures
        LOADED = false;

        //load our textures
        this.textures.loadTextures();
    }

    /**
     *  Called if the geometry of the view changes.<br>
     *  For example when the device's screen orientation changes
     * @param width pixel width of surface
     * @param height pixel height of surface
     */
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {

        //store screen dimensions
        this.screenWidth = width;
        this.screenHeight = height;

        //make the viewport fullscreen on the users phone by using their screen width/height
        GLES20.glViewport(0, 0, (int)screenWidth, (int)screenHeight);

        //clear our matrices
        for(int i = 0; i < mtrxProjectionAndView.length; i++)
        {
            mtrxProjection[i] = 0.0f;
            mtrxView[i] = 0.0f;
            mtrxProjectionAndView[i] = 0.0f;
        }

        //setup our screen width and height for our intended screen size
        Matrix.orthoM(mtrxProjection, 0, 0f, WIDTH, HEIGHT, 0f, 0f, 50f);

        //offset the screen
        Matrix.translateM(mtrxProjection, 0, 0.0f, 0.0f, 0.0f);

        //set the camera position (matrix)
        Matrix.setLookAtM(mtrxView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        //calculate the projection and view transformation
        Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);

        //store the ratio when touching the screen
        this.originalScaleMotionX = (float)WIDTH  / (float)screenWidth;
        this.originalScaleMotionY = (float)HEIGHT / (float)screenHeight;

        //set the zoom values same as original
        if (RESET_ZOOM)
            resetZoom();

        //flag that we have loaded the textures & screens
        LOADED = true;
    }

    /**
     * Called for each redraw of the view
     */
    @Override
    public void onDrawFrame(GL10 unused) {

        //get the current time
        long time = System.currentTimeMillis();

        //clear the screen and depth buffer, we have set the clear color as black.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //support transparency
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_BLEND_SRC_ALPHA);

        //render our background
        setupBackground();
        getSquare().render(getEntity(), mtrxProjectionAndView);

        //restore the zoom and pan coordinates
        Matrix.orthoM(mtrxProjection, 0, 0f, WIDTH * zoomRatio, HEIGHT * zoomRatio, 0f, 0f, 50f);
        Matrix.translateM(mtrxProjection, 0, PAN_X, PAN_Y, 0.0f);
        Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);

        //render game elements
        getGame().render(mtrxProjectionAndView);

        if (DEBUG && !false) {

            //calculate how long it took to render a single frame
            long duration = System.currentTimeMillis() - time;

            //if it took too long, notify command line
            if (duration > FRAME_DURATION)
                UtilityHelper.logEvent("Single render duration: " + (System.currentTimeMillis() - time));
        }
    }

    /**
     * Setup the background to be rendered
     */
    private void setupBackground() {

        //set the correct texture for rendering
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, Textures.TEXTURE_ID_BACKGROUND);
        getEntity().setAngle(0f);

        //always render the background full screen
        getEntity().setX(0);
        getEntity().setY(0);
        getEntity().setWidth(WIDTH);
        getEntity().setHeight(HEIGHT);

        //reset to normal so background is displayed as is without transformation
        Matrix.orthoM(mtrxProjection, 0, 0f, WIDTH, HEIGHT, 0f, 0f, 50f);
        Matrix.translateM(mtrxProjection, 0, 0.0f, 0.0f, 0.0f);
        Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);
    }
}