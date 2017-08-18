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
        int rowGray = 1, rowGreen = 0;

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

        //now create the uvs coordinates;
        float[] uvsGray = null;
        float[] uvsGreen = null;

        if (getTextureCoordinatesGray() == null)
            uvsGray = new float[8];
        if (getTextureCoordinatesGreen() == null)
            uvsGreen = new float[8];

        final float startCol = (float)column / TEXTURE_HEXAGON_COLS;
        float startRow = (float)rowGray / TEXTURE_HEXAGON_ROWS;
        final float width = 1.0f / TEXTURE_HEXAGON_COLS;
        final float height = 1.0f / TEXTURE_HEXAGON_ROWS;

        uvsGray[0] = startCol; uvsGray[1] = startRow;
        uvsGray[2] = startCol; uvsGray[3] = startRow + height;
        uvsGray[4] = startCol + width; uvsGray[5] = startRow + height;
        uvsGray[6] = startCol + width; uvsGray[7] = startRow;
        super.setTextureCoordinatesGray(uvsGray);

        startRow = (float)rowGreen / TEXTURE_HEXAGON_ROWS;
        uvsGreen[0] = startCol; uvsGreen[1] = startRow;
        uvsGreen[2] = startCol; uvsGreen[3] = startRow + height;
        uvsGreen[4] = startCol + width; uvsGreen[5] = startRow + height;
        uvsGreen[6] = startCol + width; uvsGreen[7] = startRow;
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