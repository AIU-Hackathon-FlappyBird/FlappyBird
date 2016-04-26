package com.hackathon.flappybird.extras;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by mehmet on 24.04.2016.
 */
public class SoundSystem {

    private Sound wing, die, hit, point;


    public SoundSystem(){
        this.die = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_die.ogg"));
        this.hit = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_hit.ogg"));
        this.point = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_point.ogg"));
        this.wing =  Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_wing.ogg"));


    }


    public Sound getDie() {
        return die;
    }

    public Sound getWing() {
        return wing;
    }

    public Sound getHit() {
        return hit;
    }

    public Sound getPoint() {
        return point;
    }

    public void dispose(){

        this.getDie().dispose();
        this.getHit().dispose();
        this.getPoint().dispose();
        this.getWing().dispose();
    }
}
