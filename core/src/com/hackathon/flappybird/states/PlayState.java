package com.hackathon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.hackathon.flappybird.extras.ScoreSystem;
import com.hackathon.flappybird.extras.SoundSystem;
import com.hackathon.flappybird.sprites.Bird;
import com.hackathon.flappybird.sprites.Pipe;


import com.hackathon.flappybird.settings.Settings;

/**
 * Created by umitk  on 24.04.2016.
 */
public class PlayState extends State {
    private int score = 0;
    private static final int PIPE_SPACING = 125;
    private static final int PIPE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -40;

    private Bird bird;
    private  Texture background;
    private Texture ground;
    private Vector2 positionGround1, positionGround2;
    private SoundSystem soundSystem;
    private Pipe  temp;
    private Array<Pipe> pipes;
    private ScoreSystem scoreSystem;



    protected PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.orthographicCamera.setToOrtho(false, Settings.WIDTH / 2, Settings.HEIGHT / 2);

        this.background = new Texture("fbassets/sky.png");
        this.ground = new Texture("fbassets/ground.png");


        this.positionGround1 = new Vector2(this.orthographicCamera.position.x - this.orthographicCamera.viewportWidth / 2, this.GROUND_Y_OFFSET);
        this.positionGround2 = new Vector2((this.orthographicCamera.position.x - this.orthographicCamera.viewportWidth / 2) + ground.getWidth(), this.GROUND_Y_OFFSET);

        this.bird = new Bird(50, 300);

        this.pipes = new Array<Pipe>();

        for (int i = 1; i <= PIPE_COUNT; i++) {
            this.pipes.add(new Pipe(i * (PIPE_SPACING + Pipe.PIPE_WIDTH)));
        }
        this.temp = this.pipes.get(1);
        this.scoreSystem = new ScoreSystem();
        soundSystem = new SoundSystem();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.bird.jump();
        }
    }

    @Override
    public void update(float deltaTime)  {
        this.handleInput();
        this.updateGround();

        this.bird.update(deltaTime);
        this.orthographicCamera.position.x = bird.getBirdPosition().x + 80;

        for (int i = 0; i < this.pipes.size; i++){
            Pipe pipe = this.pipes.get(i);
            if (this.orthographicCamera.position.x - (this.orthographicCamera.viewportWidth / 2) > pipe.getPositionTopPipe().x + pipe.getTopPipe().getWidth()){
                pipe.reposition(pipe.getPositionTopPipe().x + ((Pipe.PIPE_WIDTH + PIPE_SPACING) * PIPE_COUNT));
            }
            if (pipe.collide(this.bird.getBounds())){

                this.soundSystem.getHit().play();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.gameStateManager.set(new GameOverState(this.gameStateManager, score));

            }

            if (this.isPassed(pipe)){

                this.soundSystem.getPoint().play();
            }

        }
        if (this.bird.getBirdPosition().y <= this.ground.getHeight() + this.GROUND_Y_OFFSET){

            this.soundSystem.getHit().play();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.gameStateManager.set(new GameOverState(this.gameStateManager, score));

        }

        this.orthographicCamera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(this.orthographicCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(this.background, this.orthographicCamera.position.x - (this.orthographicCamera.viewportWidth / 2), 0);
        spriteBatch.draw(this.bird.getBird(), this.bird.getBirdPosition().x, this.bird.getBirdPosition().y);


        for (Pipe pipe : this.pipes) {
            spriteBatch.draw(pipe.getTopPipe(),    pipe.getPositionTopPipe().x,     pipe.getPositionTopPipe().y);
            spriteBatch.draw(pipe.getBottomPipe(), pipe.getPositionBottomPipe().x,  pipe.getPositionBottomPipe().y);
        }



        spriteBatch.draw(this.scoreSystem.zeroth(), this.bird.getBirdPosition().x + 45 , this.orthographicCamera.viewportHeight - this.scoreSystem.zeroth().getHeight() - 1);
        spriteBatch.draw(this.scoreSystem.first(), this.bird.getBirdPosition().x + 50 + this.scoreSystem.first().getWidth() , this.orthographicCamera.viewportHeight - this.scoreSystem.zeroth().getHeight() - 1);
        spriteBatch.draw(this.scoreSystem.second(), this.bird.getBirdPosition().x + 55 + this.scoreSystem.first().getWidth() * 2, this.orthographicCamera.viewportHeight - this.scoreSystem.zeroth().getHeight() - 1);
        spriteBatch.draw(this.scoreSystem.thirth(), this.bird.getBirdPosition().x + 60 + this.scoreSystem.first().getWidth() * 3, this.orthographicCamera.viewportHeight - this.scoreSystem.zeroth().getHeight() - 1);
        spriteBatch.draw(this.ground, positionGround1.x,positionGround1.y);
        spriteBatch.draw(this.ground, positionGround2.x,positionGround2.y);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        this.background.dispose();

        this.bird.dispose();
        for (Pipe pipe: this.pipes){
            pipe.dispose();
        }
        this.ground.dispose();
        this.soundSystem.dispose();
    }



    private void updateGround(){
        if (this.orthographicCamera.position.x - (this.orthographicCamera.viewportWidth / 2) > this.positionGround1.x + this.ground.getWidth()){
            positionGround1.add(this.ground.getWidth() * 2, 0);
        }

        if (this.orthographicCamera.position.x - (this.orthographicCamera.viewportWidth / 2) > this.positionGround2.x + this.ground.getWidth()){
            positionGround2.add(this.ground.getWidth() * 2, 0);
        }
    }

    private boolean isPassed(Pipe pipe){
        if (bird.getBirdPosition().x > pipe.getPositionTopPipe().x && this.temp != pipe){
            this.temp = pipe;
            this.score++;
            this.scoreSystem.splitScore(score);
            return true;
        }
        return false;
    }


}
