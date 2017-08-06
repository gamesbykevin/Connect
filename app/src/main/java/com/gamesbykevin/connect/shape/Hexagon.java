package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.opengl.Textures;

import javax.microedition.khronos.opengles.GL10;

import static com.gamesbykevin.connect.shape.CustomShape.ANGLE_MAX;

/**
 * Created by Kevin on 8/5/2017.
 */

public class Hexagon extends CustomShape {
    /**
     * The size of the square
     */
    public static int DIMENSION = 75;

    /**
     * How many degrees do we rotate the shape each time
     */
    public static final float ROTATE_VELOCITY = 4.0f;

    /**
     * How many degrees is each rotation for the given shape
     */
    public static final float ROTATION_ANGLE = 60.0f;

    //what is open on this shape
    private boolean north = false;
    private boolean northWest = false;
    private boolean northEast = false;
    private boolean south = false;
    private boolean southWest = false;
    private boolean southEast = false;

    public Hexagon() {
        super(DIMENSION, DIMENSION);
    }

    @Override
    public int getRotationCountMax() {
        return (int) (ANGLE_MAX / ROTATION_ANGLE);
    }

    @Override
    public int getTextureIdPipe() {
        if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S_SW);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S_SW);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_S_SW);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S_SW);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S_SW);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S_SW);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S_SW);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S_SW);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SE_S_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SE_S_SW);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SW);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SW);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SW);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SW);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SW);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_NE_SW);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_SW);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_SW);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_SW);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_SW);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_SW);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_N_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_N_SW);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NS : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NS);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NS : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NS);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NS : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NS);
        } else {
            return (isConnected() ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_ALL : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_ALL);
        }
    }

    @Override
    public void calculateAnglePipe() {
        if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(60);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(120);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(180);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(240);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(60);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(120);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(180);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(240);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(60);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(120);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(180);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(240);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(60);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(120);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(60);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(120);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(180);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(240);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(60);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(120);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(180);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(240);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(60);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(120);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(180);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(240);
        } else if (hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(60);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(120);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && hasSouthWest()) {
            setAnglePipe(180);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(240);
        } else if (hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(60);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(120);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(180);
        } else if (!hasNorth() && !hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(240);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(300);
        } else if (hasNorth() && !hasNorthWest() && !hasNorthEast() && !hasSouthEast() && hasSouth() && !hasSouthWest()) {
            setAnglePipe(0);
        } else if (!hasNorth() && !hasNorthWest() && hasNorthEast() && !hasSouthEast() && !hasSouth() && hasSouthWest()) {
            setAnglePipe(60);
        } else if (!hasNorth() && hasNorthWest() && !hasNorthEast() && hasSouthEast() && !hasSouth() && !hasSouthWest()) {
            setAnglePipe(120);
        } else {
            setAnglePipe(0);
        }
    }

    public boolean hasNorthWest() {
        return this.northWest;
    }

    public boolean hasNorthEast() {
        return this.northEast;
    }

    public boolean hasSouthWest() {
        return this.southWest;
    }

    public boolean hasSouthEast() {
        return this.southEast;
    }

    public boolean hasNorth() {
        return this.north;
    }

    public boolean hasSouth() {
        return this.south;
    }

    public void setNorthWest(final boolean northWest) {
        this.northWest = northWest;
    }

    public void setNorthEast(final boolean northEast) {
        this.northEast = northEast;
    }

    public void setSouthWest(final boolean southWest) {
        this.southWest = southWest;
    }

    public void setSouthEast(final boolean southEast) {
        this.southEast = southEast;
    }

    private void setSouth(final boolean south) {
        this.south = south;
    }

    private void setNorth(final boolean north) {
        this.north = north;
    }

    @Override
    public void setBorders(final Room room) {
        setNorth(!room.hasWall(Wall.North));
        setNorthWest(!room.hasWall(Wall.NorthWest));
        setNorthEast(!room.hasWall(Wall.NorthEast));
        setSouth(!room.hasWall(Wall.South));
        setSouthWest(!room.hasWall(Wall.SouthWest));
        setSouthEast(!room.hasWall(Wall.SouthEast));
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
        boolean oldNorth     =  new Boolean(hasNorth());
        boolean oldNorthWest =  new Boolean(hasNorthWest());
        boolean oldNorthEast =  new Boolean(hasNorthEast());
        boolean oldSouth     =  new Boolean(hasSouth());
        boolean oldSouthWest =  new Boolean(hasSouthWest());
        boolean oldSouthEast =  new Boolean(hasSouthEast());

        //rotate the borders along with the shape
        setNorthWest(oldSouthWest);
        setNorth(oldNorthWest);
        setNorthEast(oldNorth);
        setSouthEast(oldNorthEast);
        setSouth(oldSouthEast);
        setSouthWest(oldSouth);
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

    @Override
    public void reset() {
        //what do we do here?
    }

    /**
     * Render the shape on the board
     * @param openGL Object used to render pixel data
     */
    @Override
    public void render(GL10 openGL) {
        super.render(openGL);
    }
}