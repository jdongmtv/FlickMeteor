package com.doodlemobile.entity;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.FadeTo;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Parallel;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.doodlemobile.config.Assets;

public class Blade extends Actor implements InputProcessor{
	Stage stage;
	public Image hp;
	int preX, preY;
	public Blade(Stage stage){
		this.stage = stage;
		hp = new Image(Assets.hp);
		hp.x=0;
		hp.y= Gdx.graphics.getHeight()*19/20;
		hp.height = Gdx.graphics.getHeight()/20;
		hp.width =	Gdx.graphics.getWidth();
		stage.addActor(hp);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		makeTrail(x,Gdx.graphics.getHeight()-y,32);
		float newX =(float)(2*x -Gdx.graphics.getWidth())/(float)Gdx.graphics.getWidth();
		float newY =(float)(Gdx.graphics.getHeight()-2*y)/(float)Gdx.graphics.getHeight();
		Log.d("dongjun",newX+":"+newY+"----"+x+":"+y);
		preX = x; preY = y;
		System.err.println(Gdx.input.getAccelerometerX()*Gdx.graphics.getDeltaTime()*100+"--:--"+Gdx.input.getAccelerometerY()*Gdx.graphics.getDeltaTime()*100);
		return false;
	}

	private void makeTrail(int x, int y, int size) {
		Image img = null;
		img = new Image(Assets.sanjiao);
		img.x = x;
		img.y = y;
		size =(int) Math.sqrt((Math.pow(Math.abs(x-preX), 2)+Math.pow(Math.abs(y-preY), 2)));
		img.width = 32;
		img.height = 128;
		this.addAction(img);
		stage.addActor(img);
	}

	private void addAction(final Image img) {
		int duration = MathUtils.random(3, 60);
		MoveTo moveto = MoveTo.$(img.x, 800, duration);
		moveto.setCompletionListener(new OnActionCompleted() {
			public void completed(Action action) {
				stage.removeActor(img);
			}
		});

		int rotate = MathUtils.random(360);
		float scale = MathUtils.random(0.5f, 2.0f);
		float fade = MathUtils.random(1.0f);
//		Action action = Parallel.$(moveto, ScaleTo.$(scale, scale, duration),
//				RotateTo.$(rotate, duration), FadeTo.$(0, 3));
		Action action = Parallel.$(FadeTo.$(0, 1));
		action.setCompletionListener(new OnActionCompleted() {
			@Override
			public void completed(Action action) {
				stage.removeActor(img);
			}
		});
		img.action(action);
	}
	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}
	
	
}
