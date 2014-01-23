package com.badlogic.androidgames.asteroids;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
	
	public boolean gameOver = false;
	
	static final int worldWidth = 1280;
	static final int worldHeight = 720;
	static final int scoreIncrement = 1;
	int score;
	
	static int createCounter;
	int createSpeed;
	
	static int missileCounter = 0;
	boolean isReady = false;
	
	SpaceShip spaceship;
	List <Asteroid> asteroids = new ArrayList();
	
	Random random = new Random();
	float tickTime=0;
	float tick = 0.017f;
	
	public World () {
		spaceship = new SpaceShip();
		score = 1;
		createSpeed = 51;
	}
	
	public void incrementScore(){
		score += scoreIncrement;
		if (score % 800 == 0)
			Asteroid.increaseSpeed();
	}
	
	
	public void createAsteroid(float deltaTime){
		if (gameOver)
			return;
		if (score%500 == 0){
			createCounter = 0;
			createSpeed -= 3;
		}
		tickTime += deltaTime;
		while (tickTime>tick){
			tickTime -= tick;
			createCounter++;
			
			if (createCounter == createSpeed){
				asteroids.add(new Asteroid (random.nextInt(worldHeight) - 30, random.nextInt(3), random.nextInt(3)));
				createCounter=0;
			}
		}
	}

	public void removeAsteroids(){
		Asteroid ast;
		for (int i=0;i<asteroids.size();i++){
			ast = asteroids.get(i);
			
			if (ast.x < -100)
				asteroids.remove(i);
		}
	}
	
	public void checkDeath(){
		for (int i=0;i<asteroids.size();i++){
			Asteroid ast = asteroids.get(i);
			if (ast.size == ast.size1){
				if ((ast.x + 15 < spaceship.x + spaceship.width + 80 && ast.x + 15 > spaceship.x + 80)
						|| (ast.x + ast.width - 15 < spaceship.x + spaceship.width + 80 && ast.x + ast.width - 15 > spaceship.x + 80)){
					if ((spaceship.y + spaceship.height - 20 > ast.y + 8 && spaceship.y + spaceship.height - 20 < ast.y + ast.height - 8)
							|| (spaceship.y + 20 > ast.y + 8 && spaceship.y + 20 < ast.y + ast.height - 8))
						gameOver = true;
				}
			}
			else{
				if ((spaceship.x + spaceship.width + 80 > ast.x + 20 && spaceship.x + spaceship.width + 80 < ast.x + ast.width - 20)
						|| (spaceship.x + 80 > ast.x + 8 && spaceship.x + 80 < ast.x + ast.width - 8)){
					if ((spaceship.y + spaceship.height - 30 > ast.y + 8 && spaceship.y + spaceship.height - 30 < ast.y + ast.height - 8)
							|| (spaceship.y + 30 > ast.y + 8 && spaceship.y + 30 < ast.y + ast.height - 8))
						gameOver = true;
				}
			}
//			if ((ast.x + 15 < spaceship.x + spaceship.width + 80 && ast.x + 15 > spaceship.x + 80)
//					|| (ast.x + ast.width - 15 < spaceship.x + spaceship.width + 80 && ast.x + ast.width - 15 > spaceship.x + 80)){
//				if ((spaceship.y + spaceship.height - 20 > ast.y + 8 && spaceship.y + spaceship.height - 20 < ast.y + ast.height - 8)
//						|| (spaceship.y + 20 > ast.y + 8 && spaceship.y + 20 < ast.y + ast.height - 8))
//					gameOver = true;
//			}
		}
	}
	

	public void moveAsteroids(float deltaTime) {
		// TODO Auto-generated method stub
		for (int i=0;i<asteroids.size();i++) {
			asteroids.get(i).move(deltaTime);
		}
	}
	public boolean checkHit(){
		Missile missile = spaceship.missile;
		
		if (missile == null)
			return false;
		
		if (missile.x > 1280){
			spaceship.missile = null;
			return false;
		}
		
		for (int i=0;i<asteroids.size();i++){
			Asteroid ast = asteroids.get(i);
			if (missile.x > ast.x - 50){
				if (missile.y + 40 > ast.y && missile.y + 40 < ast.y + ast.height){
					asteroids.remove(i);
					spaceship.missile = null;
					score += 1000;
					return true;
				}
			}
		}
		return false;
	}


	private void prepareMissile() {
		// TODO Auto-generated method stub
		missileCounter++;
		if (missileCounter == 250){
			isReady = true;
			missileCounter = 0;
		}
	}
	
	public void update(float deltaTime){
		if (gameOver)
			return;
		spaceship.move(spaceship.dir);
		createAsteroid(deltaTime);
		incrementScore();
		moveAsteroids(deltaTime);
		removeAsteroids();
		checkDeath();
		if (spaceship.missile != null){
			spaceship.missile.move();
			if (checkHit() && Settings.soundEnabled)
				Assets.explode.play(0.2f);
		}
		if (!isReady)
			prepareMissile();
	}
}
