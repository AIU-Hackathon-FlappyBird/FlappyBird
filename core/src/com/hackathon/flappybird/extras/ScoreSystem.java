package com.hackathon.flappybird.extras;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by koray on 24.04.2016.
 */
public class ScoreSystem {

    public Preferences preferences;


    private  Texture[] numbers = {
            new Texture("fbassets/font_small_0.png"),
            new Texture("fbassets/font_small_1.png"),
            new Texture("fbassets/font_small_2.png"),
            new Texture("fbassets/font_small_3.png"),
            new Texture("fbassets/font_small_4.png"),
            new Texture("fbassets/font_small_5.png"),
            new Texture("fbassets/font_small_6.png"),
            new Texture("fbassets/font_small_7.png"),
            new Texture("fbassets/font_small_8.png"),
            new Texture("fbassets/font_small_9.png")
    };

    private int zeroth = 0, first = 0, second = 0, third = 0;






    public ScoreSystem() {
        this.preferences = Gdx.app.getPreferences("Flappy Bird Preferences");

    }
    public Texture zeroth(){return numbers[this.zeroth];}
    public  Texture first(){return numbers[this.first];}
    public  Texture second(){return numbers[this.second];}
    public  Texture thirth(){return numbers[this.third];}

    public void splitScore(int score){
        int[] scorePartial = new int[4];
        int i = 3;
        int rem = 0;
        int temp = score;

        while(temp != 0){
            rem = temp % 10;
            temp /= 10;
            scorePartial[i--] = rem;

        }
        this.zeroth = scorePartial[0];
        this.first = scorePartial[1];
        this.second = scorePartial[2];
        this.third = scorePartial[3];

    }

    public void setBestScore(int bestScore){
        this.preferences.putInteger("best score",bestScore);
    }
    public int getBestScore(){
        return this.preferences.getInteger("best score");
    }



}
