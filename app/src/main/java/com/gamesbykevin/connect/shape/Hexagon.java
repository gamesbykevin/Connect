package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;
import com.gamesbykevin.connect.opengl.Textures;

import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW;
import static com.gamesbykevin.connect.opengl.Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW;

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
    public void assignTextureIdPipe() {
        if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_ALL);
            setTextureIdPipeGreen(Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_ALL);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_END);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_END);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_END);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_END);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_END);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_END);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_END);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W);
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W);
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W);
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW);
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW);
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW);
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW);
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW);
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            setTextureIdPipeGray(TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW);
            setTextureIdPipeGreen(TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW);
        } else {
            throw new RuntimeException("Texture id not found NW:" + hasNorthWest() + " NE:" + hasNorthEast() + " E:" + hasEast() + " SE:" + hasSouthEast() + " SW:" + hasSouthWest() + " W:" + hasWest());
        }
    }

    @Override
    public int getTextureIdPipe() {

        //assign the values if they don't exist
        if (getTextureIdPipeGray() < 0 || getTextureIdPipeGreen() < 0)
            assignTextureIdPipe();

        //return the correct value
        return isConnected() ? getTextureIdPipeGreen() : getTextureIdPipeGray();
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