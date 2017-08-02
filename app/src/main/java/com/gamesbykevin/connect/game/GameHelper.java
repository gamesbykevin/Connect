package com.gamesbykevin.connect.game;

import com.gamesbykevin.connect.entity.Entity;
import com.gamesbykevin.connect.opengl.Textures;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;

/**
 * Game helper methods
 * @author GOD
 */
public final class GameHelper 
{
    /**
     * Check if the game is over
     */
    protected final static boolean isGameOver()
	{
		return false;
	}

	private static Entity entity = null;

    private static Entity getEntity() {
        if (entity == null)
            entity = new Entity();

        return entity;
    }

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

        getEntity().setTextureId(Textures.TEXTURE_ID_BACKGROUND);
        getEntity().setX(0);
        getEntity().setY(0);
        getEntity().setWidth(WIDTH);
        getEntity().setHeight(HEIGHT);
        getEntity().render(openGL);

		//render the pieces on the board
        getGame().getBoard().render(openGL);
    }
}