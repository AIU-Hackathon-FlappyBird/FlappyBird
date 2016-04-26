package com.hackathon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by koray on 24.04.2016.
 */
public class StartGameState extends State {
    private Texture getReady;
    private Texture background;
    protected StartGameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.init();
    }

    public void init(){
        this.background = new Texture("fbassets/sky.png");
        this.getReady = new Texture("fbassets/getready.png");
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            this.gameStateManager.set(new PlayState(this.gameStateManager));
            this.dispose();
        }
    }

    @Override
    public void update(float deltaTime) {
        this.handleInput();
        this.orthographicCamera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(this.orthographicCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(this.background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        spriteBatch.draw(this.getReady, (Gdx.graphics.getWidth() - this.getReady.getWidth() * 2) / 2,(Gdx.graphics.getHeight() - this.getReady.getHeight() * 2) / 2 , this.getReady.getWidth() * 2, this.getReady.getHeight() * 2);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.getReady.dispose();

    }
}
