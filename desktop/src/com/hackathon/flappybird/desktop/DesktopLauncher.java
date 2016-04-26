package com.hackathon.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hackathon.flappybird.FlappyBird;

import com.hackathon.flappybird.settings.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = Settings.isRESIZABLE;
		config.title = Settings.TITLE;
		config.width = Settings.WIDTH;
		config.height = Settings.HEIGHT;
		new LwjglApplication(new FlappyBird(), config);
	}
}
