package com.doodlemobile.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doodlemobile.config.Assets;

public class UniverseRender {
	Universe universe;
	OrthographicCamera cam;
	SpriteBatch batch;
	TextureRegion background;

	public UniverseRender (SpriteBatch batch, Universe universe) {
		this.universe = universe;
		this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.cam.position.set(Gdx.graphics.getWidth()/ 2, Gdx.graphics.getHeight()/ 2, 0);
		this.batch = batch;
	}
	
	public void rend () {
		if (universe.meteor.position.x >=cam.position.x + Gdx.graphics.getWidth()/2)
			cam.position.x =universe.meteor.position.x + Gdx.graphics.getWidth()/2;
		if(universe.meteor.position.y>=cam.position.y+Gdx.graphics.getHeight()/2)
			cam.position.y = universe.meteor.position.y + Gdx.graphics.getHeight()/2;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}

	private void renderObjects() {
		batch.enableBlending();
		batch.begin();
		renderMeteor();
		batch.end();
	}

	private void renderMeteor() {
		batch.draw(Assets.meteor, universe.meteor.position.x,universe.meteor.position.y, universe.meteor.bounds.width, universe.meteor.bounds.height);
	}

	private void renderBackground() {
		batch.enableBlending();
		batch.begin();
		batch.draw(Assets.fog,cam.position.x-Gdx.graphics.getWidth()/2, cam.position.y-Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		if((cam.position.x-Gdx.graphics.getWidth()/2)%(2*Gdx.graphics.getWidth())!=0||(cam.position.y-Gdx.graphics.getHeight()/2)%(2*Gdx.graphics.getHeight())%2!=0)
		{
			batch.draw(Assets.star1, cam.position.x-Gdx.graphics.getWidth()/2, cam.position.y-Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			batch.draw(Assets.star2, cam.position.x-Gdx.graphics.getWidth()/2, cam.position.y-Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		}
		else
			batch.draw(Assets.star2,cam.position.x-Gdx.graphics.getWidth()/2, cam.position.y-Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		batch.end();
	}
}
