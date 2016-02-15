package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private Ball ball;
	private Player player;
	private Border borderTop;
	private Border borderBot;
	private Enemy enemy;

	@Override
	public void create() {
		batch = new SpriteBatch();
		
		//get Texture info
		Texture paddleText = new Texture("paddle.png");
		Texture ballText = new Texture("ball.png");
		Texture borderText = new Texture("Border.png");
		
		//set objects
		player = new Player(paddleText, 75, 175);
		ball = new Ball(ballText, 175, 175);
		borderTop = new Border(borderText, 0, 450);
		borderBot = new Border(borderText, 0, 0);
		enemy = new Enemy(paddleText, 540, 175);
		gameObjects.add(player);
		gameObjects.add(borderBot);
		gameObjects.add(borderTop);
		gameObjects.add(ball);
		gameObjects.add(enemy);
		
	}

	@Override
	public void render() {
		// get delta
		float delta = Gdx.graphics.getDeltaTime();

		// update
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).update(delta);
		}
		// check collision enemy with border
		for (int k = 0; k < gameObjects.size(); k++) {
			if (gameObjects.get(k) instanceof Border) {
				enemy.checkCollision(gameObjects.get(k));
			}
		}
		// enemy motion according to Ball
		enemy.moveToBall(ball);

		// check collision for ball
		for (int k = 0; k < gameObjects.size(); k++) {
			if (!(gameObjects.get(k) instanceof Ball)) {
				
				ball.checkCollision(gameObjects.get(k));

			}

		}

		// check collision Border for player
		for (int k = 0; k < gameObjects.size(); k++) {
			if (gameObjects.get(k).equals(borderBot)) {
				player.checkCollision(gameObjects.get(k));
			}
			if (gameObjects.get(k).equals(borderTop)) {
				player.checkCollision(gameObjects.get(k));
			}
		}

		// render
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).render(batch);
		}
		batch.end();
	}
}
