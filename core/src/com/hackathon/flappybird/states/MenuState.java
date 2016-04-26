package com.hackathon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.flappybird.FlappyBird;

/**
 * Created by umitk  on 24.04.2016.
 */
public class MenuState extends State{

    private Texture backGround;
    private Texture playButton;
    private Texture title;
    private Texture scoreButton;

    private int playButtonX, playButtonY;
    private int scoreButtonX, scoreButtonY;


    private final int playButtonWidth = 115;
    private final int playButtonHeight = 70;
    private final int scoreButtonWidth = 100;
    private final int scoreButtonHeight = 61;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.init();
    }

    public void init(){
        this.backGround = new Texture("fbassets/sky.png");
        this.playButton = new Texture("fbassets/replay.png");
        this.scoreButton = new Texture("fbassets/score.png");
        this.title = new Texture("fbassets/flappyBird.png");
        this.playButtonX = 100;
        this.playButtonY = Gdx.graphics.getHeight() / 2;
        this.scoreButtonX = Gdx.graphics.getWidth() / 2;
        this.scoreButtonY = Gdx.graphics.getHeight() / 2 + 6;
    }


    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            int touchedPosX = (int) input.x;
            int touchedPosY = (int) input.y;
            this.orthographicCamera.unproject(input);
            //System.out.println("x:" + touchedPosX + " y:" + touchedPosY);

            Rectangle touchedArea = new Rectangle(touchedPosX, touchedPosY, 20, 20);
            if (touchedArea.overlaps(this.playButtonBounds())) {

                this.gameStateManager.set(new StartGameState(this.gameStateManager));
            }

        }








    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        this.orthographicCamera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(this.orthographicCamera.combined);

        spriteBatch.begin();
        spriteBatch.draw(this.backGround,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        spriteBatch.draw(this.playButton, this.playButtonX ,this.playButtonY);
        spriteBatch.draw(this.scoreButton, this.scoreButtonX, this.scoreButtonY);
        spriteBatch.draw(this.title, 130,( Gdx.graphics.getHeight()) - 250);

        spriteBatch.end();
    }

    private Rectangle playButtonBounds(){
        return new Rectangle(this.playButtonX, this.playButtonY - this.playButtonHeight, this.playButtonWidth, this.playButtonHeight);
        // rectangle da yukseklik pozisyonu icin buttonun yuksekligini cikar
    }

    private Rectangle scoreButtonBounds(){
        return new Rectangle(this.scoreButtonX, this.scoreButtonY - this.scoreButtonHeight, this.scoreButtonWidth, this.scoreButtonHeight);
    }


    @Override
    public void dispose() {
        this.backGround.dispose();
        this.playButton.dispose();
        this.title.dispose();
    }
}
