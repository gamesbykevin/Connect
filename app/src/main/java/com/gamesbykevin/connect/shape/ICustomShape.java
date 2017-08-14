package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.connect.common.ICommon;

/**
 * Created by Kevin on 8/5/2017.
 */

interface ICustomShape extends ICommon {

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

    boolean hasWest();
    boolean hasEast();
    boolean hasNorth();
    boolean hasSouth();
    boolean hasNorthWest();
    boolean hasNorthEast();
    boolean hasSouthWest();
    boolean hasSouthEast();

    void setWest(final boolean west);
    void setEast(final boolean east);
    void setSouth(final boolean south);
    void setNorth(final boolean north);
    void setNorthWest(final boolean northWest);
    void setNorthEast(final boolean northEast);
    void setSouthWest(final boolean southWest);
    void setSouthEast(final boolean southEast);

    void assignTextureIdPipe();
}