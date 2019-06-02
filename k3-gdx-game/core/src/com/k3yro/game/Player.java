package com.k3yro.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    private Animation<TextureRegion> runAnimation;
    private float animationTime = 0.0f;

    public Player(){
        TextureAtlas atlas = K3GdxGame.manager.get("player.atlas", TextureAtlas.class);
        runAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), Animation.PlayMode.LOOP);
    }

    public void  render(float delta, SpriteBatch batch){
        animationTime += delta;
        TextureRegion currentFrame = runAnimation.getKeyFrame(animationTime);
        batch.draw(currentFrame, 50,50,128,128);
    }

}
