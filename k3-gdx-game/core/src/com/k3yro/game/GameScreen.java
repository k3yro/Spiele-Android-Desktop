package com.k3yro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class GameScreen extends ScreenAdapter {

    ShapeRenderer shapeRenderer;
    Vector2 paddlePosition;
    Vector2 enemyPosition;
    Vector2 ballPosition;
    Vector2 ballVelocity;

    float maxVelocity = 400.0f;
    float startSpeed = 600.0f;
    float paddleWidth = 140.0f;
    float paddleHeight = 28.0f;
    float ballRadius = 23.0f;
    float paddleOffset = 50.0f;
    Color paintColor = Color.BLACK;
    float bgColor = 1;
    Random random = new Random();

    float enemyOffset = Gdx.graphics.getHeight() - paddleOffset - paddleHeight;

    public GameScreen() {
        shapeRenderer = new ShapeRenderer();
        paddlePosition = new Vector2(Gdx.graphics.getWidth() / 2.0f - paddleWidth / 2.0f, paddleOffset);
        enemyPosition = new Vector2(Gdx.graphics.getWidth() / 2.0f - paddleWidth / 2.0f, enemyOffset);
        ballPosition = new Vector2(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f - ballRadius);

        // nextFloat -> Zufallszahl zwischen 0 und 1
        ballVelocity = new Vector2((random.nextFloat() * 200) - 100, startSpeed);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            paddlePosition.x -=delta * maxVelocity;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            paddlePosition.x +=delta * maxVelocity;
        }

        if (paddlePosition.x <= (Gdx.graphics.getWidth()-100.0f) && Gdx.input.getAccelerometerX() < 0)
        {
            paddlePosition.x -= Gdx.input.getAccelerometerX() * 5;
        }
        else if (paddlePosition.x >= 0 && Gdx.input.getAccelerometerX() > 0)
        {
            paddlePosition.x -= Gdx.input.getAccelerometerX() * 5;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        // Paddle am Bildende stoppen
        if (paddlePosition.x < 0.0f){
            paddlePosition.x = 0.0f;
        }else if (paddlePosition.x > Gdx.graphics.getWidth() - paddleWidth ){
            paddlePosition.x = Gdx.graphics.getWidth() - paddleWidth;
        }

        // Ballreflektion rechts
        if (ballPosition.x > Gdx.graphics.getWidth() - ballRadius){
            ballPosition.x = Gdx.graphics.getWidth() - ballRadius;
            ballVelocity.x *= -1;
        }

        // Ballreflektion links
        if (ballPosition.x <= ballRadius){
            ballPosition.x = ballRadius;
            ballVelocity.x *= -1;
        }

        // Game over
        if (ballPosition.y <= ballRadius){
            paddlePosition = new Vector2(Gdx.graphics.getWidth() / 2.0f - paddleWidth / 2.0f, paddleOffset);
            ballPosition = new Vector2(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f - ballRadius);
            ballVelocity = new Vector2((random.nextFloat() * 200) - 100, startSpeed);
        }

        // Win
        if (ballPosition.y >= Gdx.graphics.getHeight() + ballRadius){
            paddlePosition = new Vector2(Gdx.graphics.getWidth() / 2.0f - paddleWidth / 2.0f, paddleOffset);
            ballPosition = new Vector2(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f - ballRadius);
            ballVelocity = new Vector2((random.nextFloat() * 200) - 100, startSpeed);
        }

        // KI
        if (ballPosition.x >= enemyPosition.x + paddleWidth - paddleWidth / 3.0f){
            enemyPosition.x += 5.3f;
        }else if(ballPosition.x <= enemyPosition.x + paddleWidth / 3.0f) {
            enemyPosition.x -= 5.3f;
        }

        // Ballreflektion Enemy
        if (ballPosition.y + ballRadius >= enemyPosition.y){
            if (ballPosition.x >= enemyPosition.x && ballPosition.x <= enemyPosition.x + paddleWidth){


                if (ballVelocity.y > 0)
                    ballVelocity.y *= -1.0;

                System.out.println(ballVelocity.y);


            }

        }

        // Ballreflektion Paddle
        if (ballPosition.y - ballRadius <= paddlePosition.y + paddleHeight ){
            if (ballPosition.x >= paddlePosition.x && ballPosition.x <= paddlePosition.x + paddleWidth){
                ballPosition.y = paddlePosition.y + paddleHeight + ballRadius;
                if (ballVelocity.y >= -1400.0f) {
                    ballVelocity.y *= -1.4;
                   // System.out.println("sdfjsl");
                }
                else {
                    ballVelocity.y *= -0.8;
                }
                if (random.nextFloat() * 100 < 50) {
                    if (paintColor == Color.BLACK) {
                        paintColor = Color.WHITE;
                        bgColor = 0;
                    } else {
                        paintColor = Color.BLACK;
                        bgColor = 1;
                    }
                }

                ballVelocity.x = (random.nextFloat() * 1200) - 600;
            }
        }

        // Ball Geschwindigkeit berechnen
        ballPosition.mulAdd(ballVelocity, delta);

        Gdx.gl.glClearColor(bgColor, bgColor, bgColor, bgColor);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(paintColor);
        shapeRenderer.rect(paddlePosition.x, paddlePosition.y, paddleWidth, paddleHeight);
        shapeRenderer.rect(enemyPosition.x, enemyPosition.y, paddleWidth, paddleHeight);
        shapeRenderer.circle(ballPosition.x, ballPosition.y, ballRadius);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public void hide() {
        this.dispose();
    }
}
