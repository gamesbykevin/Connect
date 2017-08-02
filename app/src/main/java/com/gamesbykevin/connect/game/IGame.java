package com.gamesbykevin.connect.game;

import com.gamesbykevin.androidframeworkv2.base.Disposable;

/**
 * Game interface methods
 * @author GOD
 */
public interface IGame extends Disposable
{
    /**
     * Logic to update element
     */
    public void update() throws Exception;
    
    /**
     * Update the game based on a motion event
     * @param action The action of the MotionEvent
     * @param x (x-coordinate)
     * @param y (y-coordinate)
     * @throws Exception
     * @return true if we want to keep receiving events, false otherwise
     */
    public boolean onTouchEvent(final int action, final float x, final float y) throws Exception;
}