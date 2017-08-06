package com.gamesbykevin.connect.board;

import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.shape.Square;

import java.util.ArrayList;
import java.util.List;

import static com.gamesbykevin.connect.board.Board.ANCHOR_COL;
import static com.gamesbykevin.connect.board.Board.ANCHOR_ROW;
import static com.gamesbykevin.connect.board.Board.BOARD_COLS;
import static com.gamesbykevin.connect.board.Board.BOARD_ROWS;
import static com.gamesbykevin.connect.game.GameHelper.GAME_OVER;

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
            Square shape = (Square)check.get(0);

            //remove the object from the list
            check.remove(0);

            int col = (int)shape.getCol();
            int row = (int)shape.getRow();

            //mark the current location as connected
            tmpConnected[row][col] = true;

            //check neighbor shapes
            Square shapeW = null;
            Square shapeE = null;
            Square shapeN = null;
            Square shapeS = null;

            //get our neighbor shapes
            if (col > 0)
                shapeW = (Square)board.getShapes()[row][col - 1];
            if (col < BOARD_COLS - 1)
                shapeE = (Square)board.getShapes()[row][col + 1];
            if (row > 0)
                shapeN = (Square)board.getShapes()[row - 1][col];
            if (row < BOARD_ROWS - 1)
                shapeS = (Square)board.getShapes()[row + 1][col];

            //if the opening is there and not already connected these are new spots to connect
            if (canConnect(shape, shapeW) && !tmpConnected[row][col - 1])
                check.add(shapeW);
            if (canConnect(shape, shapeE) && !tmpConnected[row][col + 1])
                check.add(shapeE);
            if (canConnect(shape, shapeN) && !tmpConnected[row - 1][col])
                check.add(shapeN);
            if (canConnect(shape, shapeS) && !tmpConnected[row + 1][col])
                check.add(shapeS);
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
    protected static boolean canConnect(Board board, Square shape) {

        int col = (int)shape.getCol();
        int row = (int)shape.getRow();

        //check neighbor shapes
        Square shapeW = null;
        Square shapeE = null;
        Square shapeN = null;
        Square shapeS = null;

        //get our neighbor shapes
        if (col > 0)
            shapeW = (Square)board.getShapes()[row][col - 1];
        if (col < BOARD_COLS - 1)
            shapeE = (Square)board.getShapes()[row][col + 1];
        if (row > 0)
            shapeN = (Square)board.getShapes()[row - 1][col];
        if (row < BOARD_ROWS - 1)
            shapeS = (Square)board.getShapes()[row + 1][col];

        //if any of these work, return true
        if (canConnect(shape, shapeW))
            return true;
        if (canConnect(shape, shapeE))
            return true;
        if (canConnect(shape, shapeN))
            return true;
        if (canConnect(shape, shapeS))
            return true;

        //we couldn't connect anything
        return false;
    }

    /**
     * Can this shape connect to its neighbor
     * @param shape The base shape
     * @param neighbor The neighbor we want to connect to
     * @return true if these shapes are connected, false otherwise
     */
    protected static boolean canConnect(Square shape, Square neighbor) {

        //if the neighbor doesn't exist we can't connect
        if (neighbor == null)
            return false;

        //if the shape doesn't exist we can't connect
        if (shape == null)
            return false;

        if (shape.getCol() > neighbor.getCol()) {
            if (shape.hasWest() && neighbor.hasEast())
                return true;
        } else if (shape.getCol() < neighbor.getCol()) {
            if (shape.hasEast() && neighbor.hasWest())
                return true;
        }

        if (shape.getRow() > neighbor.getRow()) {
            if (shape.hasNorth() && neighbor.hasSouth())
                return true;
        } else if (shape.getRow() < neighbor.getRow()) {
            if (shape.hasSouth() && neighbor.hasNorth())
                return true;
        }

        //we don't have a connection
        return false;
    }
}