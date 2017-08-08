package com.gamesbykevin.connect.board;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.shape.Square;

import java.util.ArrayList;
import java.util.List;

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

    //list of locations to check
    private static List<CustomShape> check;

    //our array storing what shapes are connected
    private static boolean[][] tmpConnected;

    /**
     * Make sure all connected pieces are highlighted.<br>
     * If all are connected, the game is over
     */
    public static void checkBoard(Board board) {

        //create array if not instantiated
        if (tmpConnected == null)
            tmpConnected = new boolean[board.getMaze().getRows()][board.getMaze().getCols()];

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

        //if solved the game is over
        GAME_OVER = solved;
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

    protected static int getStartX(Board.Shape type, final int cols, final int rows, final int w, final int h) {

        //middle coordinate
        final int mx = (WIDTH / 2);

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

    protected static int getStartY(Board.Shape type, final int cols, final int rows, final int w, final int h) {

        //middle coordinate
        final int my = (HEIGHT / 2);

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
     * Mark the shapes on the board visible...
     * @param board The board containing our shapes
     * @param visible Do we want the shape background to be visible
     */
    public static void setVisible(final Board board, final boolean visible) {
        for (int col = 0; col < board.getShapes()[0].length; col++) {
            for (int row = 0; row < board.getShapes().length; row++) {
                board.getShapes()[row][col].setVisible(visible);
            }
        }
    }
}