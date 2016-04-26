package com.hackathon.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by mehmet on 24.04.2016.
 */
public class Pipe {
    public static final int PIPE_WIDTH = 52;
    private  static final int FLUCTUATION = 130;
    private  static final int TUBE_GAP = 100;
    private  static final int LOWEST_OPENING = 120;
    private Texture topPipe;
    private Texture bottomPipe;
    private Vector2 positionTopPipe, positionBottomPipe;
    private Random random;
    private Rectangle boundsTop, boundsBottom;


    public Pipe(float x){
        this.topPipe = new Texture("fbassets/topPipe.png");
        this.bottomPipe = new Texture("fbassets/bottomPipe.png");

        this.random = new Random();
        positionTopPipe = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomPipe = new Vector2(x, positionTopPipe.y - TUBE_GAP - this.bottomPipe.getHeight());

        this.boundsTop = new Rectangle(this.positionTopPipe.x, positionTopPipe.y, this.topPipe.getWidth(), this.topPipe.getHeight());
        this.boundsBottom = new Rectangle(this.positionBottomPipe.x, this.positionBottomPipe.y, this.bottomPipe.getWidth(), this.bottomPipe.getHeight());




    }

    public Texture getTopPipe() {
        return topPipe;
    }

    public Texture getBottomPipe() {
        return bottomPipe;
    }

    public Vector2 getPositionTopPipe() {
        return positionTopPipe;
    }

    public Vector2 getPositionBottomPipe() {
        return positionBottomPipe;
    }


    public void reposition(float x){
        positionTopPipe.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomPipe.set(x, positionTopPipe.y - TUBE_GAP - this.bottomPipe.getHeight());
        this.boundsTop.setPosition(this.positionTopPipe.x, this.positionTopPipe.y);
        this.boundsBottom.setPosition(this.positionBottomPipe.x, this.positionBottomPipe.y );
    }


    public boolean collide(Rectangle birdBound){
        return birdBound.overlaps(this.boundsTop) || birdBound.overlaps(this.boundsBottom);
    }
    public void dispose(){
        this.topPipe.dispose();
        this.bottomPipe.dispose();
    }


}
