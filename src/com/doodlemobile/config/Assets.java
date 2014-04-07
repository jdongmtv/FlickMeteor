package com.doodlemobile.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture stars;
	public static Texture buttons;
	public static Texture backgrounds;
	public static Texture mainMenu;
	public static Texture sanjiao;

	public static TextureRegion star1;
	public static TextureRegion star2;
	public static TextureRegion flickBack;
	public static TextureRegion hp;
	public static TextureRegion line;
	public static TextureRegion meteor;
	public static TextureRegion fog;
	public static TextureRegion flickLightOn;
	public static TextureRegion flickLightOff;
	public static TextureRegion button;
	public static TextureRegion earth;
	public static TextureRegion forcast;
	public static TextureRegion screen;
	public static TextureRegion incoming;
	
	public static Music music;
	public static Sound flySound;
	public static Sound hitSound;
	public static Sound clickSound;
	
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load() {
		stars = Assets.loadTexture("stars.png");
		buttons = Assets.loadTexture("buttons.png");
		backgrounds = Assets.loadTexture("backgrounds.png");
		mainMenu = Assets.loadTexture("mainMenu.jpg");
		sanjiao = Assets.loadTexture("sanjiao.png");

		star1 = new TextureRegion(stars, 2, 2, 800, 480);
		star2 = new TextureRegion(stars, 2, 484, 800, 480);
		flickBack = new TextureRegion(stars, 804, 2, 8, 480);
		
		button = new TextureRegion(buttons, 2, 337, 158, 158);
		earth = new TextureRegion(buttons, 2, 2, 456, 333);
		flickLightOn = new TextureRegion(buttons, 162, 337, 133, 102);
		flickLightOff = new TextureRegion(buttons, 297, 337, 133, 102);
		screen = new TextureRegion(buttons, 2, 497, 95, 81);
		forcast = new TextureRegion(buttons, 99, 497, 95, 81);
		meteor = new TextureRegion(buttons, 432, 337,73, 72);

		line = new TextureRegion(backgrounds, 2, 2, 800, 480);
		fog = new TextureRegion(backgrounds, 2, 484, 800, 480);
		incoming = new TextureRegion(backgrounds, 804, 2, 122, 125);
		hp = new TextureRegion(backgrounds, 2, 966, 794, 28);
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
