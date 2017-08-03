package com.gamesbykevin.connect.board;

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

import javax.microedition.khronos.opengles.GL10;

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
    public static final int BOARD_ROWS = 7;

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

    public final void addShape(Shape shape, CustomShape.Connections connection, float x, float y, int col, int row) {

        CustomShape tmp = new CustomShape(shape, connection);

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

        switch (GameActivity.getRandomObject().nextInt(4)) {

            default:
            case 0:
                tmp.setAngle(tmp.getAngle() + 0);
                break;

            case 1:
                tmp.setAngle(tmp.getAngle() + 90);
                break;

            case 2:
                tmp.setAngle(tmp.getAngle() + 180);
                break;

            case 3:
                tmp.setAngle(tmp.getAngle() + 270);
                break;
        }

        if (col == 0 && row == 0)
            tmp.setConnected(true);

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

        for (int col = 0; col < getMaze().getCols(); col++) {
            for (int row = 0; row < getMaze().getRows(); row++) {

                CustomShape shape = getShapes()[row][col];

                if (col == 0 && row == 0) {
                    shape.setConnected(true);
                    continue;
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

                        y = 50 + (row * CustomShape.DEFAULT_DIMENSION);

                        Room room = getMaze().getRoom(col, row);

                        CustomShape.Connections connection = null;

                        boolean hasW = !room.hasWall(Wall.West);
                        boolean hasE = !room.hasWall(Wall.East);
                        boolean hasN = !room.hasWall(Wall.North);
                        boolean hasS = !room.hasWall(Wall.South);

                        if (hasN && !hasS && !hasW && !hasE)
                            connection = CustomShape.Connections.N;
                        if (!hasN && hasS && !hasW && !hasE)
                            connection = CustomShape.Connections.S;
                        if (!hasN && !hasS && hasW && !hasE)
                            connection = CustomShape.Connections.W;
                        if (!hasN && !hasS && !hasW && hasE)
                            connection = CustomShape.Connections.E;

                        if (hasN && hasS && !hasW && !hasE)
                            connection = CustomShape.Connections.NS;
                        if (!hasN && !hasS && hasW && hasE)
                            connection = CustomShape.Connections.WE;
                        if (!hasN && hasS && !hasW && hasE)
                            connection = CustomShape.Connections.SE;
                        if (hasN && !hasS && hasW && !hasE)
                            connection = CustomShape.Connections.NW;
                        if (hasN && !hasS && !hasW && hasE)
                            connection = CustomShape.Connections.NE;
                        if (!hasN && hasS && hasW && !hasE)
                            connection = CustomShape.Connections.SW;

                        if (hasN && hasS && hasW && !hasE)
                            connection = CustomShape.Connections.NSW;
                        if (hasN && hasS && !hasW && hasE)
                            connection = CustomShape.Connections.NSE;
                        if (hasN && !hasS && hasW && hasE)
                            connection = CustomShape.Connections.WEN;
                        if (!hasN && hasS && hasW && hasE)
                            connection = CustomShape.Connections.WES;

                        if (hasN && hasS && hasW && hasE)
                            connection = CustomShape.Connections.NSEW;

                        addShape(Shape.Square, connection, x, y, col, row);
                    }
                }
            } else {

                boolean done = false;

                for (int col = 0; col < getMaze().getCols(); col++) {
                    for (int row = 0; row < getMaze().getRows(); row++) {

                        CustomShape shape = getShapes()[row][col];

                        if (shape.hasRotate()) {
                            shape.update(activity);

                            if (shape.hasRotate()) {
                                done = false;
                            } else {
                                done = true;
                            }
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

                switch (shape.getConnection()) {
                    case N:
                    case S:
                    case E:
                    case W:
                        pipe.setTextureId(shape.isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_END : Textures.TEXTURE_ID_GRAY_PIPE_END);
                        break;

                    case NE:
                    case NW:
                    case SE:
                    case SW:
                        pipe.setTextureId(shape.isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_SE : Textures.TEXTURE_ID_GRAY_PIPE_SE);
                        break;

                    case NS:
                    case WE:
                        pipe.setTextureId(shape.isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_NS : Textures.TEXTURE_ID_GRAY_PIPE_NS);
                        break;

                    case NSEW:
                        pipe.setTextureId(shape.isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_NSEW : Textures.TEXTURE_ID_GRAY_PIPE_NSEW);
                        break;

                    case NSW:
                    case NSE:
                    case WEN:
                    case WES:
                        pipe.setTextureId(shape.isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_WES : Textures.TEXTURE_ID_GRAY_PIPE_WES);
                        break;
                }

                pipe.setX(shape);
                pipe.setY(shape);
                pipe.setAngle(shape.getAngle() + shape.getConnection().getAngleAdjustment());
                pipe.render(openGL);
            }
        }
    }
}