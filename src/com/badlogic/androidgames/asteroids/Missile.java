package com.badlogic.androidgames.asteroids;

public class Missile {
	int x, y;
	final int speed = 10;
	
	public Missile (int y){
		this.y = y;
		x = 230;
	}
	
	public void move(){
		x+=speed;
	}
}
