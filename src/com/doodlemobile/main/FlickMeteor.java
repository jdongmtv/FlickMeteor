package com.doodlemobile.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.doodlemobile.config.Assets;
import com.doodlemobile.config.Settings;
import com.doodlemobile.screen.MainMenuScreen;

public class FlickMeteor extends Game{

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	public Screen getScreen() {
		return super.getScreen();
	}

	@Override
	public void create() {
		Settings.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}
	
}
