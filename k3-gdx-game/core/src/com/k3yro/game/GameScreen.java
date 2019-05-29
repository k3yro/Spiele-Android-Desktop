package com.k3yro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {

    SpriteBatch batch;
    Texture img;
    ShapeRenderer shapeRenderer;
    Vector2 position;
    Vector2 touchPos;
    float maxVelocity = 200.0f;
    boolean touched = false;

    public GameScreen() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        shapeRenderer = new ShapeRenderer();

        position = new Vector2(100.0f, 100.0f);
        touchPos = new Vector2(100.0f, 100.0f);

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

        // Neigungssteuerung
        if (position.x <= (Gdx.graphics.getWidth()-100.0f) && Gdx.input.getAccelerometerX() < 0)
        {
            position.x -= Gdx.input.getAccelerometerX() * 5;
        }
        else if (position.x >= 0 && Gdx.input.getAccelerometerX() > 0)
        {
            position.x -= Gdx.input.getAccelerometerX() * 5;
        }

        //if (Gdx.input.isTouched()){
            if (position.y <= (Gdx.graphics.getHeight()-100.0f) && Gdx.input.getAccelerometerY() < 0)
            {
                position.y -= Gdx.input.getAccelerometerY() * 5;
            }
            else if (position.y >= 0 && Gdx.input.getAccelerometerY() > 0)
            {
                position.y -= Gdx.input.getAccelerometerY() * 5;
            }

       // }

        // Feuerball
        if (touched){
            touchPos.y += delta * 900;
            if (touchPos.y > Gdx.graphics.getHeight()){
                touched = false;
            }
        }

        if (Gdx.input.justTouched())
        {
            if (touched){

            }else {
                touchPos.x = position.x;
                touchPos.y = position.y;
                touched = true;
            }

        }

        //float accelY = Gdx.input.getAccelerometerY();
        //float accelZ = Gdx.input.getAccelerometerZ();

        //position.y = -1 * (Gdx.input.getY()-400);
        //Gdx.input.setCursorCatched(true); //
        //if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){} // Mausklick

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(img, Gdx.graphics.getWidth() - img.getWidth(), Gdx.graphics.getHeight() - img.getWidth());
        batch.end();

        // Beispiel: Vom GameScreen zurueck ins Hauptmenu
        //K3GdxGame.INSTANCE.setScreen(new MainMenuScreen());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(position.x, position.y, 100.0f, 100.0f);
        if (touched){
            shapeRenderer.setColor(Color.ORANGE);
            shapeRenderer.circle(touchPos.x, touchPos.y, 10.0f);
        }
        shapeRenderer.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    @Override
    public void hide() {
        this.dispose(); //Alle Grafiken freigeben
    }
}
