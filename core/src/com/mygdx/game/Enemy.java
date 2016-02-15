package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Paddle {
	private int speed = 10;
	

	public Enemy(Texture text, float x, float y) {
		super(text, x, y);
	}

	public void moveToBall(GameObject a) {
		if(a.velocity.y>0){
			velocity.y=a.velocity.y*4/5;
		}
		else if (a.velocity.y<0){
			velocity.y=a.velocity.y*4/5;
		}
	}
	
	protected void collidedWith(GameObject a) {
		if (a instanceof Border) {
			if (velocity.y > 0) {
				position.y = a.position.y - getHeight();
			} else if (velocity.y <= 0) {
				position.y = a.position.y + a.getHeight();
			}
			velocity.y=0;
		}
	}

	@Override
	public void update(float delta) {
		
		super.update(delta);
	}

}
