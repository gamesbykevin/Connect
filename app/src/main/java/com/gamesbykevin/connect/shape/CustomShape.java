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

    //what is open on this shape
    private boolean west = false;
    private boolean east = false;
    private boolean north = false;
    private boolean south = false;
    private boolean northWest = false;
    private boolean northEast = false;
    private boolean southWest = false;
    private boolean southEast = false;

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

    //do we render the shape background
    private boolean visible = true;

    private int textureIdPipeGray = -1, textureIdPipeGreen = -1;

    /**
     * Default constructor
     */
    protected CustomShape(int w, int h) {

        super();

        super.setWidth(w);
        super.setHeight(h);
    }

    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return this.visible;
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

    @Override
    public boolean hasWest() {
        return this.west;
    }

    @Override
    public boolean hasEast() {
        return this.east;
    }

    @Override
    public boolean hasNorth() {
        return this.north;
    }

    @Override
    public boolean hasSouth() {
        return this.south;
    }

    @Override
    public boolean hasNorthWest() {
        return this.northWest;
    }

    @Override
    public boolean hasNorthEast() {
        return this.northEast;
    }

    @Override
    public boolean hasSouthWest() {
        return this.southWest;
    }

    @Override
    public boolean hasSouthEast() {
        return this.southEast;
    }

    @Override
    public void setWest(final boolean west) {
        this.west = west;
    }

    @Override
    public void setEast(final boolean east) {
        this.east = east;
    }

    @Override
    public void setSouth(final boolean south) {
        this.south = south;
    }

    @Override
    public void setNorth(final boolean north) {
        this.north = north;
    }

    @Override
    public void setNorthWest(final boolean northWest) {
        this.northWest = northWest;
    }

    @Override
    public void setNorthEast(final boolean northEast) {
        this.northEast = northEast;
    }

    @Override
    public void setSouthWest(final boolean southWest) {
        this.southWest = southWest;
    }

    @Override
    public void setSouthEast(final boolean southEast) {
        this.southEast = southEast;
    }

    protected void setTextureIdPipeGray(final int textureIdPipeGray) {
        this.textureIdPipeGray = textureIdPipeGray;
    }

    protected void setTextureIdPipeGreen(final int textureIdPipeGreen) {
        this.textureIdPipeGreen = textureIdPipeGreen;
    }

    protected int getTextureIdPipeGray() {
        return this.textureIdPipeGray;
    }

    protected int getTextureIdPipeGreen() {
        return this.textureIdPipeGreen;
    }

    //need logic implemented to assign the texture
    public abstract void assignTextureIdPipe();

    @Override
    public void reset() {
        setVisible(true);
        setTextureIdPipeGreen(-1);
        setTextureIdPipeGray(-1);
    }

    @Override
    public void render(GL10 openGL) {

        if (isVisible())
            super.render(openGL);
    }
}