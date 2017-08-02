package com.gamesbykevin.connect.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.androidframeworkv2.util.UtilityHelper.DEBUG;
import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.FRAME_DURATION;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;

/**
 * Created by Kevin on 6/1/2017.
 */
public class OpenGLRenderer implements Renderer {

    //get the ratio of the users screen compared to the default dimensions for the render
    private float scaleRenderX, scaleRenderY;

    //get the ratio of the users screen compared to the default dimensions for the motion event
    public float scaleMotionX = 0, scaleMotionY = 0;

    /**
     * Have all textures been loaded?
     */
    public static boolean LOADED = false;

    //object containing all the texture ids
    private Textures textures;

    public OpenGLRenderer(Context activity) {

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

        //store the ratio for the render
        this.scaleRenderX = width / (float) WIDTH;
        this.scaleRenderY = height / (float) HEIGHT;

        //store the ratio when touching the screen
        this.scaleMotionX = (float) WIDTH / width;
        this.scaleMotionY = (float) HEIGHT / height;

        //sets the current view port to the new size of the screen
        gl.glViewport(0, 0, width, height);

        //reset the projection matrix back to its default state
        gl.glLoadIdentity();

        //select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);

        //set rendering dimensions
        gl.glOrthof(0.0f, width, height, 0.0f, 1.0f, -1.0f);

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

        //scale to our game dimensions to match the users screen
        gl.glScalef(scaleRenderX, scaleRenderY, 0.0f);

        //render game objects
        getGame().render(gl);

        if (DEBUG) {

            //calculate how long it took to render a single frame
            long duration = System.currentTimeMillis() - time;

            //if it took too long, notify command line
            if (duration > FRAME_DURATION)
                UtilityHelper.logEvent("Single render duration: " + (System.currentTimeMillis() - time));
        }
    }
}