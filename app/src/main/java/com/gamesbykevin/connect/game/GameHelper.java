package com.gamesbykevin.connect.game;

import android.opengl.GLES20;

import com.gamesbykevin.connect.entity.Entity;
import com.gamesbykevin.connect.opengl.Square;

import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.FPS;

/**
 * Game helper methods
 * @author GOD
 */
public final class GameHelper 
{
	private static Entity entity = null;

    private static Square square = null;

    public static Entity getEntity() {
        if (entity == null)
            entity = new Entity();

        return entity;
    }


    public static Square getSquare() {
        if (square == null)
            square = new Square();

        return square;
    }

    //did we flag the game over?
    public static boolean GAME_OVER = false;

    //how long do we wait until displaying the game over overlay
    public static final int GAME_OVER_DELAY_FRAMES = (FPS * 3);

    //keep track of elapsed frames
    public static int FRAMES = 0;

    /**
     * Render the game accordingly
     * @throws Exception
     */
    public static final void render(float[] m) {

		//make sure we are supporting alpha for transparency
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

		//render the pieces on the board
        getGame().getBoard().render(m);

        //we can now disable alpha transparency
        GLES20.glDisable(GLES20.GL_BLEND);
    }
}