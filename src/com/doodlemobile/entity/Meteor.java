package com.doodlemobile.entity;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Meteor extends DynamicGameObject {
	public static final int Meteor_STATE_FLY = 0;
	public static final int Meteor_STATE_HIT = 1;
	public static final float Meteor_FLY_VELOCITY = 11;
	public static final float Meteor_MOVE_VELOCITY = 20;
	public static final float Meteor_WIDTH = 64;
	public static final float Meteor_HEIGHT = 64;

	int state;
	float stateTime;
	public Meteor(float x, float y) {
		super(x, y, Meteor_WIDTH,Meteor_HEIGHT);
		state = Meteor_STATE_FLY;
		stateTime = 0;
		velocity.set(10.0f, 0);
	}

	@Override
	public void update(float deltaTime){
		velocity.add(accel.x * deltaTime, accel.y * deltaTime);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
		stateTime += deltaTime;
	}
	
	public void hitBlackHole(){
		velocity.set(0, 0);
		state = Meteor_STATE_HIT;
		stateTime = 0;
	}

	@Override
	public void render(float deltaTime) {
		
	}
	
	
}
