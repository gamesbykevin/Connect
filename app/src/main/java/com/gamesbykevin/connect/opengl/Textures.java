package com.gamesbykevin.connect.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.R;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin on 7/23/2017.
 */

public class Textures {

    //array containing all the texture ids
    public static int[] IDS;

    //texture id for each shape
    public static int TEXTURE_ID_SQUARE = 0;
    public static int TEXTURE_ID_HEXAGON = 0;
    public static int TEXTURE_ID_TRIANGLE = 0;
    public static int TEXTURE_ID_BACKGROUND = 0;


    //store reference to access resources
    private final Context activity;

    //keep track of the current index
    private int index = 0;

    public Textures(Context activity) {

        this.activity = activity;

        //create array containing all the texture ids
        IDS = new int[4];
    }

    /**
     * Load all the textures
     * @param openGL
     */
    public void loadTextures(GL10 openGL) {

        //reset index
        this.index = 0;

        //sprite sheet containing a lot of images
        //Bitmap square = BitmapFactory.decodeResource(activity.getResources(), R.drawable.square);
        //Bitmap ball = Bitmap.createBitmap(sheet, (Ball.DIMENSIONS * i), 0, Ball.DIMENSIONS, Ball.DIMENSIONS);

        //load the texture
        TEXTURE_ID_SQUARE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square), openGL);
        TEXTURE_ID_HEXAGON = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon), openGL);
        TEXTURE_ID_TRIANGLE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.triangle), openGL);
        TEXTURE_ID_BACKGROUND = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.background), openGL);
    }

    /**
     * Load a single texture
     * @param bitmap Image we want to convert into a texture
     * @param openGL Open GL Context
     * @return texture id from generating a texture
     */
    public int loadTexture(Bitmap bitmap, GL10 openGL) {

        try {

            //our container to generate the textures
            openGL.glGenTextures(1, IDS, index);

            //bind the texture id
            openGL.glBindTexture(GL10.GL_TEXTURE_2D, IDS[index]);

            if (false) {
                //we want smoother images
                openGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
                openGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
            } else {
                //not smoother images
                openGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
                openGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            }

            //allow any size texture
            openGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);

            //allow any size texture
            openGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

            //add bitmap to texture
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

            //we no longer need the resource
            bitmap.recycle();

            if (IDS[index] == 0) {
                throw new Exception("Error loading texture: " + index);
            } else {
                //display texture id
                //UtilityHelper.logEvent("Texture loaded id: " + IDS[index]);
            }

        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }

        //get texture id
        int value = IDS[index];

        //keep increasing the index
        index++;

        //return our value
        return value;
    }
}