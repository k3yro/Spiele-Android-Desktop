package com.k3yro.game;

import com.badlogic.gdx.Game;

public class K3GdxGame extends Game {

	public static K3GdxGame INSTANCE;

	public K3GdxGame(){

		INSTANCE = this;
	}

	@Override
	public void create () {
		setScreen(new GameScreen());
	}
}
