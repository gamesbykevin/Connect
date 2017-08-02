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

    private static final int DEFAULT_DIMENSION = 175;

    //are we rotating
    private boolean rotate = false;

    //where do we want to rotate to
    private float destinationAngle;

    /**
     * How many degrees do we rotate the shape each time
     */
    public static final float ROTATE_VELOCITY_SQUARE = 6.0f;
    public static final float ROTATE_VELOCITY_HEXAGON = 4.0f;
    public static final float ROTATE_VELOCITY_TRIANGLE = 8.0f;

    /**
     * How many degrees is each rotation for the given shape
     */
    public static final float ROTATION_ANGLE_SQUARE = 90.0f;
    public static final float ROTATION_ANGLE_HEXAGON = 60.0f;
    public static final float ROTATION_ANGLE_TRIANGLE = 120.0f;

    /**
     * The largest angle allowed
     */
    public static final float ANGLE_MAX = 360.0f;

    /**
     * The smallest angle allowed
     */
    public static final float ANGLE_MIN = 0.0f;

    private final Board.Shape shape;

    /**
     * Default constructor
     */
    public CustomShape(final Board.Shape shape) {
        super();

        this.shape = shape;

        super.setWidth(DEFAULT_DIMENSION);
        super.setHeight(DEFAULT_DIMENSION);
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

            case Triangle:
                setDestinationAngle(getAngle() + ROTATION_ANGLE_TRIANGLE);
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

                    case Triangle:
                        setAngle(getAngle() + ROTATE_VELOCITY_TRIANGLE);
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