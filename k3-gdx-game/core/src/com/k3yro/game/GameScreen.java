package com.k3yro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {

    ShapeRenderer shapeRenderer;
    Vector2 position;
    float maxVelocity = 200.0f;

    public GameScreen() {
        shapeRenderer = new ShapeRenderer();
        position = new Vector2(100.0f, 100.0f);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            position.x -=delta * maxVelocity;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            position.x +=delta * maxVelocity;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            position.y -=delta * maxVelocity;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            position.y +=delta * maxVelocity;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(position.x, position.y, 100.0f, 100.0f);
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
