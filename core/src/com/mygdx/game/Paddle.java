package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paddle extends GameObject {
	private int speed = 50;
	protected boolean movingUp = false;
	protected boolean movingDown = false;

	public Paddle(Texture text, float x, float y) {
		super(text, x, y);
		
	}


	protected void collidedWith(GameObject a) {
		if (a instanceof Border) {
			if (velocity.y > 0) {
				position.y = a.position.y - getHeight();

			} else if (velocity.y < 0) {
				position.y = a.position.y + a.getHeight();
			}
			velocity.y = 0;
		}
	}
	/*
	 * private void getInput(float delta) { if (Gdx.input.isKeyPressed(Keys.UP))
	 * {
	 * 
	 * addVelocity(0, speed * delta); } if (Gdx.input.isKeyPressed(Keys.DOWN)) {
	 * addVelocity(0, -speed * delta); }
	 * 
	 * }
	 */

	@Override
	public void update(float delta) {
		if (movingUp) {
			velocity.add(0, speed * delta);
		}
		if (movingDown) {
			velocity.add(0, -speed * delta);
		}
		if (!movingUp && !movingDown) {
			velocity.y *= (float) Math.pow(0.001, delta);
		}
		super.update(delta);
	}

}
