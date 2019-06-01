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
    Vector2 ballPosition;
    Vector2 ballVelocity;

    float maxVelocity = 400.0f;
    float paddleWidth = 60.0f;
    float paddleHeight = 15.0f;
    float ballRadius = 10.0f;
    float paddleOffset = 30.0f;
    Color paintColor = Color.BLACK;
    float bgColor = 1;
    Random random = new Random();

    public GameScreen() {
        shapeRenderer = new ShapeRenderer();
        paddlePosition = new Vector2(Gdx.graphics.getWidth() / 2.0f - paddleWidth / 2.0f, paddleOffset);
        ballPosition = new Vector2(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f - ballRadius);

        // nextFloat -> Zufallszahl zwischen 0 und 1
        ballVelocity = new Vector2((random.nextFloat() * 200) - 100, 100.0f);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            paddlePosition.x -=delta * maxVelocity;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            paddlePosition.x +=delta * maxVelocity;
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

        // Ballreflektion oben
        if (ballPosition.y >= Gdx.graphics.getHeight() - ballRadius){
            ballPosition.y = Gdx.graphics.getHeight() - ballRadius;
            ballVelocity.y *= -1;
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
            ballVelocity = new Vector2((random.nextFloat() * 200) - 100, 100.0f);
        }

        // Ballreflektion Paddle
        if (ballPosition.y - ballRadius <= paddlePosition.y + paddleHeight && ballPosition.y + ballRadius > paddlePosition.y){
            if (ballPosition.x >= paddlePosition.x && ballPosition.x <= paddlePosition.x + paddleWidth){
                ballPosition.y = paddlePosition.y + paddleHeight + ballRadius;
                if (ballVelocity.y >= -400.0f) {
                    ballVelocity.y *= -1.4;
                    System.out.println("sdfjsl");
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
                ballVelocity.x = random.nextFloat() * 200 - 100;

            }

        }

        // Ball Geschwindigkeit berechnen
        ballPosition.mulAdd(ballVelocity, delta);

        Gdx.gl.glClearColor(bgColor, bgColor, bgColor, bgColor);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(paintColor);
        shapeRenderer.rect(paddlePosition.x, paddlePosition.y, paddleWidth, paddleHeight);
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
