package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

public class Ball extends GameObject {
	private float timer = 0;
	private Random rand = new Random();
	private int randomSpeed;
	private int randomDirection;
	private float impactPoint;
	private Sound hitBorder;
	private Sound hitPaddle;
	private Sound Out = null;
	private float constant;

	public Ball(Texture text, float x, float y) {
		super(text, x, y);
		velocity.x = -6;
		velocity.y = 6;
		hitBorder = Gdx.audio.newSound(Gdx.files.internal("sound/hitBorder.ogg"));
		hitPaddle = Gdx.audio.newSound(Gdx.files.internal("sound/hitPaddle.ogg"));
		Out = Gdx.audio.newSound(Gdx.files.internal("sound/Out.ogg"));
	}

	private void random() {
		randomSpeed = rand.nextInt(3) + 5;
		if (rand.nextBoolean() == true) {
			randomDirection = 1;
		} else {
			randomDirection = -1;
		}
	}

	public void outOfBound() {
		if (position.x < -100 || position.x + getWidth() > 800) {
			position.x = 250;
			position.y = 250;
			random();
			velocity.x = randomDirection * randomSpeed;
			random();
			velocity.y = randomDirection * randomSpeed;
			Out.play(0.7f);
		}
	}

	protected void collidedWith(GameObject a) {
		if (a instanceof Paddle) {
			if (timer > 0.3 && overLaps(a)) {
				returnImpact(a);
				timer = 0;
				hitPaddle.play(0.7f);
			}
		} else if (a instanceof Border) {
			if (overLaps(a)) {
				velocity.y = -velocity.y;
				hitBorder.play(0.7f);
			}
		}
	}

	private void returnImpact(GameObject a) {
		// impact on half down of the paddle
		impactPoint = position.y + height / 2;
		float midPoint = a.position.y + a.getHeight() / 2;
		double z = Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y);
		int sign = (int) Math.signum(velocity.x);
		if (impactPoint < midPoint) {
			constant = (float) ((midPoint - impactPoint) / a.getHeight() / 2 * 150);
			System.out.println(constant);
			// if the angle is upward
			velocity.x = -(float) (sign * z * Math.cos(Math.toRadians(constant)));
			if (velocity.y > 0) {
				velocity.y = -(float) (z * Math.sin(Math.toRadians(constant)));
			}
			// if the angle is downward
			else if (velocity.y <= 0) {
				velocity.y = (float) (z * Math.sin(Math.toRadians(constant)));
			}
		}
		// at half point
		else if (impactPoint == a.position.y + a.getHeight() / 2) {
			velocity.x = -velocity.x;
			velocity.y = 0;
			// above half point
		} else if (impactPoint > a.position.y + a.getHeight() / 2) {
			constant = (float) ((impactPoint - midPoint) / a.getHeight() / 2 * 150);
			System.out.println(constant);
			velocity.x = (float) (-sign * z * Math.cos(Math.toRadians(constant)));
			if (velocity.y < 0) {
				velocity.y = (float) (z * Math.sin(Math.toRadians(constant)));

			} else if (velocity.y >= 0) {
				velocity.y = -(float) (z * Math.sin(Math.toRadians(constant)));
			}
		}
		// System.out.print(velocity.x);
		// System.out.print(velocity.y);
	}

	@Override
	public void update(float delta) {
		timer += delta;
		super.update(delta);
		outOfBound();

	}

}
