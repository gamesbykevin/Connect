package com.gamesbykevin.connect.shape;

import com.gamesbykevin.androidframeworkv2.base.Entity;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.common.ICommon;

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

    public enum Connections {
        N(180), S(0), E(270), W(90),
        NE(270), NW(180), SE(0), SW(90), NS(0), WE(90),
        NSW(90), NSE(270), WEN(180), WES(0),
        NSEW(0);

        private final float angleAdjustment;

        private Connections(float angleAdjustment) {
            this.angleAdjustment = angleAdjustment;
        }

        public float getAngleAdjustment() {
            return this.angleAdjustment;
        }
    }

    private Connections connection;

    /**
     * Default constructor
     */
    public CustomShape(final Board.Shape shape, final Connections connection) {

        super();

        this.shape = shape;
        this.connection = connection;

        super.setWidth(DEFAULT_DIMENSION);
        super.setHeight(DEFAULT_DIMENSION);
    }

    public Connections getConnection() {
        return this.connection;
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
                setRotate(false);

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