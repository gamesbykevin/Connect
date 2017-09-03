package com.gamesbykevin.connect.base;

import static com.gamesbykevin.connect.shape.CustomShape.ANGLE_MAX;

/**
 * Created by Kevin on 9/2/2017.
 */
public class Entity extends com.gamesbykevin.androidframeworkv2.base.Entity {

    @Override
    public void setAngle(final float angle) {

        super.setAngle(angle);

        if (getAngle() >= ANGLE_MAX)
            this.setAngle(getAngle() - ANGLE_MAX);

    }
}
