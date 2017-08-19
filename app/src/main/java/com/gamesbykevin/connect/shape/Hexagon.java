package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;

import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_HEXAGON_COLS;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_HEXAGON_ROWS;

/**
 * Created by Kevin on 8/5/2017.
 */

public class Hexagon extends CustomShape {
    /**
     * The size of the square
     */
    public static int DIMENSION = 64;

    /**
     * How many degrees do we rotate the shape each time
     */
    public static final float ROTATE_VELOCITY = 4.0f;

    /**
     * How many degrees is each rotation for the given shape
     */
    public static final float ROTATION_ANGLE_DEFAULT = 60.0f;

    /**
     * How many degrees do we rotate the shape each time when auto rotating
     */
    public static final float ROTATE_VELOCITY_FAST = (ROTATION_ANGLE_DEFAULT / 10);
    
    public Hexagon() {
        super(DIMENSION, DIMENSION);
    }

    @Override
    public int getRotationCountMax() {
        return (int) (ANGLE_MAX / ROTATION_ANGLE);
    }

    @Override
    public void assignTextureCoordinates() {

        //the location of each
        int column;
        int rowGray = 0, rowGreen = 1;

        if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 0;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 1;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 1;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 1;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 1;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 1;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 1;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 2;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 2;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 2;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 2;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 2;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 2;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 3;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 3;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 3;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 3;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 3;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 3;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 4;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 4;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 4;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 4;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 4;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 4;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 5;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 5;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 5;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 5;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 5;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 5;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 6;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 6;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 6;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 6;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 6;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 6;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 7;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 7;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 7;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 7;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 7;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 7;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 8;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 8;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 8;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 8;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 8;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 8;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 9;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 9;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 9;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 10;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 10;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            column = 10;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 10;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 10;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 10;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 13;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 13;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 13;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 12;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 12;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 12;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 12;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            column = 12;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 12;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            column = 11;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            column = 11;
        } else {
            throw new RuntimeException("Texture id not found NW:" + hasNorthWest() + " NE:" + hasNorthEast() + " E:" + hasEast() + " SE:" + hasSouthEast() + " SW:" + hasSouthWest() + " W:" + hasWest());
        }

        if (rowGray >= TEXTURE_HEXAGON_ROWS || rowGray < 0)
            throw new RuntimeException("Unexpected rowGray " + rowGray);
        if (rowGreen >= TEXTURE_HEXAGON_ROWS || rowGreen < 0)
            throw new RuntimeException("Unexpected rowGreen " + rowGreen);

        //now create the uvs coordinates;
        float[] uvsGray = null;
        float[] uvsGreen = null;

        if (getTextureCoordinatesGray() == null)
            uvsGray = new float[8];
        if (getTextureCoordinatesGreen() == null)
            uvsGreen = new float[8];

        final float startCol = (float)column / TEXTURE_HEXAGON_COLS;
        final float width = 1.0f / TEXTURE_HEXAGON_COLS;
        final float height = 1.0f / TEXTURE_HEXAGON_ROWS;

        float startRow = (float)rowGray / TEXTURE_HEXAGON_ROWS;
        setupUVS(uvsGray, startCol, startRow, width, height);
        super.setTextureCoordinatesGray(uvsGray);

        startRow = (float)rowGreen / TEXTURE_HEXAGON_ROWS;
        setupUVS(uvsGreen, startCol, startRow, width, height);
        super.setTextureCoordinatesGreen(uvsGreen);
    }

    @Override
    public void calculateAnglePipe() {
        if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(60);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(120);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(180);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(60);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(120);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(180);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(60);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(120);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(180);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(60);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(120);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(180);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(0);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(60);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(120);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(180);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(60);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(120);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(180);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(0);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(60);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(120);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(180);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(60);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(120);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(180);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(60);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(120);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(60);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setAnglePipe(120);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(180);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(300);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(60);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(120);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(60);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(120);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(180);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setAnglePipe(240);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(300);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setAnglePipe(0);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setAnglePipe(60);
        } else {
            throw new RuntimeException("Angle not set NW:" + hasNorthWest() + " NE:" + hasNorthEast() + " E:" + hasEast() + " SE:" + hasSouthEast() + " SW:" + hasSouthWest() + " W:" + hasWest());
        }
    }

    @Override
    public void setBorders(final Room room) {
        setNorthWest(!room.hasWall(Wall.NorthWest));
        setNorthEast(!room.hasWall(Wall.NorthEast));
        setEast(!room.hasWall(Wall.East));
        setSouthEast(!room.hasWall(Wall.SouthEast));
        setSouthWest(!room.hasWall(Wall.SouthWest));
        setWest(!room.hasWall(Wall.West));
        setNorth(false);
        setSouth(false);
    }

    @Override
    public boolean contains(final float x, final float y) {

        //if we are close enough to the center return true
        return (super.getDistance(x, y) < getWidth() / 2);
    }

    @Override
    public void rotateFinish() {

        //stop rotating
        setRotate(false);

        //update the destination angle
        setAngle(getDestinationAngle());

        //update the current borders
        final int oldNW = hasNorthWest() ? 0 : 1;
        final int oldNE = hasNorthEast() ? 0 : 1;
        final int oldE  = hasEast() ? 0 : 1;
        final int oldSE = hasSouthEast() ? 0 : 1;
        final int oldSW = hasSouthWest() ? 0 : 1;
        final int oldW  = hasWest() ? 0 : 1;

        //rotate the borders along with the shape
        setNorthWest(oldW == 0);
        setNorthEast(oldNW == 0);
        setEast(oldNE == 0);
        setSouthEast(oldE == 0);
        setSouthWest(oldSE == 0);
        setWest(oldSW == 0);
    }
}