package com.doodlemobile.main;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class MainActivity extends  AndroidApplication {

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState); 
    	  initialize(new FlickMeteor(), false);
    }
}