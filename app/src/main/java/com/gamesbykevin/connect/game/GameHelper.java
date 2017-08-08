package com.gamesbykevin.connect.game;

import com.gamesbykevin.connect.entity.Entity;
import com.gamesbykevin.connect.opengl.Textures;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.FPS;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;

/**
 * Game helper methods
 * @author GOD
 */
public final class GameHelper 
{
	private static Entity entity = null;

    public static Entity getEntity() {
        if (entity == null)
            entity = new Entity();

        return entity;
    }

    private static float angle = 0;

    /**
     * How far do we zoom in/out
     */
    private static float ZOOM_RATIO = 2.5f;

    //did we flag the game over?
    public static boolean GAME_OVER = false;

    //how long do we wait until displaying the game over overlay
    public static final int GAME_OVER_DELAY_FRAMES = (FPS * 3);

    //keep track of elapsed frames
    public static int FRAMES = 0;

    /**
     * Render the game accordingly
     * @param openGL Place to write pixel data
     * @throws Exception
     */
    public static final void render(final GL10 openGL)
    {
		//make sure we are supporting alpha for transparency
		openGL.glEnable(GL10.GL_BLEND);
		openGL.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		//render the pieces on the board
        getGame().getBoard().render(openGL);
    }
}