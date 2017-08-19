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
import static com.gamesbykevin.connect.board.BoardHelper.setupCoordinates;
import static com.gamesbykevin.connect.game.Game.AUTO_ROTATE;
import static com.gamesbykevin.connect.game.GameHelper.getSquare;

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

    //our maze generation object
    private Maze maze;

    private Entity entity = new Entity();

    public static final int BOARD_COLS = 15;
    public static final int BOARD_ROWS = 15;

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

    public Maze getMaze() {
        return this.maze;
    }

    private void addShapes() {

        int x = 0, y = 0;
        final int w, h;

        switch (getType()) {

            case Square:

                //assign dimensions
                w = Square.DIMENSION;
                h = Square.DIMENSION;
                CustomShape.ROTATION_ANGLE = Square.ROTATION_ANGLE_DEFAULT;
                break;

            case Hexagon:

                //assign dimensions
                w = Hexagon.DIMENSION;
                h = Hexagon.DIMENSION;
                CustomShape.ROTATION_ANGLE = Hexagon.ROTATION_ANGLE_DEFAULT;
                break;

            case Diamond:

                //assign dimensions
                w = Diamond.DIMENSION;
                h = Diamond.DIMENSION;
                CustomShape.ROTATION_ANGLE = Diamond.ROTATION_ANGLE_DEFAULT;
                break;

            default:
                throw new RuntimeException("Shape not defined: " + getType().toString());
        }

        //start coordinates
        final int sx = BoardHelper.getStartX(getType(), maze.getCols(), maze.getRows(), w, h);
        final int sy = BoardHelper.getStartY(getType(), maze.getCols(), maze.getRows(), w, h);

        for (int col = 0; col < getMaze().getCols(); col++) {

            for (int row = 0; row < getMaze().getRows(); row++) {

                //calculate coordinates
                x = sx + BoardHelper.getX(getType(), col, row, w, h);
                y = sy + BoardHelper.getY(getType(), col, row, w, h);

                //add shape
                addShape(getType(), getMaze().getRoom(col, row), x, y, col, row);
            }
        }

        //setup coordinates for open gl rendering
        getUvs();
        getVertices();
        getIndices();
    }

    public final void addShape(Shape shape, Room room, float x, float y, int col, int row) {

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

        //calculate vertices, so it is cached at first
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

        setupCoordinates(getShapes());

        //render the shape
        getSquare().render(this, m);
    }
}