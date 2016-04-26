package com.hackathon.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hackathon.flappybird.extras.SoundSystem;

/**
 * Created by koray on 24.04.2016.
 */
public class Bird {

    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -12;
    private Vector3 birdPosition;
    private Vector3 birdVelocity;
    private Rectangle birdBound;
    private Animation birdAnimation;
    private Texture texture;
    private SoundSystem soundSystem;
    //private Texture bird;

    public  Bird(int x, int y){

        this.birdPosition = new Vector3(x, y, 0);
        this.birdVelocity = new Vector3(0, 0, 0);
        texture = new Texture("fbassets/birdanimation.png");
        this.birdAnimation = new Animation(new TextureRegion(texture), 3, 0.4f);
        this.birdBound = new Rectangle(this.birdPosition.x, this.birdPosition.y, texture.getWidth() / 3, texture.getHeight());
        this.soundSystem = new SoundSystem();
    }

    public void update(float dt){
        this.birdAnimation.update(dt);
        if(this.birdPosition.y > 0){
            this.birdVelocity.add(0, GRAVITY, 0);
        }

        //this.birdVelocity.add(0, GRAVITY, 0);
        this.birdVelocity.scl(dt);
        this.birdPosition.add(MOVEMENT * dt, this.birdVelocity.y, 0);
        this.birdVelocity.scl(1 / dt);

        if (this.birdPosition.y < 0){
            this.birdPosition.y = 0;
        }
        this.birdBound.setPosition(this.birdPosition.x, this.birdPosition.y);
    }

    public Vector3 getBirdPosition() {
        return birdPosition;
    }
    public TextureRegion getBird() {
        return this.birdAnimation.getFrame();
    }



    public void jump(){
        birdVelocity.y = 240;
        this.soundSystem.getWing().play();
    }

    public Rectangle getBounds(){
        return birdBound;
    }

    public void dispose(){
        this.texture.dispose();
        this.soundSystem.dispose();
    }








}
