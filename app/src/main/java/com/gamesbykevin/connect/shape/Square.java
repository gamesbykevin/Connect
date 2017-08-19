package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;

import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_SQUARE_COLS;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_SQUARE_ROWS;

/**
 * Created by Kevin on 8/5/2017.
 */

public class Square extends CustomShape {

    /**
     * The size of the square
     */
    public static int DIMENSION = 64;

    /**
     * How many degrees do we rotate the shape each time
     */
    public static final float ROTATE_VELOCITY = 6.0f;

    /**
     * How many degrees is each rotation for the given shape
     */
    public static final float ROTATION_ANGLE_DEFAULT = 90.0f;

    /**
     * How many degrees do we rotate the shape each time when auto rotating
     */
    public static final float ROTATE_VELOCITY_FAST = (ROTATION_ANGLE_DEFAULT / 10);

    public Square() {
        super(DIMENSION, DIMENSION);
    }

    @Override
    public int getRotationCountMax() {
        return (int)(ANGLE_MAX / ROTATION_ANGLE);
    }

    @Override
    public void assignTextureCoordinates() {

        //the location of each
        int column;
        int rowGray = 0, rowGreen = 1;

        if (hasNorth() && !hasSouth() && !hasEast() && !hasWest()) { //n
            column = 0;
        } else if (!hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //s
            column = 0;
        } else if (!hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //e
            column = 0;
        } else if (!hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //w
            column = 0;
        } else if (hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //ne
            column = 3;
        } else if (hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //nw
            column = 3;
        } else if (!hasNorth() && hasSouth() && hasEast() && !hasWest()) { //se
            column = 3;
        } else if (!hasNorth() && hasSouth() && !hasEast() && hasWest()) { //sw
            column = 3;
        } else if (hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //ns
            column = 1;
        } else if (!hasNorth() && !hasSouth() && hasEast() && hasWest()) { //we
            column = 1;
        } else if (hasNorth() && hasSouth() && hasEast() && hasWest()) { //nsew
            column = 2;
        } else {
            //NSW, NSE, WEN, WES
            column = 4;
        }

        if (column >= TEXTURE_SQUARE_COLS || column < 0)
            throw new RuntimeException("Unexpected column " + column);
        if (rowGray >= TEXTURE_SQUARE_ROWS || rowGray < 0)
            throw new RuntimeException("Unexpected rowGray " + rowGray);
        if (rowGreen >= TEXTURE_SQUARE_ROWS || rowGreen < 0)
            throw new RuntimeException("Unexpected rowGreen " + rowGreen);


        //now create the uvs coordinates;
        float[] uvsGray = null;
        float[] uvsGreen = null;

        if (getTextureCoordinatesGray() == null)
            uvsGray = new float[8];
        if (getTextureCoordinatesGreen() == null)
            uvsGreen = new float[8];

        final float startCol = (float)column / TEXTURE_SQUARE_COLS;
        final float width = 1.0f / TEXTURE_SQUARE_COLS;
        final float height = 1.0f / TEXTURE_SQUARE_ROWS;

        float startRow = (float)rowGray / TEXTURE_SQUARE_ROWS;
        setupUVS(uvsGray, startCol, startRow, width, height);
        super.setTextureCoordinatesGray(uvsGray);

        startRow = (float)rowGreen / TEXTURE_SQUARE_ROWS;
        setupUVS(uvsGreen, startCol, startRow, width, height);
        super.setTextureCoordinatesGreen(uvsGreen);
    }

    @Override
    public void calculateAnglePipe() {
        if (hasWest() && hasEast() && hasNorth() && hasSouth()) {
            setAnglePipe(0);   //wens
        } else if (hasWest() && hasEast() && !hasNorth() && hasSouth()) {
            setAnglePipe(0);   //wes
        } else if (hasWest() && hasEast() && hasNorth() && !hasSouth()) {
            setAnglePipe(180); //wen
        } else if (!hasWest() && hasEast() && hasNorth() && hasSouth()) {
            setAnglePipe(270); //ens
        } else if (hasWest() && !hasEast() && hasNorth() && hasSouth()) {
            setAnglePipe(90);  //wns
        } else if (hasWest() && hasEast() && !hasNorth() && !hasSouth()) {
            setAnglePipe(90);  //we
        } else if (!hasWest() && !hasEast() && hasNorth() && hasSouth()) {
            setAnglePipe(0);   //ns
        } else if (hasWest() && !hasEast() && !hasNorth() && hasSouth()) {
            setAnglePipe(90);  //ws
        } else if (!hasWest() && hasEast() && !hasNorth() && hasSouth()) {
            setAnglePipe(0);   //es
        } else if (hasWest() && !hasEast() && hasNorth() && !hasSouth()) {
            setAnglePipe(180); //nw
        } else if (!hasWest() && hasEast() && hasNorth() && !hasSouth()) {
            setAnglePipe(270); //en
        } else if (!hasWest() && !hasEast() && hasNorth() && !hasSouth()) {
            setAnglePipe(180); //n
        } else if (!hasWest() && !hasEast() && !hasNorth() && hasSouth()) {
            setAnglePipe(0);   //s
        } else if (!hasWest() && hasEast() && !hasNorth() && !hasSouth()) {
            setAnglePipe(270); //e
        } else if (hasWest() && !hasEast() && !hasNorth() && !hasSouth()) {
            setAnglePipe(90);  //w
        } else {
            throw new RuntimeException("Angle not found west:" + hasWest() + ", east:" + hasEast() + ", north:" + hasNorth() + ", south:" + hasSouth());
        }
    }

    @Override
    public void setBorders(final Room room) {
        setNorth(!room.hasWall(Wall.North));
        setSouth(!room.hasWall(Wall.South));
        setWest(!room.hasWall(Wall.West));
        setEast(!room.hasWall(Wall.East));
        setSouthEast(false);
        setSouthWest(false);
        setNorthEast(false);
        setNorthWest(false);
    }

    @Override
    public boolean contains(final float x, final float y) {

        //if the coordinate is not in range return false
        if (x < getX() || x > getX() + getWidth())
            return false;
        if (y < getY() || y > getY() + getHeight())
            return false;

        //this coordinate is contained within
        return true;
    }


    @Override
    public void rotateFinish() {

        //stop rotating
        setRotate(false);

        //update the destination angle
        setAngle(getDestinationAngle());

        //update the current borders
        final int oldN = (hasNorth()) ? 0 : 1;
        final int oldE = (hasEast()) ? 0 : 1;
        final int oldS = (hasSouth()) ? 0 : 1;
        final int oldW = (hasWest()) ? 0 : 1;

        //rotate the borders along with the shape
        setEast(oldN == 0);
        setSouth(oldE == 0);
        setWest(oldS == 0);
        setNorth(oldW == 0);
    }
}