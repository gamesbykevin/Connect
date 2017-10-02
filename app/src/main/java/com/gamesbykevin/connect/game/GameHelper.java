package com.gamesbykevin.connect.game;

import android.opengl.GLES20;

import com.gamesbykevin.connect.base.Entity;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.opengl.Square;

import static com.gamesbykevin.connect.activity.GameActivity.getGame;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.ZOOM_DEFAULT;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.adjustZoom;
import static com.gamesbykevin.connect.opengl.OpenGLRenderer.resetZoom;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.FPS;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;

/**
 * Game helper methods
 * @author GOD
 */
public final class GameHelper 
{
	private static Entity entityBackground = null;

    private static Square square = null;

    private static Square squareBackground = null;

    public static Entity getEntityBackground() {

        if (entityBackground == null) {
            entityBackground = new Entity();
            entityBackground.setX(0);
            entityBackground.setY(0);
            entityBackground.setAngle(0f);
            entityBackground.setWidth(WIDTH);
            entityBackground.setHeight(HEIGHT);

            //only needed to do one time
            getSquareBackground().setupImage();
            getSquareBackground().setupTriangle();
            getSquareBackground().setupVertices(entityBackground.getVertices());
        }

        return entityBackground;
    }


    public static Square getSquare() {
        if (square == null)
            square = new Square();

        return square;
    }

    public static Square getSquareBackground() {
        if (squareBackground == null)
            squareBackground = new Square();

        return squareBackground;
    }

    //did we flag the game over?
    public static boolean GAME_OVER = false;

    //how long do we wait until displaying the game over overlay
    public static final int GAME_OVER_DELAY_FRAMES = (FPS * 3);

    //keep track of elapsed frames
    public static int FRAMES = 0;

    /**
     * Are we resuming a saved game?
     */
    public static boolean RESUME_SAVE = false;

    public static void dispose() {
        squareBackground = null;
        square = null;
        entityBackground = null;
    }

    public static void zoomOut(final Board board) {

        //reset the zoom for a new level
        resetZoom();

        //get the board size
        final float w = (float)(board.getWidth() * 1.1);
        final float h = (float)(board.getHeight() * 1.1);

        //calculate the width and height ratio
        final float wRatio = (w / WIDTH);
        final float hRatio = (h / HEIGHT);

        //make sure we zoom out properly
        if (wRatio > 1.0f && hRatio > 1.0f) {

            //we want to offset the bigger ratio difference
            if (wRatio > hRatio) {
                adjustZoom(wRatio - ZOOM_DEFAULT);
            } else {
                adjustZoom(hRatio - ZOOM_DEFAULT);
            }

        } else if (wRatio > 1.0f) {
            adjustZoom(wRatio - ZOOM_DEFAULT);
        } else if (hRatio > 1.0f) {
            adjustZoom(hRatio - ZOOM_DEFAULT);
        }
    }

    /**
     * Render the game accordingly
     * @throws Exception
     */
    public static void render(float[] m) {

		//make sure we are supporting alpha for transparency
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

		//render the pieces on the board
        getGame().getBoard().render(m);

        //we can now disable alpha transparency
        GLES20.glDisable(GLES20.GL_BLEND);
    }
}