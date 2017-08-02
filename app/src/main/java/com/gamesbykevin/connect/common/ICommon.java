package com.gamesbykevin.connect.common;

import com.gamesbykevin.androidframeworkv2.base.Disposable;
import com.gamesbykevin.connect.activity.GameActivity;

import javax.microedition.khronos.opengles.GL10;

public interface ICommon extends com.gamesbykevin.androidframeworkv2.common.ICommon
{
	/**
	 * Update the entity
	 */
	public void update(GameActivity activity);

	/**
	 * Logic to reset
	 */
	public void reset();
}