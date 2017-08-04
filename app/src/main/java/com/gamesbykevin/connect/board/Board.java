package com.gamesbykevin.connect.board;

import android.printservice.CustomPrinterIconCallback;

import com.gamesbykevin.androidframeworkv2.base.Cell;
import com.gamesbykevin.androidframeworkv2.maze.Maze;
import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;
import com.gamesbykevin.androidframeworkv2.maze.algorithm.Prims;
import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.entity.Entity;
import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.common.ICommon;
import com.gamesbykevin.connect.opengl.Textures;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.shape.CustomShape.ANGLE_MAX;
import static com.gamesbykevin.connect.shape.CustomShape.ROTATION_ANGLE_SQUARE;

/**
 * Created by Kevin on 8/1/2017.
 */
public class Board implements ICommon {

    private CustomShape[][] shapes;

    public enum Shape {
        Square, Hexagon, Diamond
    }

    //our maze generation object
    private Prims maze;

    private Entity pipe = new Entity();

    public static final int BOARD_COLS = 5;
    public static final int BOARD_ROWS = 5;

    //base point that we will mark connected
    public static final int ANCHOR_COL = (BOARD_COLS / 2);
    public static final int ANCHOR_ROW = (BOARD_ROWS / 2);

    //our array storing what shapes are connected
    private boolean[][] tmpConnected;

    //list of locations to check
    private List<Cell> check;

    /**
     * Default constructor
     */
    public Board() throws Exception {
        //addShape(Shape.Square, 0, 50);
        //addShape(Shape.Hexagon, 0, 400);
        //addShape(Shape.Diamond, 200, 250);

        this.pipe = new Entity();
        this.pipe.setWidth(CustomShape.DEFAULT_DIMENSION);
        this.pipe.setHeight(CustomShape.DEFAULT_DIMENSION);

        reset();
    }

    public Maze getMaze() {
        return this.maze;
    }

    public final void addShape(Shape shape, Room room, float x, float y, int col, int row) {

        CustomShape tmp = new CustomShape(shape);

        tmp.setX(x);
        tmp.setY(y);

        switch (tmp.getShape()) {

            case Square:
                tmp.setTextureId(Textures.TEXTURE_ID_SQUARE);
                break;

            case Hexagon:
                tmp.setTextureId(Textures.TEXTURE_ID_HEXAGON);
                break;

            case Diamond:
                tmp.setTextureId(Textures.TEXTURE_ID_DIAMOND);
                break;

            default:
                throw new RuntimeException("Shape not defined: " + shape.toString());
        }

        if (col == ANCHOR_COL && row == ANCHOR_ROW)
            tmp.setConnected(true);

        //open up the appropriate sides
        tmp.setBorders(room);

        //make sure we render the pipe correctly
        tmp.calculateAnglePipe();

        //how many rotations have we had
        int rotations = 0;

        //pick a random number of rotations to perform
        int rotationsMax = GameActivity.getRandomObject().nextInt((int)(ANGLE_MAX / ROTATION_ANGLE_SQUARE));

        while (rotations < rotationsMax) {
            tmp.rotate();
            tmp.rotateFinish();
            rotations++;
        }

        //assign shape in array
        getShapes()[row][col] = tmp;
    }

    private CustomShape[][] getShapes() {

        if (this.shapes == null)
            this.shapes = new CustomShape[BOARD_ROWS][BOARD_COLS];

        return this.shapes;
    }

    public void touch(float x, float y) {

        for (int col = 0; col < getMaze().getCols(); col++) {
            for (int row = 0; row < getMaze().getRows(); row++) {
                CustomShape shape = getShapes()[row][col];

                if (shape != null && shape.contains(x, y) && !shape.hasRotate())
                    shape.rotate();
            }
        }
    }

    public void checkBoard() {

        //create array if not instantiated
        if (tmpConnected == null)
            tmpConnected = new boolean[getMaze().getRows()][getMaze().getCols()];

        //mark all shapes not connected at first
        for (int col = 0; col < getMaze().getCols(); col++) {
            for (int row = 0; row < getMaze().getRows(); row++) {
                tmpConnected[row][col] = false;
            }
        }

        if (this.check == null)
            this.check = new ArrayList<>();

        //add starting point
        this.check.add(new Cell(ANCHOR_COL, ANCHOR_ROW));

        //keep going as long as we have places to check
        while (!this.check.isEmpty()) {

            //get the first element
            Cell cell = this.check.get(0);

            //remove the object from the list
            this.check.remove(0);

            int col = (int)cell.getCol();
            int row = (int)cell.getRow();

            CustomShape shape = getShapes()[row][col];

            //mark the current location as connected
            tmpConnected[row][col] = true;

            //check neighbor shapes
            CustomShape shapeW = null;
            CustomShape shapeE = null;
            CustomShape shapeN = null;
            CustomShape shapeS = null;

            //get our neighbor shapes
            if (col > 0)
                shapeW = getShapes()[row][col - 1];
            if (col < BOARD_COLS - 1)
                shapeE = getShapes()[row][col + 1];
            if (row > 0)
                shapeN = getShapes()[row - 1][col];
            if (row < BOARD_ROWS - 1)
                shapeS = getShapes()[row + 1][col];

            if (shapeW != null && shapeW.hasEast() && shape.hasWest() && !tmpConnected[row][col - 1])
                this.check.add(new Cell(col - 1, row));
            if (shapeE != null && shapeE.hasWest() && shape.hasEast() && !tmpConnected[row][col + 1])
                this.check.add(new Cell(col + 1, row));
            if (shapeN != null && shapeN.hasSouth() && shape.hasNorth() && !tmpConnected[row - 1][col])
                this.check.add(new Cell(col, row - 1));
            if (shapeS != null && shapeS.hasNorth() && shape.hasSouth() && !tmpConnected[row + 1][col])
                this.check.add(new Cell(col, row + 1));
        }

        for (int col = 0; col < getMaze().getCols(); col++) {
            for (int row = 0; row < getMaze().getRows(); row++) {
                CustomShape shape = getShapes()[row][col];

                if (shape != null) {
                    shape.setConnected(tmpConnected[row][col]);
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
                    getMaze().update(GameActivity.getRandomObject());
                }

                int x = 0, y = 0;

                for (int col = 0; col < getMaze().getCols(); col++) {

                    x = 40 + (col * CustomShape.DEFAULT_DIMENSION);

                    for (int row = 0; row < getMaze().getRows(); row++) {

                        y = 30 + (row * CustomShape.DEFAULT_DIMENSION);

                        addShape(Shape.Square, getMaze().getRoom(col, row), x, y, col, row);
                    }
                }

                //highlight the connected pipes
                checkBoard();

            } else {

                boolean done = false;

                for (int col = 0; col < getMaze().getCols(); col++) {
                    for (int row = 0; row < getMaze().getRows(); row++) {

                        CustomShape shape = getShapes()[row][col];

                        if (shape == null)
                            continue;

                        if (shape.hasRotate()) {
                            shape.update(activity);

                            if (!shape.hasRotate())
                                done = true;
                        }
                    }
                }

                if (done)
                    checkBoard();
            }
            
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    @Override
    public void reset() {

        try {

            //create new instance of maze
            this.maze = new Prims(BOARD_COLS, BOARD_ROWS);

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

                pipe.setX(shape);
                pipe.setY(shape);
                pipe.setAngle(shape.getAngle() + shape.getAnglePipe());
                pipe.render(openGL);
            }
        }
    }
}