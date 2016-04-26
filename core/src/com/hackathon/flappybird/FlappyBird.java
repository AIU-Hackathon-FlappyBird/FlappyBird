package com.hackathon.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackathon.flappybird.states.GameStateManager;
import com.hackathon.flappybird.states.MenuState;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	GameStateManager gameStateManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.gameStateManager = new GameStateManager();
		this.gameStateManager.push(new MenuState(this.gameStateManager));
	}

	@Override
	public void render () {
		this.gameStateManager.update(Gdx.graphics.getDeltaTime());
		this.gameStateManager.render(this.batch);
	}
	@Override
	public void dispose(){
		this.gameStateManager.dispose();
	}
}
