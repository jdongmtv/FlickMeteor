package com.doodlemobile.screen;

import java.util.Random;

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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.doodlemobile.config.Assets;
import com.doodlemobile.entity.Blade;
import com.doodlemobile.entity.Universe;
import com.doodlemobile.entity.UniverseRender;

public class GameScreen implements Screen {
	Game game;
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	SpriteBatch batch;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	int lastScore;
	String scoreString;
	Universe universe;
	UniverseRender render;
	
	Stage stage;
//	Image fog,star1,star2,line,earth,hp,button,forcast,screen,flickLightOff;
//	fog = new Image();
	Blade blade;
	Random random;
	
	public GameScreen(Game game) {
		this.game = game;
		state = GAME_READY;
		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(Gdx.graphics.getWidth()/ 2, Gdx.graphics.getHeight() / 2, 0);
		touchPoint = new Vector3();
		batch = new SpriteBatch();
		this.universe = new Universe();
		render = new UniverseRender(batch, universe);
	    stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 
	                true);
	    blade = new Blade(stage);
	    stage.addActor(blade);
	    Gdx.input.setInputProcessor(blade);
	}
	
	@Override
	public void render(float delta) {

		GLCommon gl = Gdx.gl;
		update(delta);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		guiCam.update();
		batch.setProjectionMatrix(guiCam.combined);

		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentLevelEnd();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}
		blade.hp.width =blade.hp.width - random.nextInt(1000);
		batch.enableBlending();
		batch.begin();
		stage.act(delta);
		stage.draw();
		batch.end();
	}
	
	private void presentGameOver() {
 
	}

	private void presentLevelEnd() {
		
	}

	private void presentPaused() {
		
	}

	private void presentRunning() {
		render.rend();
	
	}

	private void presentReady() {
		batch.enableBlending();
		batch.begin();
		batch.draw(Assets.fog, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(Assets.star1, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(Assets.star2, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(Assets.line, 0, 0,Gdx.graphics.getWidth()*3/4, Gdx.graphics.getHeight()*14/15);
		batch.draw(Assets.earth, 0, 0,Gdx.graphics.getWidth()*1/2, Gdx.graphics.getHeight()/2);
		batch.draw(Assets.hp, 0, Gdx.graphics.getHeight()*14/15,Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/15);
		batch.draw(Assets.button, 0, 0,Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/4);
		batch.draw(Assets.forcast,Gdx.graphics.getWidth()/3, 0,Gdx.graphics.getWidth()/12,Gdx.graphics.getHeight()/6);
		batch.draw(Assets.screen,Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/5,Gdx.graphics.getWidth()/12,Gdx.graphics.getHeight()/6);
		batch.draw(Assets.flickLightOff,Gdx.graphics.getWidth()*27/30, Gdx.graphics.getHeight()*11/15,Gdx.graphics.getWidth()*2/15,Gdx.graphics.getHeight()*3/15);
		batch.end();
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
		if (state == GAME_RUNNING) state = GAME_PAUSED;
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	
	}
	
	public void update(float deltaTime){
		if (deltaTime > 0.1f) deltaTime = 0.1f;

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			updateLevelEnd();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void updateGameOver() {
		if (Gdx.input.justTouched()) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

	private void updateLevelEnd() {
		if (Gdx.input.justTouched()) {
			state = GAME_READY;
		}
	}

	private void updatePaused() {
		
	}

	private void updateRunning(float deltaTime) {
		Vector2 accel =new Vector2();
		accel.set(0, 2f);
		universe.update(deltaTime, accel);
	}

	private void updateReady() {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}
	}

}
