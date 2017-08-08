package com.gamesbykevin.connect.entity;

import com.gamesbykevin.connect.shape.CustomShape;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kevin on 8/1/2017.
 */
public class Entity extends com.gamesbykevin.androidframeworkv2.base.Entity {

    public Entity() {
        super();
    }

    @Override
    public void dispose() {
        //clean up anything here
    }

    public void render(CustomShape shape, GL10 openGL) {
        super.setTextureId(shape.getTextureIdPipe());
        super.setX(shape);
        super.setY(shape);
        super.setWidth(shape);
        super.setHeight(shape);
        super.setAngle(shape.getAngle() + shape.getAnglePipe());
        super.render(openGL);
    }
}
