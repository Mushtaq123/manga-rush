package com.mangarush.ui.actors;

import static com.mangarush.ui.utils.B2DVars.PPM;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mangarush.constants.Paths;
import com.mangarush.ui.Game;
import com.mangarush.ui.utils.B2DVars;

/**
 * Abstract actor for a character on screen (player or enemy)
 */
public abstract class Character extends BodyActor {
	protected enum State {
		RUN, JUMP, FALL, LANDED, THROW, DEAD
	}

	// State
	private boolean alive;

	// Sprites and animations
	private final int characterId;
	protected final TextureAtlas atlas;
	protected float stateTime;

	/** Takes the ID and the world to add body in and its initial position */
	public Character(final int characterId, final World world, final Vector2 position) {
		super(world, position, 45, 50);

		// State
		alive = true;

		// Textures
		this.characterId = characterId;
		atlas = Game.GDXVars().getTextureAtlas(Paths.charactersAtlases[characterId]);
		stateTime = 0f;

		// Default bounds
		setBounds(0, 0, 45, 50);
	}

	/** Init B2D body */
	@Override
	protected void initBody(final World world, final Vector2 position, final float width, final float height) {
		// Object instanciate
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape ps;

		// body
		bdef.position.set(position);
		bdef.type = BodyType.DynamicBody; // To dynamic
		bdef.fixedRotation = true;
		body = world.createBody(bdef);
		body.setUserData(this); // Set userData as player to retrieve in contact listener

		// fixture
		fdef.shape = ps = new PolygonShape();
		fdef.friction = 0;
		fdef.density = 1;
		fdef.filter.categoryBits = B2DVars.PLAYER_MASK;
		fdef.filter.maskBits = B2DVars.GROUND_MASK;
		ps.setAsBox(width / 2f / PPM, height / 2f / PPM);
		body.createFixture(fdef);
		ps.dispose();
	}

	// Force override
	@Override
	public abstract void draw(Batch batch, float parentAlpha);

	/**
	 * Draw frame at (getCenterX() - frame.getRegionWidth() / 2f, getY()) with
	 * the given batch
	 */
	public void draw(Batch batch, float parentAlpha, TextureRegion frame) {
		// Draw
		batch.draw(frame, getCenterX() - frame.getRegionWidth() / 2f, getY());
	}

	@Override
	public void act(float delta) {
		// Update animation stateTime
		stateTime += delta;

		// Call added actions
		super.act(delta);
	}

	public int getCharacterId() {
		return characterId;
	}

	/** Return true if character is alive */
	public boolean isAlive() {
		return alive;
	}

	/** Set character alive or not */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
