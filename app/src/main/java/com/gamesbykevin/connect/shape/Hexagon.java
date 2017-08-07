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
    public int getTextureIdPipe() {
        if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_ALL : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_ALL;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_END : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_END;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SE_SW_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SE_SW_W;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_SW;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_E_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_E_W;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SE : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SE;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_SW;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W;
        } else if (!hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W;
        } else if (!hasNorthWest() && !hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W;
        } else if (hasNorthWest() && !hasNorthEast() && !hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NE_W : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NE_W;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_SE_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_SE_SW;
        } else if (hasNorthWest() && hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW;
        } else if (!hasNorthWest() && hasNorthEast() && hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW;
        } else if (hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_NE_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_NE_E_SW;
        } else if (hasNorthWest() && !hasNorthEast() && hasEast() && !hasSouthEast() && hasSouthWest() && !hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW;
        } else if (!hasNorthWest() && hasNorthEast() && !hasEast() && hasSouthEast() && !hasSouthWest() && hasWest()) {
            return (isConnected()) ? Textures.TEXTURE_ID_HEXAGON_GREEN_PIPE_NW_E_SW : Textures.TEXTURE_ID_HEXAGON_GRAY_PIPE_NW_E_SW;


        } else {
            throw new RuntimeException("Texture id not found NW:" + hasNorthWest() + " NE:" + hasNorthEast() + " E:" + hasEast() + " SE:" + hasSouthEast() + " SW:" + hasSouthWest() + " W:" + hasWest());
        }
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
        boolean oldNorthWest =  new Boolean(hasNorthWest());
        boolean oldNorthEast =  new Boolean(hasNorthEast());
        boolean oldEast      =  new Boolean(hasEast());
        boolean oldSouthEast =  new Boolean(hasSouthEast());
        boolean oldSouthWest =  new Boolean(hasSouthWest());
        boolean oldWest      =  new Boolean(hasWest());

        //rotate the borders along with the shape
        setNorthWest(oldWest);
        setNorthEast(oldNorthWest);
        setEast(oldNorthEast);
        setSouthEast(oldEast);
        setSouthWest(oldSouthEast);
        setWest(oldSouthWest);
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