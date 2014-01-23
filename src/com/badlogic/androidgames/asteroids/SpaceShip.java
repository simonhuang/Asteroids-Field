package com.badlogic.androidgames.asteroids;

public class SpaceShip {
	final int x = 125;
	int y;
	int speed = 8;
	int width, height;
	int dir;
	Missile missile;
	public SpaceShip(){
		y = 330;
		width = 150;
		height = 75;
		dir = 0;
	}
	public void move (int dir){
		if (dir > 0 && y + height < World.worldHeight)
			y+=speed;
		else if (dir < 0 && y > 1)
			y-=speed;
	}
	public void shoot(){
		missile = new Missile(y - 5);
	}
}