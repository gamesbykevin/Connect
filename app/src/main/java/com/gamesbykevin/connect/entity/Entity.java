package com.gamesbykevin.connect.entity;

import android.graphics.PointF;
import android.graphics.RectF;

import com.gamesbykevin.androidframeworkv2.base.Cell;

/**
 * Created by Kevin on 8/1/2017.
 */
public class Entity extends Cell {

    //current facing angle of the entity
    private float angle;

    //the size of the entity
    private float scale;

    //base rectangle
    private RectF base;

    //where do we render the entity
    private PointF translation;

    //full transparency that we can't see through
    private static final float TRANSPARENCY_OPAQUE = 1.0f;

    //the level of transparency when we render, start out will full transparency
    private float transparency = TRANSPARENCY_OPAQUE;

    //texture id so we know what texture to render
    private int textureId;

    //the coordinates used for rotation
    private PointF nw = new PointF(), ne = new PointF(), sw = new PointF(), se = new PointF();

    //the vertices of our entity
    private float[] vertices = new float[12];

    /**
     * Default constructor
     */
    public Entity() {

        //call default parent constructor
        super();

        //create our rectangle with the new coordinates
        this.base = new RectF();

        //where do we draw the entity, start so base rectangle is at origin (0,0);
        this.translation = new PointF();

        //size of the entity
        setScale(1f);

        //start rotation
        setAngle(0f);
    }

    private PointF getTranslation() {
        return this.translation;
    }

    private RectF getBase() {
        return this.base;
    }

    public void setScale(final float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return this.scale;
    }

    /**
     * Set the transparency of this entity
     * @param transparency 1.0 fully opaque, 0.0 invisible
     */
    public void setTransparency(final float transparency) {
        this.transparency = transparency;
    }

    public float getTransparency() {
        return this.transparency;
    }

    /**
     * Assign the facing angle
     * @param angle The facing angle in degrees
     */
    public void setAngle(final float angle) {
        this.angle = angle;
    }

    /**
     * Get the facing angle
     * @return The facing angle in degrees
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * Assign the texture id
     * @param textureId The unique id of the texture we want to render
     */
    public void setTextureId(final int textureId) {
        this.textureId = textureId;
    }

    /**
     * Get the texture id
     * @return The unique id of the texture we want to render
     */
    public int getTextureId() {
        return this.textureId;
    }

    /**
     * Get the distance
     * @param entity The entity we want to compare
     * @return The distance between the current and specified entities
     */
    public double getDistance(final Entity entity)
    {
        return getDistance(entity.getX(), entity.getY());
    }

    /**
     * Get the distance
     * @param x x-coordinate
     * @param y y-coordinate
     * @return The distance between the entity and specified (x,y)
     */
    public double getDistance(final double x, final double y)
    {
        return getDistance(x, y, getX(), getY());
    }

    /**
     * Get the distance
     * @param x1 x-coordinate
     * @param y1 y-coordinate
     * @param x2 x-coordinate
     * @param y2 y-coordinate
     * @return The distance between the 2 specified (x,y) coordinates
     */
    public static double getDistance(final double x1, final double y1, final double x2, final double y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Assign the x-coordinate
     * @param x the desired x-coordinate
     */
    public void setX(final float x)
    {
        getTranslation().x = x;
    }

    /**
     * Assign the y-coordinate
     * @param y the desired y-coordinate
     */
    public void setY(final float y)
    {
        getTranslation().y = y;
    }

    /**
     * Get the x-coordinate
     * @return the x-coordinate
     */
    public float getX()
    {
        return getTranslation().x;
    }

    /**
     * Get the y-coordinate
     * @return the y-coordinate
     */
    public float getY()
    {
        return getTranslation().y;
    }

    /**
     * Get the width
     * @return get the width
     */
    public float getWidth()
    {
        return (getBase().right - getBase().left);
    }

    /**
     * Get the height
     * @return get the height
     */
    public float getHeight()
    {
        return (getBase().bottom - getBase().top);
    }

    /**
     * Assign the width
     * @param entity The object containing the width
     */
    public void setWidth(final Entity entity)
    {
        setWidth(entity.getWidth());
    }

    /**
     * Assign the width
     * @param w The desired width
     */
    public void setWidth(final float w)
    {
        float half = (w / 2);

        //update the rectangle coordinates
        getBase().left = -half;
        getBase().right = half;
    }

    /**
     * Assign the height
     * @param entity The object containing the height
     */
    public void setHeight(final Entity entity)
    {
        setHeight(entity.getHeight());
    }

    /**
     * Assign the height
     * @param h The desired height
     */
    public void setHeight(final float h)
    {
        float half = (h / 2);

        //update the rectangle coordinates
        getBase().top = -half;
        getBase().bottom = half;
    }

    public float[] getVertices() {
        return this.vertices;
    }

    public float[] getTransformedVertices() {

        //start scaling
        float x1 = getBase().left * getScale();
        float x2 = getBase().right * getScale();
        float y1 = getBase().bottom * getScale();
        float y2 = getBase().top * getScale();

        //now we need to detach from the points so we can start rotating
        nw.x = x1; nw.y = y2;
        sw.x = x1; sw.y = y1;
        se.x = x2; se.y = y1;
        ne.x = x2; ne.y = y2;

        //convert to radians
        double radians = Math.toRadians(getAngle());

        //calculate this once, so we don't have to do it every time
        float s = (float) Math.sin(radians);
        float c = (float) Math.cos(radians);

        //rotate each point
        nw.x = x1 * c - y2 * s;
        nw.y = x1 * s + y2 * c;
        sw.x = x1 * c - y1 * s;
        sw.y = x1 * s + y1 * c;
        se.x = x2 * c - y1 * s;
        se.y = x2 * s + y1 * c;
        ne.x = x2 * c - y2 * s;
        ne.y = x2 * s + y2 * c;

        //offset so we are back at the designated location
        float tmpX = (getWidth() / 2) + getTranslation().x;
        float tmpY = (getHeight() / 2) + getTranslation().y;

        //now translate all 4 locations to the correct location
        nw.x += tmpX; nw.y += tmpY;
        sw.x += tmpX; sw.y += tmpY;
        se.x += tmpX; se.y += tmpY;
        ne.x += tmpX; ne.y += tmpY;

        //update our vertices with the new coordinates
        getVertices()[0] = nw.x; getVertices()[1] = nw.y;  getVertices()[2] = 0.0f;
        getVertices()[3] = sw.x; getVertices()[4] = sw.y;  getVertices()[5] = 0.0f;
        getVertices()[6] = se.x; getVertices()[7] = se.y;  getVertices()[8] = 0.0f;
        getVertices()[9] = ne.x; getVertices()[10] = ne.y; getVertices()[11] = 0.0f;

        //return our calculated vertices
        return getVertices();
    }
}