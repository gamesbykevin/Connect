package com.gamesbykevin.connect.board;

import com.gamesbykevin.connect.shape.CustomShape;
import com.gamesbykevin.connect.activity.GameActivity;
import com.gamesbykevin.connect.common.ICommon;
import com.gamesbykevin.connect.opengl.Textures;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin on 8/1/2017.
 */

public class Board implements ICommon {

    private List<CustomShape> shapes;

    public enum Shape {
        Square, Hexagon, Triangle
    }

    /**
     * Default constructor
     */
    public Board() {
        addShape(Shape.Square, 50, 50);
        addShape(Shape.Hexagon, 50, 400);
        addShape(Shape.Triangle, 250, 250);
    }

    public final void addShape(Shape shape, float x, float y) {

        CustomShape tmp = new CustomShape(shape);

        tmp.setX(x);
        tmp.setY(y);

        switch (tmp.getShape()) {

            case Square:
                tmp.setTextureId(Textures.TEXTURE_ID_SQUARE);
                break;

            case Hexagon:
                tmp.setTextureId(Textures.TEXTURE_ID_HEXAGON);
                break;

            case Triangle:
                tmp.setTextureId(Textures.TEXTURE_ID_TRIANGLE);
                break;

            default:
                throw new RuntimeException("Shape not defined: " + shape.toString());
        }

        getShapes().add(tmp);
    }

    private List<CustomShape> getShapes() {

        if (this.shapes == null)
            this.shapes = new ArrayList<>();

        return this.shapes;
    }

    public void touch(float x, float y) {
        for (int i = 0; i < getShapes().size(); i++) {

            CustomShape shape = getShapes().get(i);

            if (shape != null && shape.contains(x, y) && !shape.hasRotate())
                shape.rotate();
        }
    }

    @Override
    public void dispose() {

        if (getShapes() != null) {
            for (int i = 0; i < getShapes().size(); i++) {
                if (getShapes().get(i) != null) {
                    getShapes().get(i).dispose();
                    getShapes().set(i, null);
                }
            }

            getShapes().clear();
            shapes = null;
        }
    }

    @Override
    public void update(GameActivity activity) {

        for (int i = 0; i < getShapes().size(); i++) {

            CustomShape shape = getShapes().get(i);

            shape.update(activity);
        }
    }

    @Override
    public void reset() {

        for (int i = 0; i < getShapes().size(); i++) {
            getShapes().get(i).reset();
        }
    }

    /**
     * Render all objects part of the board
     * @param openGL
     */
    @Override
    public void render(GL10 openGL) {

        for (int i = 0; i < getShapes().size(); i++) {
            getShapes().get(i).render(openGL);
        }
    }
}