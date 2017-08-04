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

public class CustomShape extends Entity implements ICommon {

    public static final int DEFAULT_DIMENSION = 75;

    //are we rotating
    private boolean rotate = false;

    //where do we want to rotate to
    private float destinationAngle;

    /**
     * How many degrees do we rotate the shape each time
     */
    public static final float ROTATE_VELOCITY_SQUARE = 6.0f;
    public static final float ROTATE_VELOCITY_HEXAGON = 4.0f;
    public static final float ROTATE_VELOCITY_DIAMOND = 6.0f;

    /**
     * How many degrees is each rotation for the given shape
     */
    public static final float ROTATION_ANGLE_SQUARE = 90.0f;
    public static final float ROTATION_ANGLE_HEXAGON = 60.0f;
    public static final float ROTATION_ANGLE_DIAMOND = 90.0f;

    /**
     * The largest angle allowed
     */
    public static final float ANGLE_MAX = 360.0f;

    /**
     * The smallest angle allowed
     */
    public static final float ANGLE_MIN = 0.0f;

    public boolean connected = false;

    private final Board.Shape shape;

    //what is open on this shape
    private boolean west = false;
    private boolean east = false;
    private boolean north = false;
    private boolean south = false;

    //how to render the pipe
    private float anglePipe = 0.0f;

    /**
     * Default constructor
     */
    public CustomShape(final Board.Shape shape) {

        super();

        this.shape = shape;

        super.setWidth(DEFAULT_DIMENSION);
        super.setHeight(DEFAULT_DIMENSION);
    }

    public boolean hasWest() {
        return this.west;
    }

    public boolean hasEast() {
        return this.east;
    }

    public boolean hasNorth() {
        return this.north;
    }

    public boolean hasSouth() {
        return this.south;
    }

    private void setWest(final boolean west) {
        this.west = west;
    }

    private void setEast(final boolean east) {
        this.east = east;
    }

    private void setSouth(final boolean south) {
        this.south = south;
    }

    private void setNorth(final boolean north) {
        this.north = north;
    }

    public void setBorders(final Room room) {
        setNorth(!room.hasWall(Wall.North));
        setSouth(!room.hasWall(Wall.South));
        setWest(!room.hasWall(Wall.West));
        setEast(!room.hasWall(Wall.East));
    }

    public int getTextureIdPipe() {

        if (hasNorth() && !hasSouth() && !hasEast() && !hasWest()) { //n
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_END : Textures.TEXTURE_ID_GRAY_PIPE_END);
        } else if (!hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //s
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_END : Textures.TEXTURE_ID_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //e
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_END : Textures.TEXTURE_ID_GRAY_PIPE_END);
        } else if (!hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //w
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_END : Textures.TEXTURE_ID_GRAY_PIPE_END);
        } else if (hasNorth() && !hasSouth() && hasEast() && !hasWest()) { //ne
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_SE : Textures.TEXTURE_ID_GRAY_PIPE_SE);
        } else if (hasNorth() && !hasSouth() && !hasEast() && hasWest()) { //nw
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_SE : Textures.TEXTURE_ID_GRAY_PIPE_SE);
        } else if (!hasNorth() && hasSouth() && hasEast() && !hasWest()) { //se
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_SE : Textures.TEXTURE_ID_GRAY_PIPE_SE);
        } else if (!hasNorth() && hasSouth() && !hasEast() && hasWest()) { //sw
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_SE : Textures.TEXTURE_ID_GRAY_PIPE_SE);
        } else if (hasNorth() && hasSouth() && !hasEast() && !hasWest()) { //ns
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_NS : Textures.TEXTURE_ID_GRAY_PIPE_NS);
        } else if (!hasNorth() && !hasSouth() && hasEast() && hasWest()) { //we
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_NS : Textures.TEXTURE_ID_GRAY_PIPE_NS);
        } else if (hasNorth() && hasSouth() && hasEast() && hasWest()) { //nsew
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_NSEW : Textures.TEXTURE_ID_GRAY_PIPE_NSEW);
        } else {
            //NSW, NSE, WEN, WES
            return (isConnected() ? Textures.TEXTURE_ID_GREEN_PIPE_WES : Textures.TEXTURE_ID_GRAY_PIPE_WES);
        }
    }

    public float getAnglePipe() {
        return this.anglePipe;
    }

    public void calculateAnglePipe() {
        if (hasWest() && hasEast() && hasNorth() && hasSouth()) {
            this.anglePipe = 0;   //wens
        } else if (hasWest() && hasEast() && !hasNorth() && hasSouth()) {
            this.anglePipe = 0;   //wes
        } else if (hasWest() && hasEast() && hasNorth() && !hasSouth()) {
            this.anglePipe = 180; //wen
        } else if (!hasWest() && hasEast() && hasNorth() && hasSouth()) {
            this.anglePipe = 270; //ens
        } else if (hasWest() && !hasEast() && hasNorth() && hasSouth()) {
            this.anglePipe = 90;  //wns
        } else if (hasWest() && hasEast() && !hasNorth() && !hasSouth()) {
            this.anglePipe = 90;  //we
        } else if (!hasWest() && !hasEast() && hasNorth() && hasSouth()) {
            this.anglePipe = 0;   //ns
        } else if (hasWest() && !hasEast() && !hasNorth() && hasSouth()) {
            this.anglePipe = 90;  //ws
        } else if (!hasWest() && hasEast() && !hasNorth() && hasSouth()) {
            this.anglePipe = 0;   //es
        } else if (hasWest() && !hasEast() && hasNorth() && !hasSouth()) {
            this.anglePipe = 180; //nw
        } else if (!hasWest() && hasEast() && hasNorth() && !hasSouth()) {
            this.anglePipe = 270; //en
        } else if (!hasWest() && !hasEast() && hasNorth() && !hasSouth()) {
            this.anglePipe = 180; //n
        } else if (!hasWest() && !hasEast() && !hasNorth() && hasSouth()) {
            this.anglePipe = 0;   //s
        } else if (!hasWest() && hasEast() && !hasNorth() && !hasSouth()) {
            this.anglePipe = 270; //e
        } else if (hasWest() && !hasEast() && !hasNorth() && !hasSouth()) {
            this.anglePipe=90;  //w
        } else {
            throw new RuntimeException("Angle not found west:" + west + ", east:" + east + ", north:" + north + ", south:" + south);
        }
    }

    public Board.Shape getShape() {
        return this.shape;
    }

    public boolean contains(final float x, final float y) {

        //if the coordinate is not in range return false
        if (x < getX() || x > getX() + getWidth())
            return false;
        if (y < getY() || y > getY() + getHeight())
            return false;

        //this coordinate is contained within
        return true;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void rotate() {

        //we can't rotate again if we are currently
        if (hasRotate())
            return;

        //flag rotate true
        setRotate(true);

        switch (getShape()) {

            case Square:
                setDestinationAngle(getAngle() + ROTATION_ANGLE_SQUARE);
                break;

            case Hexagon:
                setDestinationAngle(getAngle() + ROTATION_ANGLE_HEXAGON);
                break;

            case Diamond:
                setDestinationAngle(getAngle() + ROTATION_ANGLE_DIAMOND);
                break;

            default:
                throw new RuntimeException("Shape not defined: " + getShape());
        }
    }

    /**
     * Quickly finish the rotation
     */
    public void rotateFinish() {

        //stop rotating
        setRotate(false);

        //update the destination angle
        setAngle(getDestinationAngle());

        //update the current borders
        boolean oldNorth =  new Boolean(hasNorth());
        boolean oldEast =   new Boolean(hasEast());
        boolean oldSouth =  new Boolean(hasSouth());
        boolean oldWest =   new Boolean(hasWest());

        //rotate the borders along with the shape
        setEast(oldNorth);
        setSouth(oldEast);
        setWest(oldSouth);
        setNorth(oldWest);
    }

    private void setRotate(final boolean rotate) {
        this.rotate = rotate;
    }

    public boolean hasRotate() {
        return this.rotate;
    }

    private void setDestinationAngle(final float destinationAngle) {
        this.destinationAngle = destinationAngle;

        if (getDestinationAngle() >= ANGLE_MAX)
            setDestinationAngle(ANGLE_MIN);
    }

    public float getDestinationAngle() {
        return this.destinationAngle;
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


                //rotation speed depends on shape
                switch (getShape()) {

                    case Square:
                        setAngle(getAngle() + ROTATE_VELOCITY_SQUARE);
                        break;

                    case Hexagon:
                        setAngle(getAngle() + ROTATE_VELOCITY_HEXAGON);
                        break;

                    case Diamond:
                        setAngle(getAngle() + ROTATE_VELOCITY_DIAMOND);
                        break;

                    default:
                        throw new RuntimeException("Shape not defined: " + getShape());
                }

                //make sure we stay within range
                if (getAngle() >= ANGLE_MAX)
                    setAngle(ANGLE_MIN);
            }

        }
    }

    @Override
    public void reset() {

    }

    /**
     * Render all objects part of the board
     * @param openGL
     */
    @Override
    public void render(GL10 openGL) {
        super.render(openGL);
    }
}