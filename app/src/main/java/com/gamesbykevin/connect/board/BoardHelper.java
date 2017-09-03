package com.gamesbykevin.connect.board;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.connect.util.UtilityHelper;
import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.shape.Diamond;
import com.gamesbykevin.connect.shape.Hexagon;
import com.gamesbykevin.connect.shape.Square;

import java.util.ArrayList;
import java.util.List;

import static com.gamesbykevin.connect.activity.GameActivity.getRandomObject;
import static com.gamesbykevin.connect.board.Board.ANCHOR_COL;
import static com.gamesbykevin.connect.board.Board.ANCHOR_ROW;
import static com.gamesbykevin.connect.board.Board.BOARD_COLS;
import static com.gamesbykevin.connect.board.Board.BOARD_ROWS;
import static com.gamesbykevin.connect.game.GameHelper.GAME_OVER;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.HEIGHT;
import static com.gamesbykevin.connect.opengl.OpenGLSurfaceView.WIDTH;

/**
 * Created by Kevin on 8/5/2017.
 */
public class BoardHelper {

    /**
     * The size of a single shape
     */
    public static int DIMENSION = 64;

    //list of locations to check
    private static List<CustomShape> check;

    //our array storing what shapes are connected
    private static boolean[][] tmpConnected;

    //only calculate when we need to
    protected static boolean CALCULATE_UVS = true, CALCULATE_INDICES = true, CALCULATE_VERTICES = true;

    public static boolean SOUND_ROTATE = false, SOUND_ROTATE_CONNECT = false;

    protected static float getWidth(Board board) {

        //2 shapes to calculate the difference
        CustomShape shape1, shape2;

        //get the appropriate shapes based on the type
        switch(board.getType()) {

            case Square:
                shape1 = board.getShapes()[0][0];
                shape2 = board.getShapes()[0][board.getMaze().getCols() - 1];
                break;

            case Diamond:
                shape1 = board.getShapes()[board.getMaze().getRows() - 1][0];
                shape2 = board.getShapes()[0][board.getMaze().getCols() - 1];
                break;

            case Hexagon:
                shape1 = board.getShapes()[0][0];
                shape2 = board.getShapes()[1][board.getMaze().getCols() - 1];
                break;

            default:
                throw new RuntimeException("Type not defined: " + board.getType().toString());
        }

        //get the difference
        return (shape2.getX() + shape2.getWidth()) - shape1.getX();
    }

    protected static float getHeight(Board board) {

        //2 shapes to calculate the difference
        CustomShape shape1, shape2;

        //get the appropriate shapes based on the type
        switch(board.getType()) {

            case Square:
                shape1 = board.getShapes()[0][0];
                shape2 = board.getShapes()[board.getMaze().getRows() - 1][0];
                break;

            case Diamond:
                shape1 = board.getShapes()[0][0];
                shape2 = board.getShapes()[board.getMaze().getRows() - 1][board.getMaze().getCols() - 1];
                break;

            case Hexagon:
                shape1 = board.getShapes()[0][0];
                shape2 = board.getShapes()[board.getMaze().getRows() - 1][0];
                break;

            default:
                throw new RuntimeException("Type not defined: " + board.getType().toString());
        }

        //get the difference
        return (shape2.getY() + shape2.getHeight()) - shape1.getY();
    }

    /**
     * Check the board to see if all shapes are connected
     * @param board The board containing all our shapes
     * @param checkGameOver Do we want to check if the board is solved?
     */
    protected static void checkBoard(Board board, boolean checkGameOver) {

        //create array if not instantiated or if the size doesn't match
        if (tmpConnected == null || tmpConnected.length != board.getMaze().getRows() || tmpConnected[0].length != board.getMaze().getCols()) {
            tmpConnected = new boolean[board.getMaze().getRows()][board.getMaze().getCols()];

            //flag to recalculate
            CALCULATE_VERTICES = true;
            CALCULATE_INDICES = true;
            CALCULATE_UVS = true;
        }

        //mark all shapes not connected at first
        for (int col = 0; col < board.getMaze().getCols(); col++) {
            for (int row = 0; row < board.getMaze().getRows(); row++) {
                tmpConnected[row][col] = false;
            }
        }

        if (check == null)
            check = new ArrayList<>();

        //add starting point
        check.add(board.getShapes()[ANCHOR_ROW][ANCHOR_COL]);

        //keep going as long as we have places to check
        while (!check.isEmpty()) {

            //get the first shape
            CustomShape shape = check.get(0);

            //remove the object from the list
            check.remove(0);

            int col = (int)shape.getCol();
            int row = (int)shape.getRow();

            //mark the current location as connected
            tmpConnected[row][col] = true;

            //check all neighbors
            for (int i = 0; i < Room.getAllWalls(board.getType() == Board.Shape.Hexagon).size(); i++) {
                Room.Wall wall = Room.getAllWalls(board.getType() == Board.Shape.Hexagon).get(i);

                int tmpCol = wall.getCol();
                int tmpRow = wall.getRow();

                if (board.getType() == Board.Shape.Hexagon) {
                    switch (wall) {
                        case NorthWest:
                            if (row % 2 == 0) {
                                tmpCol = -1;
                            } else {
                                tmpCol = 0;
                            }
                            break;

                        case NorthEast:
                            if (row % 2 == 0) {
                                tmpCol = 0;
                            } else {
                                tmpCol = 1;
                            }
                            break;

                        case SouthWest:
                            if (row % 2 == 0) {
                                tmpCol = -1;
                            } else {
                                tmpCol = 0;
                            }
                            break;

                        case SouthEast:
                            if (row % 2 == 0) {
                                tmpCol = 0;
                            } else {
                                tmpCol = 1;
                            }
                            break;
                    }
                }

                //stay in bounds
                if (row + tmpRow < 0 || row + tmpRow >= BOARD_ROWS)
                    continue;
                if (col + tmpCol < 0 || col + tmpCol >= BOARD_COLS)
                    continue;

                //don't check this shape again if it is already connected
                if (tmpConnected[row + tmpRow][col + tmpCol])
                    continue;

                //get the shape at the location
                CustomShape tmpShape = board.getShapes()[row + tmpRow][col + tmpCol];

                //if the shapes can connect
                if (canConnect(shape, tmpShape, board.getType()))
                    check.add(tmpShape);
            }
        }

        //did we solve the board
        boolean solved = true;

        //connect all shapes accordingly and check if the game is over
        for (int col = 0; col < board.getMaze().getCols(); col++) {
            for (int row = 0; row < board.getMaze().getRows(); row++) {

                CustomShape shape = board.getShapes()[row][col];

                if (shape != null) {

                    //mark the correct connection setting
                    shape.setConnected(tmpConnected[row][col]);

                    //if not connected the board is not solved
                    if (!tmpConnected[row][col])
                        solved = false;
                } else {
                    solved = false;
                }
            }
        }

        //update the coordinates for everything
        updateCoordinates(board);

        //if this was the users decision assign Game over value
        if (checkGameOver) {

            if (board.getRotationShape() != null) {

                int col = (int)board.getRotationShape().getCol();
                int row = (int)board.getRotationShape().getRow();

                //which sound effect do we play?
                SOUND_ROTATE = !(tmpConnected[row][col]);
                SOUND_ROTATE_CONNECT = (tmpConnected[row][col]);
            }

            //is the game over
            GAME_OVER = solved;
        }
    }

    /**
     * Can this shape connect to any neighbor
     * @param shape The base shape
     * @return true if we can connect to any neighbor, false otherwise
     */
    protected static boolean canConnect(Board board, CustomShape shape) {

        int col = (int)shape.getCol();
        int row = (int)shape.getRow();

        //check all neighbors
        for (int i = 0; i < Room.getAllWalls(board.getType() == Board.Shape.Hexagon).size(); i++) {
            Room.Wall wall = Room.getAllWalls(board.getType() == Board.Shape.Hexagon).get(i);

            int tmpCol = wall.getCol();
            int tmpRow = wall.getRow();

            if (board.getType() == Board.Shape.Hexagon) {
                switch (wall) {
                    case NorthWest:
                        if (row % 2 == 0) {
                            tmpCol = -1;
                        } else {
                            tmpCol = 0;
                        }
                        break;

                    case NorthEast:
                        if (row % 2 == 0) {
                            tmpCol = 0;
                        } else {
                            tmpCol = 1;
                        }
                        break;

                    case SouthWest:
                        if (row % 2 == 0) {
                            tmpCol = -1;
                        } else {
                            tmpCol = 0;
                        }
                        break;

                    case SouthEast:
                        if (row % 2 == 0) {
                            tmpCol = 0;
                        } else {
                            tmpCol = 1;
                        }
                        break;
                }
            }

            //stay in bounds
            if (row + tmpRow < 0 || row + tmpRow >= BOARD_ROWS)
                continue;
            if (col + tmpCol < 0 || col + tmpCol >= BOARD_COLS)
                continue;

            //get the shape at the location
            CustomShape tmpShape = board.getShapes()[row + tmpRow][col + tmpCol];

            //if the shapes can connect return true
            if (canConnect(shape, tmpShape, board.getType()))
                return true;
        }

        //we couldn't connect anything
        return false;
    }

    protected static boolean canConnect(CustomShape shape, CustomShape neighbor, Board.Shape type) {

        //if the neighbor doesn't exist we can't connect
        if (neighbor == null)
            return false;

        //if the shape doesn't exist we can't connect
        if (shape == null)
            return false;

        if (type != Board.Shape.Hexagon) {
            if (shape.getCol() > neighbor.getCol()) {
                if (shape.hasWest() && neighbor.hasEast())
                    return true;    //WEST
            } else if (shape.getCol() < neighbor.getCol()) {
                if (shape.hasEast() && neighbor.hasWest())
                    return true;    //EAST
            }

            if (shape.getRow() > neighbor.getRow()) {
                if (shape.hasNorth() && neighbor.hasSouth())
                    return true;    //NORTH
            } else if (shape.getRow() < neighbor.getRow()) {
                if (shape.hasSouth() && neighbor.hasNorth())
                    return true;    //SOUTH
            }

        } else {

            //north east
            if (shape.getRow() > neighbor.getRow()) {
                if (shape.getRow() % 2 == 0) {
                    if (shape.getCol() == neighbor.getCol()) {
                        if (shape.hasNorthEast() && neighbor.hasSouthWest())
                            return true;
                    }
                } else {
                    if (shape.getCol() < neighbor.getCol()) {
                        if (shape.hasNorthEast() && neighbor.hasSouthWest())
                            return true;
                    }
                }
            }

            //south east
            if (shape.getRow() < neighbor.getRow()) {
                if (shape.getRow() % 2 == 0) {
                    if (shape.getCol() == neighbor.getCol()) {
                        if (shape.hasSouthEast() && neighbor.hasNorthWest())
                            return true;
                    }
                } else {
                    if (shape.getCol() < neighbor.getCol()) {
                        if (shape.hasSouthEast() && neighbor.hasNorthWest())
                            return true;
                    }
                }
            }

            //west
            if (shape.getRow() == neighbor.getRow() && shape.getCol() > neighbor.getCol()) {
                if (shape.hasWest() && neighbor.hasEast())
                    return true;
            }

            //east
            if (shape.getRow() == neighbor.getRow() && shape.getCol() < neighbor.getCol()) {
                if (shape.hasEast() && neighbor.hasWest())
                    return true;
            }

            //north west
            if (shape.getRow() > neighbor.getRow()) {
                if (shape.getRow() % 2 == 0) {
                    if (shape.getCol() > neighbor.getCol()) {
                        if (shape.hasNorthWest() && neighbor.hasSouthEast())
                            return true;
                    }
                } else {
                    if (shape.getCol() == neighbor.getCol()) {
                        if (shape.hasNorthWest() && neighbor.hasSouthEast())
                            return true;
                    }
                }
            }

            //south west
            if (shape.getRow() < neighbor.getRow()) {
                if (shape.getRow() % 2 == 0) {
                    if (shape.getCol() > neighbor.getCol()) {
                        if (shape.hasSouthWest() && neighbor.hasNorthEast())
                            return true;
                    }
                } else {
                    if (shape.getCol() == neighbor.getCol()) {
                        if (shape.hasSouthWest() && neighbor.hasNorthEast())
                            return true;
                    }
                }
            }
        }

        //we don't have a connection
        return false;
    }

    protected static int getX(Board.Shape type, final int col, final int row, final int w, final int h) {
        switch(type) {

            case Square:
                return (col * w);

            case Hexagon:

                //offset the odd rows
                if (row % 2 != 0) {
                    return (int)(col * (w * .875) + (w * .45));
                } else {
                    return (int)(col * (w * .875));
                }

            case Diamond:
                return (col * (w / 2)) - (row * (w / 2));

            default:
                throw new RuntimeException("Type not handled here: " + type.toString());
        }
    }

    protected static int getY(Board.Shape type, final int col, final int row, final int w, final int h) {
        switch(type) {

            case Square:
                return (row * h);

            case Hexagon:
                return (int)(row * (h * .75));

            case Diamond:
                return (col * (h / 2)) + (row * (h / 2));

            default:
                throw new RuntimeException("Type not handled here: " + type.toString());
        }
    }

    public static int getStartX(Board board, int screenWidth) {
        return getStartX(board.getType(), screenWidth, board.getMaze().getCols(), board.getMaze().getRows(), DIMENSION, DIMENSION);
    }

    protected static int getStartX(Board.Shape type, int screenWidth, int cols, int rows, int w, int h) {

        //middle coordinate
        final int mx = (screenWidth / 2);

        switch(type) {

            case Square:
                return mx - ((cols * w) / 2);

            case Hexagon:
                return mx - ((cols * w) / 2);

            case Diamond:
                return mx + (((rows - cols) * (w / 2)) / 2) - (w / 2);

            default:
                throw new RuntimeException("Type not handled here: " + type.toString());
        }
    }

    public static int getStartY(Board board, int screenHeight) {
        return getStartY(board.getType(), screenHeight, board.getMaze().getCols(), board.getMaze().getRows(), DIMENSION, DIMENSION);
    }

    protected static int getStartY(Board.Shape type, int screenHeight, int cols, int rows, int w, int h) {

        //middle coordinate
        final int my = (screenHeight / 2);

        switch(type) {

            case Square:
                return my - ((rows * h) / 2);

            case Hexagon:
                return my - ((rows * h) / 2);

            case Diamond:
                return my - ((rows * (h / 2)) + (cols * (w / 2))) / 2;

            default:
                throw new RuntimeException("Type not handled here: " + type.toString());
        }
    }

    /**
     * Setup the coordinates for open gl rendering
     */
    protected static void updateCoordinates(Board board) {

        for (int col = 0; col < board.getShapes()[0].length; col++) {
            for (int row = 0; row < board.getShapes().length; row++) {

                try {

                    //get the current shape
                    CustomShape shape = board.getShapes()[row][col];

                    if (shape == null)
                        continue;

                    //update shape coordinates
                    updateShape(board, shape);

                } catch (Exception e) {
                    UtilityHelper.handleException(e);
                }
            }
        }

        //make sure our indices are created
        board.getIndices();
    }

    protected static void updateShapeVertices(Board board, CustomShape shape) {

        //if rotating update vertices
        if (shape.hasRotate())
            shape.updateVertices();

        //flag to recalculate
        CALCULATE_VERTICES = true;

        //assign vertices
        for (int i = 0; i < shape.getVertices().length; i++) {

            int index = (shape.getIndex() * 12) + i;

            if (index >= board.getVertices().length)
                return;

            board.getVertices()[index] = shape.getVertices()[i];
        }
    }

    protected static void updateShapeUvs(Board board, CustomShape shape) {

        //flag to recalculate
        CALCULATE_UVS = true;

        //which portion of the texture are we rendering
        for (int i = 0; i < shape.getTextureCoordinates().length; i++) {

            int index = (shape.getIndex() * 8) + i;

            if (index >= board.getUvs().length)
                return;

            board.getUvs()[index] = shape.getTextureCoordinates()[i];
        }
    }

    /**
     * Update the UVS and  Vertices coordinates
     * @param board The board containing the render coordinates
     * @param shape Current desired shape we want to update
     */
    protected static void updateShape(Board board, CustomShape shape) {

        updateShapeVertices(board, shape);
        updateShapeUvs(board, shape);
    }

    protected static void addShapes(final Board board) {

        int x = 0, y = 0;
        final int w = DIMENSION, h = DIMENSION;

        switch (board.getType()) {

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
                throw new RuntimeException("Shape not defined: " + board.getType().toString());
        }

        //start coordinates
        final int sx = BoardHelper.getStartX(board.getType(), WIDTH, board.getMaze().getCols(), board.getMaze().getRows(), w, h);
        final int sy = BoardHelper.getStartY(board.getType(), HEIGHT, board.getMaze().getCols(), board.getMaze().getRows(), w, h);

        int index = 0;

        for (int col = 0; col < board.getMaze().getCols(); col++) {

            for (int row = 0; row < board.getMaze().getRows(); row++) {

                //calculate coordinates
                x = sx + BoardHelper.getX(board.getType(), col, row, w, h);
                y = sy + BoardHelper.getY(board.getType(), col, row, w, h);

                //add shape
                addShape(board, board.getMaze().getRoom(col, row), x, y, col, row, index);

                //keep track of index
                index++;
            }
        }
    }

    private static void addShape(Board board, Room room, float x, float y, int col, int row, int index) {

        CustomShape tmp = null;

        switch (board.getType()) {

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
                throw new RuntimeException("Shape not defined: " + board.getType().toString());
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

        //pick a random number of rotations to perform so no matter how we rotate it never start at the correct rotation
        int rotationsMax = getRandomObject().nextInt(tmp.getRotationCountMax() - 1) + 1;

        //rotate the shape a random number of times
        while (rotations < rotationsMax) {
            tmp.rotate();
            tmp.rotateFinish();
            rotations++;
        }

        //update the vertices
        tmp.updateVertices();

        //assign shape in array
        board.getShapes()[row][col] = tmp;
    }

    /**
     * Rotate all shapes on the board at their current position
     * @param board The board containing all the shapes
     */
    protected static void rotate(final Board board) {

        for (int col = 0; col < board.getShapes()[0].length; col++) {

            for (int row = 0; row < board.getShapes().length; row++) {

                //get the current shape
                CustomShape tmp = board.getShapes()[row][col];

                //how many rotations have we had
                int rotations = 0;

                //pick a random number of rotations to perform so no matter how we rotate it never start at the correct rotation
                int rotationsMax = getRandomObject().nextInt(tmp.getRotationCountMax() - 1) + 1;

                //rotate the shape a random number of times
                while (rotations < rotationsMax) {
                    tmp.rotate();
                    tmp.rotateFinish();
                    rotations++;
                }

                //update the vertices
                tmp.updateVertices();
            }
        }

        checkBoard(board, false);
    }
}