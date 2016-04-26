package com.hackathon.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by umitk  on 24.04.2016.
 */
public class GameStateManager{
    private Stack<State> states;

    public GameStateManager(){

        this.states = new Stack<State>();

    }


    public void push(State state) {

        this.states.push(state);
    }
    public void pop(){
        this.states.pop().dispose();
    }
    public void set(State state){
        this.states.pop().dispose();
        this.states.push(state);
    }


    public void update(float deltaTime) {
        this.states.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch) {
        this.states.peek().render(spriteBatch);
    }

    public void dispose() {
        this.states.peek().dispose();
    }

}
