package com.k3yro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class LoadingScreen extends ScreenAdapter {
    public LoadingScreen(){
        K3GdxGame.manager.load("planets.atlas", TextureAtlas.class);
        K3GdxGame.manager.load("player.atlas", TextureAtlas.class);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (K3GdxGame.manager.update()){
            K3GdxGame.INSTANCE.setScreen(new GameScreen());
        }
    }
}
