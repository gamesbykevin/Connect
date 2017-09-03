package com.gamesbykevin.connect.board;

import android.opengl.GLES20;

import com.gamesbykevin.connect.base.Entity;
import com.gamesbykevin.androidframeworkv2.maze.Maze;
import com.gamesbykevin.androidframeworkv2.maze.algorithm.Prims;
import com.gamesbykevin.connect.util.UtilityHelper;
import com.gamesbykevin.connect.opengl.Textures;
import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.common.ICommon;
import com.gamesbykevin.connect.shape.Diamond;
import com.gamesbykevin.connect.shape.Hexagon;
import com.gamesbykevin.connect.shape.Square;

import static com.gamesbykevin.connect.activity.GameActivity.getRandomObject;
import static com.gamesbykevin.connect.board.BoardHelper.CALCULATE_INDICES;
import static com.gamesbykevin.connect.board.BoardHelper.CALCULATE_UVS;
import static com.gamesbykevin.connect.board.BoardHelper.CALCULATE_VERTICES;
import static com.gamesbykevin.connect.board.BoardHelper.addShapes;
import static com.gamesbykevin.connect.board.BoardHelper.checkBoard;
import static com.gamesbykevin.connect.board.BoardHelper.updateCoordinates;
import static com.gamesbykevin.connect.board.BoardHelper.updateShape;
import static com.gamesbykevin.connect.game.Game.AUTO_ROTATE;
import static com.gamesbykevin.connect.game.GameHelper.getSquare;

/**
 * Created by Kevin on 8/1/2017.
 */
public class Board implements ICommon {

    //store these coordinates for rendering
    private static float[] VERTICES;
    private static short[] INDICES;
    private static float[] UVS;

    //array of shapes on the board
    private CustomShape[][] shapes;

    public enum Shape {
        Square, Hexagon, Diamond;
    }

    //what type of shape are we using
    private Shape type = null;

    //our maze generation object
    private Maze maze;

    private Entity entity = new Entity();

    public static int BOARD_COLS;
    public static int BOARD_ROWS;

    //base point that we will mark connected
    public static int ANCHOR_COL;
    public static int ANCHOR_ROW;

    //the current rotating shape
    private CustomShape rotationShape = null;

    /**
     * Default constructor
     */
    public Board(int cols, int rows) throws Exception {

        //make sure board is large enough
        if (cols < 3 || rows < 3)
            throw new Exception("Board size too small " + cols + ", " + rows);

        //which shape is in the center
        ANCHOR_COL = (cols / 2);
        ANCHOR_ROW = (rows / 2);
    }

    /**
     * Get the width of the entire board
     * @return The pixel width of the entire board
     */
    public float getWidth() {
        return BoardHelper.getWidth(this);
    }

    /**
     * Get the height of the entire board
     * @return The pixel height of the entire board
     */
    public float getHeight() {
        return BoardHelper.getHeight(this);
    }

    public Maze getMaze() {
        return this.maze;
    }

    protected CustomShape[][] getShapes() {
        return this.shapes;
    }

    /**
     * Touch the board and rotate the selected shape
     * @param x x-coordinate
     * @param y y-coordinate
     */
    /**
     * Touch the board and rotate the selected shape
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if a shape was marked for rotation, false otherwise
     */
    public boolean touch(float x, float y) {

        //if we are already rotating don't check again
        if (getRotationShape() != null)
            return false;

        for (int col = 0; col < getMaze().getCols(); col++) {
            for (int row = 0; row < getMaze().getRows(); row++) {
                CustomShape shape = getShapes()[row][col];

                if (shape != null && !shape.hasRotate() && shape.contains(x, y)) {

                    //determine how fast the shape rotates
                    switch(getType()) {

                        case Square:
                            shape.setRotateVelocity(AUTO_ROTATE ? Square.ROTATE_VELOCITY_FAST : Square.ROTATE_VELOCITY);
                            break;

                        case Hexagon:
                            shape.setRotateVelocity(AUTO_ROTATE ? Hexagon.ROTATE_VELOCITY_FAST : Hexagon.ROTATE_VELOCITY);
                            break;

                        case Diamond:
                            shape.setRotateVelocity(AUTO_ROTATE ? Diamond.ROTATE_VELOCITY_FAST : Diamond.ROTATE_VELOCITY);
                            break;

                        default:
                            throw new RuntimeException("Type not defined: " + getType().toString());
                    }

                    shape.setRotationCount(0);
                    shape.rotate();
                    setRotationShape(shape);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void dispose() {

        if (getShapes() != null) {

            for (int col = 0; col < getMaze().getCols(); col++) {
                for (int row = 0; row < getMaze().getRows(); row++) {

                    try {
                        CustomShape shape = getShapes()[row][col];

                        if (shape != null) {
                            shape.dispose();
                            shape = null;
                        }
                    } catch (Exception e) {
                        UtilityHelper.handleException(e);
                    }
                }
            }

            shapes = null;
        }

        if (getMaze() != null) {
            getMaze().dispose();
            this.maze = null;
        }

        //flag null
        setRotationShape(null);

        VERTICES = null;
        UVS = null;
        INDICES = null;
    }

    @Override
    public void update(GameActivity activity) {

        try {

            if (!getMaze().isGenerated()) {

                //keep generating until generated
                while (!getMaze().isGenerated()) {
                    getMaze().update(getRandomObject());
                }

                //add the shapes to the board
                addShapes(this);

                //highlight the connected pipes
                checkBoard(this, false);

            } else {

                if (getRotationShape() != null) {

                    //update the rotation
                    getRotationShape().update(activity);

                    //if we still need to rotate don't go any further
                    if (getRotationShape().hasRotate())
                        return;

                    //make sure the vertices are updated
                    getRotationShape().updateVertices();

                    boolean check = false;

                    //if magnet is enabled, check if we still need to rotate
                    if (AUTO_ROTATE) {

                        //if we can't connect and the magnet is enabled, what do we do?
                        if (!BoardHelper.canConnect(this, getRotationShape())) {
                            if (getRotationShape().getRotationCount() >= getRotationShape().getRotationCountMax()) {
                                //flag true to check the board
                                check = true;
                            } else {
                                getRotationShape().rotate();
                            }
                        } else {
                            //flag true to check the board
                            check = true;
                        }
                    } else {
                        //flag true to check the board
                        check = true;
                    }

                    //do we need to check the board
                    if (check) {
                        checkBoard(this, true);
                        setRotationShape(null);
                    }
                }
            }
            
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    protected CustomShape getRotationShape() {
        return this.rotationShape;
    }

    private void setRotationShape(CustomShape rotationShape) {
        this.rotationShape = rotationShape;
    }

    public void setType(final Shape type) {
        this.type = type;
    }

    public Shape getType() {
        return this.type;
    }

    public float[] getVertices() {

        final int length = (getShapes()[0].length * getShapes().length) * 4 * 3;

        //if null or the size doesn't add up
        if (VERTICES == null || VERTICES.length != length) {
            VERTICES = new float[length];

            for (int i = 0; i < VERTICES.length; i++) {
                VERTICES[i] = 0;
            }
        }

        return VERTICES;
    }

    public short[] getIndices() {

        //expected length of array
        final int length = (shapes[0].length * shapes.length) * 6;

        //if null or the size doesn't add up
        if (INDICES == null || INDICES.length != length) {
            INDICES = new short[length];

            int last = 0;

            for (int index = 0; index < getShapes()[0].length * getShapes().length; index++) {

                try {
                    //we need to set the new indices for the new quad
                    INDICES[(index * 6) + 0] = (short) (last + 0);
                    INDICES[(index * 6) + 1] = (short) (last + 1);
                    INDICES[(index * 6) + 2] = (short) (last + 2);
                    INDICES[(index * 6) + 3] = (short) (last + 0);
                    INDICES[(index * 6) + 4] = (short) (last + 2);
                    INDICES[(index * 6) + 5] = (short) (last + 3);

                    //normal quad = 0,1,2,0,2,3 so the next one will be 4,5,6,4,6,7
                    last = last + 4;

                } catch (Exception e) {
                    UtilityHelper.handleException(e);
                }
            }
        }

        return INDICES;
    }

    public float[] getUvs() {

        final int length = (getShapes()[0].length * getShapes().length) * 4 * 2;

        //if null or the size doesn't add up
        if (UVS == null || UVS.length != length) {
            UVS = new float[length];

            for (int i = 0; i < UVS.length; i++) {
                UVS[i] = 0;
            }
        }

        return UVS;
    }

    @Override
    public void reset() {

        try {

            //we need to recalculate
            CALCULATE_UVS = true;
            CALCULATE_INDICES = true;
            CALCULATE_VERTICES = true;

            //create new instance of maze
            this.maze = new Prims((getType() == Shape.Hexagon), BOARD_COLS, BOARD_ROWS);

            //create the shapes if null or a different size
            if (this.shapes == null || this.shapes.length != BOARD_ROWS || this.shapes[0].length != BOARD_COLS)
                this.shapes = new CustomShape[BOARD_ROWS][BOARD_COLS];

            //flag the rotation shape null
            setRotationShape(null);

            //reset the shapes
            for (int col = 0; col < getMaze().getCols(); col++) {
                for (int row = 0; row < getMaze().getRows(); row++) {

                    //reset if not null
                    if (getShapes()[row][col] != null)
                        getShapes()[row][col].reset();
                }
            }

        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    /**
     * Render all objects part of the board
     */
    public void render(float[] m) {

        //if these are null or not ready, we can't continue
        if (getShapes() == null)
            return;
        if (getMaze() == null)
            return;
        if (!getMaze().isGenerated())
            return;

        try {

            //set the correct texture for rendering
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

            //bind the texture that we need only once
            switch (getType()) {

                case Square:
                    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, Textures.TEXTURE_ID_SQUARE);
                    break;

                case Hexagon:
                    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, Textures.TEXTURE_ID_HEXAGON);
                    break;

                case Diamond:
                    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, Textures.TEXTURE_ID_DIAMOND);
                    break;
            }

            if (getUvs() == null || getIndices() == null || getVertices() == null) {

                //if null we need to setup the coordinates
                updateCoordinates(this);
            } else if (getRotationShape() != null) {

                //if a shape is rotating we need to update the coordinates
                updateShape(this, getRotationShape());
            }

            //only do these calculations when necessary
            if (CALCULATE_UVS) {
                getSquare().setupImage(getUvs());
                CALCULATE_UVS = false;
            }

            if (CALCULATE_INDICES) {
                getSquare().setupTriangle(getIndices());
                CALCULATE_INDICES = false;
            }

            if (CALCULATE_VERTICES) {
                getSquare().setupVertices(getVertices());
                CALCULATE_VERTICES = false;
            }

            //make a single render call to render everything
            getSquare().render(m);

        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }
}