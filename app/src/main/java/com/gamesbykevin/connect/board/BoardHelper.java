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
            CustomShape shape = check.get(0);

            //remove the object from the list
            check.remove(0);

            int col = (int)shape.getCol();
            int row = (int)shape.getRow();

            //mark the current location as connected
            tmpConnected[row][col] = true;

            //check the neighbors around the current shape
            for (int tmpCol = -1; tmpCol <= 1; tmpCol++) {
                for (int tmpRow = -1; tmpRow <= 1; tmpRow++) {

                    //don't check self
                    if (tmpCol == 0 && tmpRow == 0)
                        continue;

                    //if not hexagon, don't check diagonal
                    if (board.getType() != Board.Shape.Hexagon) {
                        if (tmpCol != 0 && tmpRow != 0)
                            continue;
                    }

                    //stay in bounds
                    if (row + tmpRow < 0 || row + tmpRow >= BOARD_ROWS)
                        continue;
                    if (col + tmpCol < 0 || col + tmpCol >= BOARD_COLS)
                        continue;

                    //get the shape at the location
                    CustomShape tmpShape = board.getShapes()[row + tmpRow][col + tmpCol];

                    //if we can connect, add to list
                    if (canConnect(shape, tmpShape, board.getType()) && !tmpConnected[row + tmpRow][col + tmpCol])
                        check.add(tmpShape);
                }
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

        for (int tmpCol = -1; tmpCol <= 1; tmpCol++) {
            for (int tmpRow = -1; tmpRow <= 1; tmpRow++) {

                //don't check self
                if (tmpCol == 0 && tmpRow == 0)
                    continue;

                //if not hexagon, don't check diagonal
                if (board.getType() != Board.Shape.Hexagon) {
                    if (tmpCol != 0 && tmpRow != 0)
                        continue;
                }

                //stay in bounds
                if (row + tmpRow < 0 || row + tmpRow >= BOARD_ROWS)
                    continue;
                if (col + tmpCol < 0 || col + tmpCol >= BOARD_COLS)
                    continue;

                //get the shape at the location
                CustomShape tmpShape = board.getShapes()[row + tmpRow][col + tmpCol];

                if (canConnect(shape, tmpShape, board.getType()))
                    return true;
            }
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

        //check these if the shape is a hexagon
        if (type == Board.Shape.Hexagon) {
            if (shape.getRow() > neighbor.getRow() && shape.getCol() > neighbor.getCol()) {
                if (shape.hasNorth() && neighbor.hasSouth() && shape.hasWest() && neighbor.hasEast())
                    return true;    //NORTH WEST
            } else if (shape.getRow() > neighbor.getRow() && shape.getCol() < neighbor.getCol()) {
                if (shape.hasNorth() && neighbor.hasSouth() && shape.hasEast() && neighbor.hasWest())
                    return true;    //NORTH EAST
            }

            if (shape.getRow() < neighbor.getRow() && shape.getCol() > neighbor.getCol()) {
                if (shape.hasSouth() && neighbor.hasNorth() && shape.hasWest() && neighbor.hasEast())
                    return true;    //SOUTH WEST
            } else if (shape.getRow() < neighbor.getRow() && shape.getCol() < neighbor.getCol()) {
                if (shape.hasSouth() && neighbor.hasNorth() && shape.hasEast() && neighbor.hasWest())
                    return true;    //SOUTH EAST
            }
        }

        //we don't have a connection
        return false;
    }
}