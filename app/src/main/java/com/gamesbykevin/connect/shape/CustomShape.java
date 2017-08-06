package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.base.Entity;
import com.gamesbykevin.androidframeworkv2.maze.Room;
import com.gamesbykevin.androidframeworkv2.maze.Room.Wall;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.common.ICommon;
import com.gamesbykevin.connect.opengl.Textures;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin on 8/1/2017.
 */

public abstract class CustomShape extends Entity implements ICustomShape {

    //are we rotating
    private boolean rotate = false;

    //where do we want to rotate to
    private float destinationAngle;

    public static final float ROTATE_VELOCITY_DIAMOND = 6.0f;
    public static final float ROTATION_ANGLE_DIAMOND = 90.0f;

    /**
     * The largest angle allowed
     */
    public static final float ANGLE_MAX = 360.0f;

    /**
     * The smallest angle allowed
     */
    public static final float ANGLE_MIN = 0.0f;

    //is this shape connected to the solution
    public boolean connected = false;

    //keep track of how many rotations we made
    private int rotations = 0;

    //how to render the pipe
    private float anglePipe = 0.0f;

    /**
     * Default constructor
     */
    protected CustomShape(int w, int h) {

        super();

        super.setWidth(w);
        super.setHeight(h);
    }

    @Override
    public abstract int getTextureIdPipe();

    @Override
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public int getRotationCount() {
        return this.rotations;
    }

    @Override
    public abstract int getRotationCountMax();

    @Override
    public void setRotationCount(final int rotations) {
        this.rotations = rotations;
    }

    @Override
    public void setRotate(final boolean rotate) {
        this.rotate = rotate;
    }

    @Override
    public boolean hasRotate() {
        return this.rotate;
    }

    @Override
    public void setDestinationAngle(final float destinationAngle) {
        this.destinationAngle = destinationAngle;

        if (getDestinationAngle() >= ANGLE_MAX)
            setDestinationAngle(ANGLE_MIN);
    }

    @Override
    public float getDestinationAngle() {
        return this.destinationAngle;
    }

    @Override
    public void setAnglePipe(final float anglePipe) {
        this.anglePipe = anglePipe;
    }

    @Override
    public float getAnglePipe() {
        return this.anglePipe;
    }
}