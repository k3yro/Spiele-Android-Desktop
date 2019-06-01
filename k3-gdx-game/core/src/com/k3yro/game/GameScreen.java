package com.k3yro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen extends ScreenAdapter {

    private Texture texture;
    //private TextureRegion planetTexture;
    //private TextureAtlas atlas;
    private SpriteBatch batch;
    private Sprite sprite;
    int direction = 1;

    public GameScreen() {
        //atlas = new TextureAtlas("planets.atlas");
        texture = new Texture("badlogic.jpg");
        batch = new SpriteBatch();
        sprite = new Sprite(texture);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(texture, 0.0f, 0.0f, texture.getWidth(), texture.getHeight());
        sprite.draw(batch);
        sprite.rotate(delta * 90.0f);
        if (sprite.getX() >= Gdx.graphics.getWidth()){
            direction = -1;
        } else if (sprite.getX() <= 0){
            direction = 1;
        }
        sprite.translate(delta * 50 * direction, 50*delta*direction);
        System.out.println(sprite.getX());
        batch.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {
        this.dispose();
    }
}
