package com.doodlemobile.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.doodlemobile.config.Assets;
import com.doodlemobile.config.Settings;
import com.doodlemobile.main.OverlapTester;

public class MainMenuScreen implements Screen{
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Rectangle normalBounds;
	Rectangle hardBounds;
	Rectangle rankBounds;
	Rectangle settingBounds;
	Rectangle rateBounds;
	Vector3 touchPoint;
	Game game;
	
	public MainMenuScreen(Game game){
		this.game = game;
		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(Gdx.graphics.getWidth()/ 2, Gdx.graphics.getHeight() / 2, 0);
		batcher = new SpriteBatch();
		normalBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		hardBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		rankBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		settingBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		rateBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		touchPoint = new Vector3();
	}
	
	public void update(float deltaTime){
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (OverlapTester.pointInRectangle(normalBounds, touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.tapSound);
				game.setScreen(new GameScreen(game));
				return;
			}
			if (OverlapTester.pointInRectangle(hardBounds, touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.tapSound);
				game.setScreen(new GameScreen(game));
				return;
			}
//			if (OverlapTester.pointInRectangle(helpBounds, touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.clickSound);
//				game.setScreen(new HelpScreen(game));
//				return;
//			}
//			if (OverlapTester.pointInRectangle(soundBounds, touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.clickSound);
//				Settings.soundEnabled = !Settings.soundEnabled;
//				if (Settings.soundEnabled)
//					Assets.music.play();
//				else
//					Assets.music.pause();
//			}
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
	
		GLCommon gl = Gdx.gl;
//		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.mainMenu, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batcher.end();

		batcher.enableBlending();
		batcher.begin();
//		batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
//		batcher.draw(Assets.mainMenu, 10, (int)(200 - 110 / 2), 300, 110);
//		batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
	
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		Settings.save();
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
