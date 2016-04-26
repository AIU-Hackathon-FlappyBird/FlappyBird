package com.hackathon.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by umitk on 24.04.2016.
 */
public abstract class State {
    protected OrthographicCamera orthographicCamera;
    protected Vector3 input;
    protected GameStateManager gameStateManager;


    protected State(GameStateManager gameStateManager){

        this.gameStateManager = gameStateManager;
        this.orthographicCamera = new OrthographicCamera();
        this.input = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();


}
