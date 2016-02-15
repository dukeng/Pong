package Pong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	protected int height;
	protected int width;
	protected Vector2 position;
	protected Texture text;
	protected Vector2 velocity;

	public GameObject(Texture text, float x, float y) {
		this.text = text;
		position = new Vector2();
		position.x = x;
		position.y = y;
		velocity = new Vector2();
		height = text.getHeight();
		width = text.getWidth();
	}

	public void update(float delta) {
		position.add(velocity.x, velocity.y);
	}

	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), getPosition().x, getPosition().y);

	}

	public void checkCollision(GameObject a) {
		if (overLaps(a)) {
			collidedWith(a);
			//System.out.println("checked");
		}
	}
	
	protected abstract void collidedWith(GameObject a);	
	
	public boolean overLaps(GameObject a) {
		return position.x < a.position.x + a.getWidth() && position.x + getWidth() > a.position.x
				&& position.y < a.position.y + a.getHeight() && position.y + getHeight() > a.position.y;
		// return x < r.x + r.width && x + width > r.x && y < r.y + r.height &&
		// y + height > r.y;
	}

	public float getWidth() {
		return width; // get the width of the box
	}

	public float getHeight() {
		return height; // get the height of the box
	}

	public Vector2 getPosition() {
		return position; // get x and y component
	}

	public void addVelocity(float x, float y) {
		velocity.x += x;
		velocity.y += y;
	}

	public Texture getTexture() {
		return text;
	}
}
