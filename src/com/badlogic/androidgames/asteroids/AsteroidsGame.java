package com.badlogic.androidgames.asteroids;

import android.content.res.Configuration;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class AsteroidsGame extends AndroidGame{
	public Screen getStartScreen(){
		return new LoadingScreen(this);
	}
}
