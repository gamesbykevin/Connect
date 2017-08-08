package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.opengl.Textures;

import javax.microedition.khronos.opengles.GL10;

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
    public static final float ROTATION_ANGLE = 90.0f;

    public Diamond() {
        super(DIMENSION, DIMENSION);
    }

    @Override
    public int getRotationCountMax() {
        return (int)(ANGLE_MAX / ROTATION_ANGLE);
    }

    @Override
    public int getTextureIdPipe() {

        if (hasNorth() && !hasSouth() && !hasEast() && !hasWest()) { //n
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_END : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_END);
        } else if (!hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //s
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_END : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //e
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_END : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //w
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_END : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_END);
        } else if (hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //ne
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_SE : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_SE);
        } else if (hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //nw
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_SE : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_SE);
        } else if (!hasNorth() && hasSouth() && hasEast() && !hasWest()) { //se
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_SE : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_SE);
        } else if (!hasNorth() && hasSouth() && !hasEast() && hasWest()) { //sw
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_SE : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_SE);
        } else if (hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //ns
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_NS : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_NS);
        } else if (!hasNorth() && !hasSouth() && hasEast() && hasWest()) { //we
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_NS : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_NS);
        } else if (hasNorth() && hasSouth() && hasEast() && hasWest()) { //nsew
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_NSEW : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_NSEW);
        } else {
            //NSW, NSE, WEN, WES
            return (isConnected() ? Textures.TEXTURE_ID_DIAMOND_GREEN_PIPE_WES : Textures.TEXTURE_ID_DIAMOND_GRAY_PIPE_WES);
        }
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
    public void rotate() {

        //we can't rotate again if we are currently
        if (hasRotate())
            return;

        //keep track of our rotations
        setRotationCount(getRotationCount() + 1);

        //flag rotate true
        setRotate(true);

        //set next destination
        setDestinationAngle(getAngle() + ROTATION_ANGLE);
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

    @Override
    public void dispose() {
        //clean up
    }

    @Override
    public void update(GameActivity activity) {

        //if we are rotating
        if (hasRotate()) {

            //if we hit the destination
            if (getAngle() == getDestinationAngle()) {

                //stop rotating
                rotateFinish();

            } else {

                setAngle(getAngle() + ROTATE_VELOCITY);

                //make sure we stay within range
                if (getAngle() >= ANGLE_MAX)
                    setAngle(ANGLE_MIN);
            }
        }
    }
}