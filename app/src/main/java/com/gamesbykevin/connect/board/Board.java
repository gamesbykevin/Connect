package com.gamesbykevin.connect.board;

import android.opengl.GLES20;

import com.gamesbykevin.androidframeworkv2.maze.Maze;
import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.algorithm.Prims;
import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.entity.Entity;
import com.gamesbykevin.connect.opengl.Textures;
import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.common.ICommon;
import com.gamesbykevin.connect.shape.Diamond;
import com.gamesbykevin.connect.shape.Hexagon;
import com.gamesbykevin.connect.shape.Square;

import static com.gamesbykevin.connect.activity.GameActivity.getRandomObject;
import static com.gamesbykevin.connect.board.BoardHelper.checkBoard;
import static com.gamesbykevin.connect.board.BoardHelper.updateCoordinates;
import static com.gamesbykevin.connect.board.BoardHelper.updateShape;
import static com.gamesbykevin.connect.game.Game.AUTO_ROTATE;
import static com.gamesbykevin.connect.game.GameHelper.getSquare;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;

/**
 * Created by Kevin on 8/1/2017.
 */
public class Board implements ICommon {

    //store these coordinates for rendering
    protected static float[] VERTICES;
    protected static short[] INDICES;
    protected static float[] UVS;

    //array of shapes on the board
    private CustomShape[][] shapes;

    public enum Shape {
        Square, Hexagon, Diamond
    }

    //what type of shape are we using
    private Shape type = null;

    /**
     * The size of the shape
     */
    public static int DIMENSION = 64;

    //our maze generation object
    private Maze maze;

    private Entity entity = new Entity();

    public static final int BOARD_COLS = 10;
    public static final int BOARD_ROWS = 10;

    //base point that we will mark connected
    public static final int ANCHOR_COL = (BOARD_COLS / 2);
    public static final int ANCHOR_ROW = (BOARD_ROWS / 2);

    //the current rotating shape
    private CustomShape rotationShape = null;

    /**
     * Default constructor
     */
    public Board() throws Exception {
        //do we need to do anything here?
    }

    /**
     * Get the width of the entire board
     * @return The pixel width of the entire board
     */
    public float getWidth() {

        //2 shapes to calculate the difference
        CustomShape shape1, shape2;

        //get the appropriate shapes based on the type
        switch(getType()) {

            case Square:
                shape1 = getShapes()[0][0];
                shape2 = getShapes()[0][getMaze().getCols() - 1];
                break;

            case Diamond:
                shape1 = getShapes()[getMaze().getRows() - 1][0];
                shape2 = getShapes()[0][getMaze().getCols() - 1];
                break;

            case Hexagon:
                shape1 = getShapes()[0][0];
                shape2 = getShapes()[1][getMaze().getCols() - 1];
                break;

            default:
                throw new RuntimeException("Type not defined: " + getType().toString());
        }

        //get the difference
        return (shape2.getX() + shape2.getWidth()) - shape1.getX();
    }

    /**
     * Get the height of the entire board
     * @return The pixel height of the entire board
     */
    public float getHeight() {

        //2 shapes to calculate the difference
        CustomShape shape1, shape2;

        //get the appropriate shapes based on the type
        switch(getType()) {

            case Square:
                shape1 = getShapes()[0][0];
                shape2 = getShapes()[getMaze().getRows() - 1][0];
                break;

            case Diamond:
                shape1 = getShapes()[0][0];
                shape2 = getShapes()[getMaze().getRows() - 1][getMaze().getCols() - 1];
                break;

            case Hexagon:
                shape1 = getShapes()[0][0];
                shape2 = getShapes()[getMaze().getRows() - 1][0];
                break;

            default:
                throw new RuntimeException("Type not defined: " + getType().toString());
        }

        //get the difference
        return (shape2.getY() + shape2.getHeight()) - shape1.getY();
    }

    public Maze getMaze() {
        return this.maze;
    }

    private void addShapes() {

        int x = 0, y = 0;
        final int w = DIMENSION, h = DIMENSION;

        switch (getType()) {

            case Square:
                CustomShape.ROTATION_ANGLE = Square.ROTATION_ANGLE_DEFAULT;
                break;

            case Hexagon:
                CustomShape.ROTATION_ANGLE = Hexagon.ROTATION_ANGLE_DEFAULT;
                break;

            case Diamond:
                CustomShape.ROTATION_ANGLE = Diamond.ROTATION_ANGLE_DEFAULT;
                break;

            default:
                throw new RuntimeException("Shape not defined: " + getType().toString());
        }

        //start coordinates
        final int sx = BoardHelper.getStartX(getType(), WIDTH, maze.getCols(), maze.getRows(), w, h);
        final int sy = BoardHelper.getStartY(getType(), HEIGHT, maze.getCols(), maze.getRows(), w, h);

        int index = 0;

        for (int col = 0; col < getMaze().getCols(); col++) {

            for (int row = 0; row < getMaze().getRows(); row++) {

                //calculate coordinates
                x = sx + BoardHelper.getX(getType(), col, row, w, h);
                y = sy + BoardHelper.getY(getType(), col, row, w, h);

                //add shape
                addShape(getType(), getMaze().getRoom(col, row), x, y, col, row, index);

                //keep track of index
                index++;
            }
        }
    }

    public final void addShape(Shape shape, Room room, float x, float y, int col, int row, int index) {

        CustomShape tmp = null;

        switch (shape) {

            case Square:
                tmp = new Square();
                break;

            case Hexagon:
                tmp = new Hexagon();
                break;

            case Diamond:
                tmp = new Diamond();
                break;

            default:
                throw new RuntimeException("Shape not defined: " + shape.toString());
        }

        //assign x, y coordinates
        tmp.setX(x);
        tmp.setY(y);

        //assign the index
        tmp.setIndex(index);

        //mark the anchor shape as connected
        if (col == ANCHOR_COL && row == ANCHOR_ROW)
            tmp.setConnected(true);

        //open up the appropriate sides
        tmp.setBorders(room);

        //assign the location
        tmp.setCol(col);
        tmp.setRow(row);

        //make sure we render the pipe(s) correctly
        tmp.calculateAnglePipe();

        //assign the texture coordinates for the pipe on the shape
        tmp.assignTextureCoordinates();

        //how many rotations have we had
        int rotations = 0;

        //pick a random number of rotations to perform
        int rotationsMax = getRandomObject().nextInt(tmp.getRotationCountMax());

        //rotate the shape a random number of times
        while (rotations < rotationsMax) {
            tmp.rotate();
            tmp.rotateFinish();
            rotations++;
        }

        //update the vertices
        tmp.updateVertices();

        //assign shape in array
        getShapes()[row][col] = tmp;
    }

    protected CustomShape[][] getShapes() {

        if (this.shapes == null)
            this.shapes = new CustomShape[BOARD_ROWS][BOARD_COLS];

        return this.shapes;
    }

    /**
     * Touch the board and rotate the selected shape
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void touch(float x, float y) {

        //if we are already rotating don't check again
        if (getRotationShape() != null)
            return;

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

                    //no need to continue checking
                    row = getMaze().getRows();
                    col = getMaze().getCols();
                    break;
                }
            }
        }
    }

    @Override
    public void dispose() {

        if (getShapes() != null) {

            for (int col = 0; col < getMaze().getCols(); col++) {
                for (int row = 0; row < getMaze().getRows(); row++) {

                    CustomShape shape = getShapes()[row][col];

                    if (shape != null) {
                        shape.dispose();
                        shape = null;
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
                addShapes();

                //highlight the connected pipes
                checkBoard(this);

            } else {

                if (getRotationShape() != null) {

                    //update the rotation
                    getRotationShape().update(activity);

                    //if we still need to rotate don't go any further
                    if (getRotationShape().hasRotate())
                        return;

                    //make sure the vertices are updates
                    getRotationShape().updateVertices();

                    //if magnet is enabled, check if we still need to rotate
                    if (AUTO_ROTATE) {

                        //if we can't connect and the magnet is enabled, what do we do?
                        if (!BoardHelper.canConnect(this, getRotationShape())) {
                            if (getRotationShape().getRotationCount() >= getRotationShape().getRotationCountMax()) {
                                checkBoard(this);
                                setRotationShape(null);
                            } else {
                                getRotationShape().rotate();
                            }
                        } else {
                            checkBoard(this);
                            setRotationShape(null);
                        }
                    } else {
                        checkBoard(this);
                        setRotationShape(null);
                    }
                }
            }
            
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    private CustomShape getRotationShape() {
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
        return VERTICES;
    }

    public short[] getIndices() {
        return INDICES;
    }

    public float[] getUvs() {
        return UVS;
    }

    @Override
    public void reset() {

        try {

            //create new instance of maze
            this.maze = new Prims((getType() == Shape.Hexagon), BOARD_COLS, BOARD_ROWS);

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

        //if these are null we can't continue
        if (getShapes() == null)
            return;
        if (getMaze() == null)
            return;

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
            updateCoordinates(getShapes());
        } else if (getRotationShape() != null) {
            updateShape(getRotationShape());
        }

        //make a single call to render all shapes
        getSquare().render(this, m);
    }
}