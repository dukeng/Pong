package Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Paddle {
	private int speed = 50;

	public Player(Texture text, float x, float y) {
		super(text, x, y);
	}

	private void getInput(float delta) {
		movingUp=false;
		movingDown=false;
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			movingUp = true;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			movingDown = true;
		}
	}

	@Override
	public void update(float delta) {
		getInput(delta);// change velocity due to delta, see above funciton
		super.update(delta);
		// checkBorder(delta);

	}

}
