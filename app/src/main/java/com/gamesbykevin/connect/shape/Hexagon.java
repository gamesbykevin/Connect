package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;
import com.gamesbykevin.connect.activity.GameActivity;
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
    public static final float ROTATION_ANGLE = 60.0f;

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
        //if the coordinate is not in range return false
        if (x < getX() || x > getX() + getWidth())
            return false;
        if (y < getY() || y > getY() + getHeight())
            return false;

        //calculate the corners
        final double n = getY() + (getHeight() * .22);
        final double s = getY() + (getHeight() * .77);
        final double w = getX() + (getWidth() * .07);
        final double e = getX() + getWidth() - (getWidth() * .07);

        //have to be within the boundaries
        if (y < n || y > s)
            return false;
        if (x < w || x > e)
            return false;

        //we are inside the square of the hexagon
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