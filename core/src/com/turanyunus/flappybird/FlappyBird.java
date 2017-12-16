package com.turanyunus.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    SpriteBatch spriteBatch;
    Texture background,bird,bee1,bee2,bee3;
    float birdX=0,birdY=0,velocity=0,gravity=0.4f;
    int gameState = 0;
    int numberofEnemies=4;
    Random random;
    float distance = 0;
    float enemyVelocity = 2;

    float [] enemyX= new float[numberofEnemies];
    float [] enemyOffset = new float[numberofEnemies];
    float [] enemyOffset2 = new float[numberofEnemies];
    float [] enemyOffset3 = new float[numberofEnemies];

    @Override
    public void create () {
        spriteBatch = new SpriteBatch();
        background = new Texture("FullBackground.png");
        bird = new Texture("bird.png");
        birdX = Gdx.graphics.getWidth() / 2 - bird.getHeight();
        birdY = Gdx.graphics.getHeight() / 2;
        bee1 = new Texture("enemy.png");
        bee2 = new Texture("enemy.png");
        bee3 = new Texture("enemy.png");
        random = new Random();


        distance = Gdx.graphics.getWidth()/3;
        for (int i = 0;i<numberofEnemies;i++){
            enemyOffset[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
            enemyOffset2[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
            enemyOffset3[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
            enemyX[i] = Gdx.graphics.getWidth()- bee1.getWidth()/2 + i * distance;
        }
    }

    @Override
    public void render () {
        spriteBatch.begin();
        spriteBatch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        if (gameState == 1){
            if (Gdx.input.justTouched()){
                velocity = -10;
            }
            for (int i = 0;i<numberofEnemies;i++){
                if (enemyX[i] < Gdx.graphics.getWidth()/15){
                    enemyX[i]=enemyX[i]+numberofEnemies*distance;
                    enemyOffset[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
                    enemyOffset2[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
                    enemyOffset3[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
                }else{
                    enemyX[i] = enemyX[i] - enemyVelocity;
                }

                spriteBatch.draw(bee1,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
                spriteBatch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset2[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
                spriteBatch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset3[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);


            }


            if (birdY > 0 || velocity <0){
                velocity = velocity+gravity;
                birdY=birdY-velocity;
            }
        }else {
            if (Gdx.input.justTouched()){
                gameState = 1;
            }
        }

        spriteBatch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
        spriteBatch.end();

    }

    @Override
    public void dispose () {

    }
}
