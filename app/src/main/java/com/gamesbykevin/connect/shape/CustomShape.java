package com.gamesbykevin.connect.shape;

import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.entity.Entity;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin on 8/1/2017.
 */

public abstract class CustomShape extends Entity implements ICustomShape {

    //are we rotating
    private boolean rotate = false;

    //where do we want to rotate to
    private float destinationAngle;

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

    //the texture id of both pipes (connected/not connected)
    private int textureIdPipeGray = -1, textureIdPipeGreen = -1;

    //how fast can we rotate the shape
    private float rotateVelocity = 0.0f;

    //# of degrees for a single rotation
    public static float ROTATION_ANGLE;

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
    public void dispose() {
        //clean up anything?
    }

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
    public int getTextureId() {

        //assign the values if they don't exist
        if (getTextureIdPipeGray() < 0 || getTextureIdPipeGreen() < 0)
            assignTextureIdPipe();

        //return the correct value
        return isConnected() ? getTextureIdPipeGreen() : getTextureIdPipeGray();
    }

    @Override
    public void reset() {
        setVisible(true);
        setTextureIdPipeGreen(-1);
        setTextureIdPipeGray(-1);
    }

    public void setRotateVelocity(final float rotateVelocity) {
        this.rotateVelocity = rotateVelocity;
    }

    public float getRotateVelocity() {
        return this.rotateVelocity;
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

    /**
     * Update the vertices based on the combined current angle and pipe angle
     */
    public void updateVertices() {

        //get angle
        float angle = getAngle();

        //offset angle for pipe
        setAngle(getAngle() + getAnglePipe());

        //update vertices
        super.getTransformedVertices();

        //restore angle
        setAngle(angle);
    }

    @Override
    public void update(GameActivity activity) {

        //if we are rotating
        if (hasRotate()) {

            //if we hit the destination
            if (getAngle() == getDestinationAngle()) {

                //stop rotating
                rotateFinish();

                //make sure vertices are correct
                updateVertices();

            } else {

                setAngle(getAngle() + getRotateVelocity());

                //make sure we stay within range
                if (getAngle() >= ANGLE_MAX)
                    setAngle(ANGLE_MIN);
            }
        }
    }

    protected double getDistance(float x, float y) {

        //calculate the center
        float mx = getX() + (getWidth() / 2);
        float my = getY() + (getHeight() / 2);

        //return the distance
        return Math.sqrt(Math.pow((x - mx), 2) + Math.pow((y - my), 2));
    }
}