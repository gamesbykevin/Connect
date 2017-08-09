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

    public static int TEXTURE_ID_BACKGROUND = 0;

    //texture id for each shape
    public static int TEXTURE_ID_SQUARE_GRAY_PIPE_NS = 0;
    public static int TEXTURE_ID_SQUARE_GRAY_PIPE_SE = 0;
    public static int TEXTURE_ID_SQUARE_GRAY_PIPE_WES = 0;
    public static int TEXTURE_ID_SQUARE_GRAY_PIPE_NSEW = 0;
    public static int TEXTURE_ID_SQUARE_GRAY_PIPE_END = 0;

    public static int TEXTURE_ID_SQUARE_GREEN_PIPE_NS = 0;
    public static int TEXTURE_ID_SQUARE_GREEN_PIPE_SE = 0;
    public static int TEXTURE_ID_SQUARE_GREEN_PIPE_WES = 0;
    public static int TEXTURE_ID_SQUARE_GREEN_PIPE_NSEW = 0;
    public static int TEXTURE_ID_SQUARE_GREEN_PIPE_END = 0;

    public static int TEXTURE_ID_DIAMOND_GRAY_PIPE_NS = 0;
    public static int TEXTURE_ID_DIAMOND_GRAY_PIPE_SE = 0;
    public static int TEXTURE_ID_DIAMOND_GRAY_PIPE_WES = 0;
    public static int TEXTURE_ID_DIAMOND_GRAY_PIPE_NSEW = 0;
    public static int TEXTURE_ID_DIAMOND_GRAY_PIPE_END = 0;

    public static int TEXTURE_ID_DIAMOND_GREEN_PIPE_NS = 0;
    public static int TEXTURE_ID_DIAMOND_GREEN_PIPE_SE = 0;
    public static int TEXTURE_ID_DIAMOND_GREEN_PIPE_WES = 0;
    public static int TEXTURE_ID_DIAMOND_GREEN_PIPE_NSEW = 0;
    public static int TEXTURE_ID_DIAMOND_GREEN_PIPE_END = 0;

    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_ALL = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_END = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW = 0;


    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_ALL = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_END = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW = 0;
    public static int TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW = 0;


    //store reference to access resources
    private final Context activity;

    //keep track of the current index
    private int index = 0;

    public Textures(Context activity) {

        this.activity = activity;

        //create array containing all the texture ids
        IDS = new int[49];
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
        TEXTURE_ID_BACKGROUND = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.background), openGL);

        TEXTURE_ID_SQUARE_GRAY_PIPE_NS = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_ns_gray), openGL);
        TEXTURE_ID_SQUARE_GRAY_PIPE_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_se_gray), openGL);
        TEXTURE_ID_SQUARE_GRAY_PIPE_WES = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_wes_gray), openGL);
        TEXTURE_ID_SQUARE_GRAY_PIPE_NSEW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_nsew_gray), openGL);
        TEXTURE_ID_SQUARE_GRAY_PIPE_END = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_end_gray), openGL);

        TEXTURE_ID_SQUARE_GREEN_PIPE_NS = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_ns_green), openGL);
        TEXTURE_ID_SQUARE_GREEN_PIPE_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_se_green), openGL);
        TEXTURE_ID_SQUARE_GREEN_PIPE_WES = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_wes_green), openGL);
        TEXTURE_ID_SQUARE_GREEN_PIPE_NSEW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_nsew_green), openGL);
        TEXTURE_ID_SQUARE_GREEN_PIPE_END = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.square_pipe_end_green), openGL);

        TEXTURE_ID_DIAMOND_GRAY_PIPE_NS = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_ns_gray), openGL);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_se_gray), openGL);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_WES = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_wes_gray), openGL);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_NSEW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_nsew_gray), openGL);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_END = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_end_gray), openGL);

        TEXTURE_ID_DIAMOND_GREEN_PIPE_NS = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_ns_green), openGL);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_se_green), openGL);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_WES = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_wes_green), openGL);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_NSEW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_nsew_green), openGL);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_END = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.diamond_pipe_end_green), openGL);

        TEXTURE_ID_HEXAGON_GRAY_PIPE_ALL = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_all), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_END = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_end), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_e), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_e_se), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_e_se_sw), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_e_se_sw_w), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_e_sw), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_e_w), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_se), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_sw), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_ne_w), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_nw_ne_se_sw), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_nw_ne_e_sw), openGL);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_gray_nw_e_sw), openGL);

        TEXTURE_ID_HEXAGON_GREEN_PIPE_ALL = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_all), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_END = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_end), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_e), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_e_se), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_e_se_sw), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_e_se_sw_w), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_e_sw), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_e_w), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_se), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_sw), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_ne_w), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_nw_ne_se_sw), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_nw_ne_e_sw), openGL);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW = loadTexture(BitmapFactory.decodeResource(activity.getResources(), R.drawable.hexagon_pipe_green_nw_e_sw), openGL);
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