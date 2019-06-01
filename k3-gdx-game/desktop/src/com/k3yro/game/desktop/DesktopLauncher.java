package com.k3yro.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.k3yro.game.K3GdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxHeight = 4096;
		settings.maxWidth = 4096;
		settings.edgePadding = true;
		settings.duplicatePadding = true;
		settings.filterMin = Texture.TextureFilter.Linear;
		settings.filterMag = Texture.TextureFilter.Linear;
		TexturePacker.process("Planeten", ".", "planets");

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.useVsync(true);
		//config.fullscreen = true;
		config.setTitle("K3 Game");
		//config.setWindowIcon(Files.FileType.Internal,"icon.png");

		//config.setWindowedMode(1920, 1000);
		//config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());


		new Lwjgl3Application(new K3GdxGame(), config);
	}
}
