package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;

import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_DIAMOND_COLS;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_DIAMOND_ROWS;

/**
 * Created by Kevin on 8/5/2017.
 */

public class Diamond extends CustomShape {

    /**
     * The size of the diamond
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

    public Diamond() {
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
        int rowGray = 1, rowGreen = 0;

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

        //now create the uvs coordinates;
        float[] uvsGray = null;
        float[] uvsGreen = null;

        if (getTextureCoordinatesGray() == null)
            uvsGray = new float[8];
        if (getTextureCoordinatesGreen() == null)
            uvsGreen = new float[8];

        final float startCol = (float)column / TEXTURE_DIAMOND_COLS;
        float startRow = (float)rowGray / TEXTURE_DIAMOND_ROWS;
        final float width = 1.0f / TEXTURE_DIAMOND_COLS;
        final float height = 1.0f / TEXTURE_DIAMOND_ROWS;

        uvsGray[0] = startCol; uvsGray[1] = startRow;
        uvsGray[2] = startCol; uvsGray[3] = startRow + height;
        uvsGray[4] = startCol + width; uvsGray[5] = startRow + height;
        uvsGray[6] = startCol + width; uvsGray[7] = startRow;
        super.setTextureCoordinatesGray(uvsGray);

        startRow = (float)rowGreen / TEXTURE_DIAMOND_ROWS;
        uvsGreen[0] = startCol; uvsGreen[1] = startRow;
        uvsGreen[2] = startCol; uvsGreen[3] = startRow + height;
        uvsGreen[4] = startCol + width; uvsGreen[5] = startRow + height;
        uvsGreen[6] = startCol + width; uvsGreen[7] = startRow;
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
        setNorth(!room.hasWall(Room.Wall.North));
        setSouth(!room.hasWall(Room.Wall.South));
        setWest(!room.hasWall(Room.Wall.West));
        setEast(!room.hasWall(Room.Wall.East));
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

        //get the distance
        double dx = Math.abs(x - (getX() + (getWidth() / 2)));
        double dy = Math.abs(y - (getY() + (getHeight() / 2)));
        double d = dx / getWidth() + dy / getHeight();

        //if close enough we are inside the diamond
        return d <= 0.5;
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