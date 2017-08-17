package com.gamesbykevin.connect.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.R;

import static com.gamesbykevin.androidframeworkv2.util.UtilityHelper.DEBUG;

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
     */
    public void loadTextures() {

        //reset index
        this.index = 0;

        //sprite sheet containing a lot of images
        //Bitmap square = BitmapFactory.decodeResource(activity.getResources(), R.drawable.square);
        //Bitmap ball = Bitmap.createBitmap(sheet, (Ball.DIMENSIONS * i), 0, Ball.DIMENSIONS, Ball.DIMENSIONS);

        //load the texture
        TEXTURE_ID_BACKGROUND = loadTexture(R.drawable.background);

        TEXTURE_ID_SQUARE_GRAY_PIPE_NS = loadTexture(R.drawable.square_pipe_ns_gray);
        TEXTURE_ID_SQUARE_GRAY_PIPE_SE = loadTexture(R.drawable.square_pipe_se_gray);
        TEXTURE_ID_SQUARE_GRAY_PIPE_WES = loadTexture(R.drawable.square_pipe_wes_gray);
        TEXTURE_ID_SQUARE_GRAY_PIPE_NSEW = loadTexture(R.drawable.square_pipe_nsew_gray);
        TEXTURE_ID_SQUARE_GRAY_PIPE_END = loadTexture(R.drawable.square_pipe_end_gray);

        TEXTURE_ID_SQUARE_GREEN_PIPE_NS = loadTexture(R.drawable.square_pipe_ns_green);
        TEXTURE_ID_SQUARE_GREEN_PIPE_SE = loadTexture(R.drawable.square_pipe_se_green);
        TEXTURE_ID_SQUARE_GREEN_PIPE_WES = loadTexture(R.drawable.square_pipe_wes_green);
        TEXTURE_ID_SQUARE_GREEN_PIPE_NSEW = loadTexture(R.drawable.square_pipe_nsew_green);
        TEXTURE_ID_SQUARE_GREEN_PIPE_END = loadTexture(R.drawable.square_pipe_end_green);

        TEXTURE_ID_DIAMOND_GRAY_PIPE_NS = loadTexture(R.drawable.diamond_pipe_ns_gray);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_SE = loadTexture(R.drawable.diamond_pipe_se_gray);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_WES = loadTexture(R.drawable.diamond_pipe_wes_gray);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_NSEW = loadTexture(R.drawable.diamond_pipe_nsew_gray);
        TEXTURE_ID_DIAMOND_GRAY_PIPE_END = loadTexture(R.drawable.diamond_pipe_end_gray);

        TEXTURE_ID_DIAMOND_GREEN_PIPE_NS = loadTexture(R.drawable.diamond_pipe_ns_green);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_SE = loadTexture(R.drawable.diamond_pipe_se_green);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_WES = loadTexture(R.drawable.diamond_pipe_wes_green);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_NSEW = loadTexture(R.drawable.diamond_pipe_nsew_green);
        TEXTURE_ID_DIAMOND_GREEN_PIPE_END = loadTexture(R.drawable.diamond_pipe_end_green);

        TEXTURE_ID_HEXAGON_GRAY_PIPE_ALL = loadTexture(R.drawable.hexagon_pipe_gray_all);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_END = loadTexture(R.drawable.hexagon_pipe_gray_end);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E = loadTexture(R.drawable.hexagon_pipe_gray_ne_e);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE = loadTexture(R.drawable.hexagon_pipe_gray_ne_e_se);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW = loadTexture(R.drawable.hexagon_pipe_gray_ne_e_se_sw);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W = loadTexture(R.drawable.hexagon_pipe_gray_ne_e_se_sw_w);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW = loadTexture(R.drawable.hexagon_pipe_gray_ne_e_sw);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W = loadTexture(R.drawable.hexagon_pipe_gray_ne_e_w);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE = loadTexture(R.drawable.hexagon_pipe_gray_ne_se);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW = loadTexture(R.drawable.hexagon_pipe_gray_ne_sw);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W = loadTexture(R.drawable.hexagon_pipe_gray_ne_w);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW = loadTexture(R.drawable.hexagon_pipe_gray_nw_ne_se_sw);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW = loadTexture(R.drawable.hexagon_pipe_gray_nw_ne_e_sw);
        TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW = loadTexture(R.drawable.hexagon_pipe_gray_nw_e_sw);

        TEXTURE_ID_HEXAGON_GREEN_PIPE_ALL = loadTexture(R.drawable.hexagon_pipe_green_all);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_END = loadTexture(R.drawable.hexagon_pipe_green_end);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E = loadTexture(R.drawable.hexagon_pipe_green_ne_e);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE = loadTexture(R.drawable.hexagon_pipe_green_ne_e_se);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW = loadTexture(R.drawable.hexagon_pipe_green_ne_e_se_sw);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W = loadTexture(R.drawable.hexagon_pipe_green_ne_e_se_sw_w);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW = loadTexture(R.drawable.hexagon_pipe_green_ne_e_sw);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W = loadTexture(R.drawable.hexagon_pipe_green_ne_e_w);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE = loadTexture(R.drawable.hexagon_pipe_green_ne_se);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW = loadTexture(R.drawable.hexagon_pipe_green_ne_sw);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W = loadTexture(R.drawable.hexagon_pipe_green_ne_w);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW = loadTexture(R.drawable.hexagon_pipe_green_nw_ne_se_sw);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW = loadTexture(R.drawable.hexagon_pipe_green_nw_ne_e_sw);
        TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW = loadTexture(R.drawable.hexagon_pipe_green_nw_e_sw);
    }

    /**
     * Load a single texture
     * @param resId The resource id
     * @return texture id from generating a texture
     */
    public int loadTexture(int resId) {

        try {

            //make sure we aren't pre-scaling the image when loading the texture(s)
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;

            //get our bitmap
            final Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), resId, options);

            //our container to generate the textures
            GLES20.glGenTextures(1, IDS, index);

            //bind texture to texture id
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, IDS[index]);

            // Set filtering
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle();

            if (IDS[index] == 0) {
                throw new Exception("Error loading texture: " + index);
            } else {

                //display texture id
                if (DEBUG)
                    UtilityHelper.logEvent("Texture loaded id: " + IDS[index]);
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