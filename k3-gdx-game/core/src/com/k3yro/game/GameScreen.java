package com.k3yro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen extends ScreenAdapter {

    private TextureAtlas atlas;
    private TextureRegion graumondTexture;
    private TextureRegion eismondTexture;
    private TextureRegion glutmondTexture;
    private SpriteBatch batch;
    private Sprite sprite;
    private Player player;



    public GameScreen() {
        atlas = K3GdxGame.manager.get("planets.atlas");

        graumondTexture = atlas.findRegion("Graumond");
        eismondTexture = atlas.findRegion("Eismond");
        glutmondTexture = atlas.findRegion("Glutmond");
        batch = new SpriteBatch();
        sprite = new Sprite(graumondTexture);
        player = new Player();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        player.render(delta, batch);

        sprite.draw(batch);
        sprite.setPosition(0, Gdx.graphics.getHeight());
        sprite.translate(delta * 2020, 0);
        sprite.rotate(delta * 9);
        sprite.scale(delta * 0.2f);

        System.out.println(sprite.getScaleX());
        batch.end();

        // Anzahl renderCalls
        int renderCalls = batch.renderCalls;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {
        this.dispose();
    }
}
