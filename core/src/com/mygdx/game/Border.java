package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Border extends GameObject {

	Border(Texture text, float x, float y) {
		super(text, x, y);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}

	@Override
	protected void collidedWith(GameObject a) {
		// TODO Auto-generated method stub
		
	}

}
