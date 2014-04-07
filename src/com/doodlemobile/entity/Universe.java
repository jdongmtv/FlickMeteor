package com.doodlemobile.entity;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;



public class Universe{
	public final Meteor meteor;
	
	public static final int Universe_STATE_RUNNING = 0;
	public static final int Universe_STATE_NEXT_LEVEL = 1;
	public static final int Universe_STATE_GAME_OVER = 2;
	public final Random rand;

	public float distanceSoFar;
	public int score;
	public int state;
	
	public Universe() {
		this.meteor = new Meteor(0.0f, 0.0f);
		rand = new Random();

		this.distanceSoFar = 0;
		this.score = 0;
		this.state = Universe_STATE_RUNNING;
	}
	
	public void update (float deltaTime, Vector2 accel) {
		updateMeteor(deltaTime, accel);
		if (meteor.state != Meteor.Meteor_STATE_HIT) checkCollisions();
		checkGameOver();
	}

	private void checkGameOver() {
		
	}

	private void checkCollisions() {
		
	}

	private void updateMeteor(float deltaTime, Vector2 accel) {
		meteor.accel =accel;
		meteor.update(deltaTime);
		distanceSoFar = Math.max(meteor.position.x, distanceSoFar);
	}
}
