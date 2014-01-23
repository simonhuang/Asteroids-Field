package com.badlogic.androidgames.asteroids;

public class Asteroid {
	public static final int type1  = 0;
	public static final int type2 = 1;
	public static final int type3 = 2;
	public static final int size1 = 0;
	public static final int size2 = 1;
	public static final int size3 = 2;
	int y;
	float x = 1380;
	int type, size;
	public static int speed = 5;
	int width, height;
	
	public Asteroid (int y, int type, int size){
		this.y = y;
		this.type = type;
		this.size = size;
		if (size == 0){
			width = 50;
			height = 50;
		}
		else if (size == 1){
			width = 100;
			height = 100;
		}
		else if (size == 2){
			width = 150;
			height = 150;
		}
	}
	
	public void move(float deltaTime){
		x -= speed * (deltaTime * 100);
	}
	

	public static void increaseSpeed(){
		speed += 1;
	}
	public static void resetSpeed(){
		speed = 5;
	}
}
