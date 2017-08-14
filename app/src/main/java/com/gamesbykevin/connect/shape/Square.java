package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.opengl.Textures;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GRAY_PIPE_END;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GRAY_PIPE_NS;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GRAY_PIPE_NSEW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GRAY_PIPE_SE;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GRAY_PIPE_WES;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GREEN_PIPE_END;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GREEN_PIPE_NS;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GREEN_PIPE_NSEW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GREEN_PIPE_SE;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_SQUARE_GREEN_PIPE_WES;

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
    public void assignTextureIdPipe() {
        if (hasNorth() && !hasSouth() && !hasEast() && !hasWest()) { //n
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_END);
        } else if (!hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //s
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_END);
        } else if (!hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //e
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_END);
        } else if (!hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //w
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_END);
        } else if (hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //ne
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_SE);
        } else if (hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //nw
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_SE);
        } else if (!hasNorth() && hasSouth() && hasEast() && !hasWest()) { //se
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_SE);
        } else if (!hasNorth() && hasSouth() && !hasEast() && hasWest()) { //sw
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_SE);
        } else if (hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //ns
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_NS);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_NS);
        } else if (!hasNorth() && !hasSouth() && hasEast() && hasWest()) { //we
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_NS);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_NS);
        } else if (hasNorth() && hasSouth() && hasEast() && hasWest()) { //nsew
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_NSEW);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_NSEW);
        } else {
            //NSW, NSE, WEN, WES
            setTextureIdPipeGray(TEXTURE_ID_SQUARE_GRAY_PIPE_WES);
            setTextureIdPipeGreen(TEXTURE_ID_SQUARE_GREEN_PIPE_WES);
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