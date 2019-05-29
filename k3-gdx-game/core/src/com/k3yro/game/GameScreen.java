package com.k3yro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {

    SpriteBatch batch;
    Texture img;

    public GameScreen() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, Gdx.graphics.getWidth() - img.getWidth(), Gdx.graphics.getHeight() - img.getWidth());
        batch.end();
        // Beispiel: Vom GameScreen zurueck ins Hauptmenu
        //K3GdxGame.INSTANCE.setScreen(new MainMenuScreen());
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
