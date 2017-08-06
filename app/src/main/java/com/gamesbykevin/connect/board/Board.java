package com.gamesbykevin.connect.board;

import com.gamesbykevin.androidframeworkv2.maze.Maze;
import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.algorithm.Prims;
import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.entity.Entity;
import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.common.ICommon;
import com.gamesbykevin.connect.opengl.Textures;
import com.gamesbykevin.connect.shape.Hexagon;
import com.gamesbykevin.connect.shape.Square;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.activity.GameActivity.getRandomObject;
import static com.gamesbykevin.connect.board.BoardHelper.checkBoard;

/**
 * Created by Kevin on 8/1/2017.
 */
public class Board implements ICommon {

    private CustomShape[][] shapes;

    public enum Shape {
        Square, Hexagon, Diamond
    }

    //what type of shape are we using
    private Shape type = Shape.Square;

    //our maze generation object
    private Prims maze;

    private Entity pipe = new Entity();

    public static final int BOARD_COLS = 5;
    public static final int BOARD_ROWS = 10;

    //base point that we will mark connected
    public static final int ANCHOR_COL = (BOARD_COLS / 2);
    public static final int ANCHOR_ROW = (BOARD_ROWS / 2);

    //do we rotate until we connect to something?
    private boolean magnet = false;

    public static final int START_X = 30;
    public static final int START_Y = 25;

    /**
     * Default constructor
     */
    public Board() throws Exception {
        reset();
    }

    public Maze getMaze() {
        return this.maze;
    }

    private void addShapes(Shape shape) {

        setType(shape);

        int x = 0, y = 0;
        int w = 0; int h = 0;

        switch (getType()) {

            case Square:
                w = Square.DIMENSION;
                h = Square.DIMENSION;
                break;

            case Hexagon:
                w = Hexagon.DIMENSION;
                h = Hexagon.DIMENSION;
                break;

            default:
                throw new RuntimeException("Shape not defined: " + shape.toString());
        }

        for (int col = 0; col < getMaze().getCols(); col++) {

            for (int row = 0; row < getMaze().getRows(); row++) {

                int startX = START_X + (int)(col * (w * 1.5));

                switch (shape) {

                    case Square:

                        //calculate coordinates
                        x = START_X + (col * w);
                        y = START_Y + (row * h);
                        break;

                    case Hexagon:

                        //calculate coordinates
                        x = startX;
                        y = START_Y + (row * (h / 2));

                        //offset the odd/even rows
                        if (row % 2 != 0) {
                            x += 64;
                        } else {
                            //x += 128;
                        }
                        break;

                    default:
                        throw new RuntimeException("Shape not defined: " + shape.toString());
                }

                addShape(shape, getMaze().getRoom(col, row), x, y, col, row);
            }
        }
    }

    public final void addShape(Shape shape, Room room, float x, float y, int col, int row) {

        CustomShape tmp = null;

        switch (shape) {

            case Square:
                tmp = new Square();
                tmp.setTextureId(Textures.TEXTURE_ID_SQUARE);
                break;

            case Hexagon:
                tmp = new Hexagon();
                tmp.setTextureId(Textures.TEXTURE_ID_HEXAGON);
                break;

            case Diamond:
                tmp.setTextureId(Textures.TEXTURE_ID_DIAMOND);
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

        for (int col = 0; col < getMaze().getCols(); col++) {
            for (int row = 0; row < getMaze().getRows(); row++) {
                CustomShape shape = getShapes()[row][col];

                if (shape != null && shape.contains(x, y) && !shape.hasRotate()) {
                    shape.setRotationCount(0);
                    shape.rotate();
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
                addShapes(Shape.Hexagon);

                //highlight the connected pipes
                checkBoard(this);

            } else {

                boolean done = false;

                for (int col = 0; col < getMaze().getCols(); col++) {
                    for (int row = 0; row < getMaze().getRows(); row++) {

                        CustomShape shape = getShapes()[row][col];

                        if (shape == null)
                            continue;

                        //ignore this shape if it doesn't need to rotate
                        if (!shape.hasRotate())
                            continue;

                        //update rotation
                        shape.update(activity);

                        //if we still need to rotate continue
                        if (shape.hasRotate())
                            continue;

                        //if magnet is enabled, check if we still need to rotate
                        if (magnet) {

                            //if we can't connect and the magnet is enabled, what do we do?
                            if (!BoardHelper.canConnect(this, shape)) {
                                if (shape.getRotationCount() >= shape.getRotationCountMax()) {
                                    done = true;
                                } else {
                                    shape.rotate();
                                    done = false;
                                }
                            } else {
                                done = true;
                            }

                        } else {
                            done = true;
                        }
                    }
                }

                if (done)
                    checkBoard(this);
            }
            
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    public void setType(final Shape type) {
        this.type = type;
    }

    public Shape getType() {
        return this.type;
    }

    @Override
    public void reset() {

        try {

            //create new instance of maze
            this.maze = new Prims(true, BOARD_COLS, BOARD_ROWS);

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
     * @param openGL
     */
    @Override
    public void render(GL10 openGL) {

        if (getShapes() == null)
            return;

        for (int col = 0; col < getMaze().getCols(); col++) {
            for (int row = 0; row < getMaze().getRows(); row++) {

                CustomShape shape = getShapes()[row][col];

                if (shape == null || pipe == null)
                    return;

                shape.render(openGL);

                //assign the pipe texture id
                pipe.setTextureId(shape.getTextureIdPipe());

                //render the pipe as well
                pipe.setX(shape);
                pipe.setY(shape);
                pipe.setWidth(shape);
                pipe.setHeight(shape);
                pipe.setAngle(shape.getAngle() + shape.getAnglePipe());
                pipe.render(openGL);
            }
        }
    }
}