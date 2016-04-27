package com.hackathon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hackathon.flappybird.extras.ScoreSystem;

/**
 * Created by umitk on 24.04.2016.
 */
public class GameOverState extends State {

    private Texture background;
    private Texture gameover;
    private Texture replayButton;
    private final int Y_OFFSET = 100;
    int score;
    private ScoreSystem scoreSystem;
    private int replayPositionX, replayPositionY;

    protected GameOverState(GameStateManager gameStateManager, int score) {
        super(gameStateManager);
        this.orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.background = new Texture("fbassets/sky.png");
        this.gameover = new Texture("fbassets/scoreboard.png");
        this.replayButton = new Texture("fbassets/replay.png");
        //this.setOrthographicCamera();
        //this.orthographicCamera.position.set(orthographicCamera.viewportWidth, orthographicCamera.viewportHeight,0);
        this.score = score;
        this.scoreSystem = new ScoreSystem();

        if(this.scoreSystem.getBestScore() < this.score){
            this.scoreSystem.setBestScore(this.score);
            this.scoreSystem.saveBestScore();
        }


    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            int touchX = (int)input.x;
            int touchY = (int)input.y;
            this.orthographicCamera.unproject(input);
            Rectangle touchedArea = new Rectangle(touchX, touchY, this.replayButton.getWidth(), replayButton.getHeight());
            if (touchedArea.overlaps(this.replayButtonBound()))
            {
                this.gameStateManager.set(new StartGameState(this.gameStateManager));
            }

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
        spriteBatch.draw(this.background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(this.gameover,(Gdx.graphics.getWidth() - this.gameover.getWidth() * 1.5f) / 2, Gdx.graphics.getHeight() / 2  , this.gameover.getWidth() * 1.5f, this.gameover.getHeight() * 1.5f);
        spriteBatch.draw(this.replayButton,(Gdx.graphics.getWidth() - this.replayButton.getWidth()) / 2, Gdx.graphics.getHeight() / 2 - Y_OFFSET * 1.5f);
        //this.scoreSystem.splitScore(this.score);
        spriteBatch.draw(this.scoreSystem.zeroth(), 315 , 520);
        spriteBatch.draw(this.scoreSystem.first(), 330,520 );
        spriteBatch.draw(this.scoreSystem.second(),345,520 );
        spriteBatch.draw(this.scoreSystem.thirth(),360,520 );
        //this.scoreSystem.splitScore(this.scoreSystem.preferences.getInteger("bestScore"));
        this.scoreSystem.splitScore(this.scoreSystem.getBestScore());
        spriteBatch.draw(this.scoreSystem.zeroth(), 315 , 460);
        spriteBatch.draw(this.scoreSystem.first(), 330,460 );
        spriteBatch.draw(this.scoreSystem.second(),345,460 );
        spriteBatch.draw(this.scoreSystem.thirth(),360,460 );
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        this.gameover.dispose();
        this.background.dispose();
        this.replayButton.dispose();

    }






    private Rectangle replayButtonBound(){
        return  new Rectangle(((int)(Gdx.graphics.getWidth() - this.replayButton.getWidth())) / 2, (int)((Gdx.graphics.getHeight())/ 2 + Y_OFFSET * 1.5f ),
                (int)this.replayButton.getWidth(), (int)this.replayButton.getHeight());
    }
}
