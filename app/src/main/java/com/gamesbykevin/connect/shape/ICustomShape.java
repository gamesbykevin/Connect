package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.connect.common.ICommon;

/**
 * Created by Kevin on 8/5/2017.
 */

interface ICustomShape extends ICommon {

    int getTextureIdPipe();

    void calculateAnglePipe();

    void setBorders(final Room room);

    boolean contains(final float x, final float y);

    void rotate();

    /**
     * Quickly finish the rotation
     */
    void rotateFinish();

    void setDestinationAngle(final float destinationAngle);

    float getDestinationAngle();

    /**
     * Get the current rotation count, so we know when we have rotated enough
     * @return The total number of times this piece has rotated
     */
    int getRotationCount();

    int getRotationCountMax();

    void setRotationCount(final int rotations);

    void setRotate(final boolean rotate);

    boolean hasRotate();

    void setConnected(boolean connected);

    boolean isConnected();

    void setAnglePipe(final float anglePipe);

    float getAnglePipe();
}