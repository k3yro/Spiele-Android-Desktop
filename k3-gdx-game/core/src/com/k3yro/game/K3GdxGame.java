package com.k3yro.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class K3GdxGame extends Game {

	public static K3GdxGame INSTANCE;
	public static AssetManager manager;

	public K3GdxGame(){

		INSTANCE = this;
	}

	@Override
	public void create () {
		manager = new AssetManager();
		setScreen(new LoadingScreen());
	}

}
