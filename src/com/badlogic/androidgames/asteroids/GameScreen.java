package com.badlogic.androidgames.asteroids;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class GameScreen extends Screen {
	
	TouchEvent event;
	int directionPointer;
	
	enum GameState{
		Ready,
		Running,
		Paused,
		GameOver
	}

	GameState state = GameState.Ready;
	World world;
	public GameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		world = new World();
		Assets.music.setLooping(true);
		directionPointer = -1;
	}

	@Override
	public void update(float deltaTime) {
		List <TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		// TODO Auto-generated method stub
		for (int i=0;i<touchEvents.size();i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				state = GameState.Running;
				if (Settings.soundEnabled){
					Assets.click.play(1);
					Assets.music.play();
				}
			}
		}
	}
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		for (int i=0;i<touchEvents.size();i++){
			event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN){
				if (event.x > 0 && event.x < 200 && event.y > 170 && event.y < 370){
					directionPointer = event.pointer;
					world.spaceship.dir = - 1;
				}
				else if (event.x > 0 && event.x < 200 && event.y > 370 && event.y < 570){
					directionPointer = event.pointer;
					world.spaceship.dir = 1;
				}
				else if (event.x > 1080 && event.x < 1280 && event.y > 240 && event.y < 420
						&& world.isReady){
					world.spaceship.shoot();
					world.isReady = false;
				}
			}
			else if (event.type == TouchEvent.TOUCH_UP){
				if (directionPointer == event.pointer){
					world.spaceship.dir = 0;
					directionPointer = -1;
				}
				else if (event.x > 20 && event.x < 180 && event.y > 30 && event.y < 130){
					state = GameState.Paused;
					Assets.music.stop();
					if (Settings.soundEnabled)
						Assets.click.play(1);
					touchEvents.clear();
					return;
				}
			}
		}
		world.update(deltaTime);
//		world.spaceship.move(world.spaceship.dir);
//		world.createAsteroid(deltaTime);
//		world.incrementScore();
//		world.moveAsteroids();
//		world.removeAsteroids();
//		world.checkDeath();
//		if (world.spaceship.missile != null){
//			world.spaceship.missile.move();
//			if (world.checkHit())
//				Assets.explode.play(1);
//		}
		if (world.gameOver){
			if (Settings.soundEnabled)
				Assets.gameOver.play(1);
			Assets.music.stop();
			state = GameState.GameOver;
		}
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		// TODO Auto-generated method stub
		for (int i=0;i<touchEvents.size();i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				state = GameState.Running;				
				if (Settings.soundEnabled){
					Assets.click.play(1);
					Assets.music.play();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		// TODO Auto-generated method stub
		for (int i=0;i<touchEvents.size();i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP && event.x > 415 && event.x < 865 && event.y > 350 && event.y < 440){
				Settings.save(game.getFileIO());
				game.setScreen (new MainMenuScreen(game));
				if (Settings.soundEnabled)
					Assets.click.play(1);
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0);
		drawWorld(world);
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
	}

	private void drawWorld(World world) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		
		Pixmap asteroid = null;
		Asteroid ast;
		for (int i=0;i<world.asteroids.size();i++){
			ast = world.asteroids.get(i);
			if (ast.size == 0){
				if (ast.type == 0)
					asteroid = Assets.ast1Small;
				else if (ast.type == 1)
					asteroid = Assets.ast2Small;
				else if (ast.type == 2)
					asteroid = Assets.ast3Small;
			}
			else if (ast.size == 1){
				if (ast.type == 0)
					asteroid = Assets.ast1Medium;
				else if (ast.type == 1)
					asteroid = Assets.ast2Medium;
				else if (ast.type == 2)
					asteroid = Assets.ast3Medium;
			}
			else if (ast.size == 2){
				if (ast.type == 0)
					asteroid = Assets.ast1Big;
				else if (ast.type == 1)
					asteroid = Assets.ast2Big;
				else if (ast.type == 2)
					asteroid = Assets.ast3Big;
			}
			g.drawPixmap(asteroid, (int)ast.x, ast.y);
		}
		if (world.spaceship.missile != null)
			g.drawPixmap(Assets.laser, world.spaceship.missile.x, world.spaceship.missile.y);
		g.drawPixmap(Assets.spaceship, world.spaceship.x, world.spaceship.y);
	}

	private void drawReadyUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.ready, 415, 280);
	}

	private void drawRunningUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.controlUp, 50, 230);
		g.drawPixmap(Assets.controlDown, 50, 410);
		g.drawPixmap(Assets.controlShoot, 1130, 280);
		g.drawPixmap(Assets.controlPause, 50, 30);
		g.drawPixmap(Assets.loadingbarOut, 340, 600);
		if (!world.isReady)
			g.drawPixmap(Assets.loadingbarIn, 395, 655, 0, 0, world.missileCounter * 2, 90);
		else
			g.drawPixmap(Assets.loadingbarReady, 395, 655);
		drawNum(world.score, 1060, 30);
	}
	
	public void drawNum (int n, int x, int y){
		Graphics g = game.getGraphics();
		String digits = "" + n;
		for (int i=0;i<digits.length();i++){
			int num = Character.getNumericValue(digits.charAt(i));
			if (num == 0)
				g.drawPixmap(Assets.numbers, x, y, 9 * 40, 0, 40, 38);
			else 
				g.drawPixmap(Assets.numbers, x, y, (num - 1) * 40, 0, 40, 38);
			x+=40;
		}
	}

	private void drawPausedUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.resume, 415, 280);

		g.drawPixmap(Assets.controlUp, 50, 230);
		g.drawPixmap(Assets.controlDown, 50, 410);
		g.drawPixmap(Assets.controlShoot, 1130, 230);
		g.drawPixmap(Assets.controlStop, 1130, 410);
		g.drawPixmap(Assets.controlPause, 50, 30);
		g.drawPixmap(Assets.loadingbarOut, 340, 600);
		if (!world.isReady)
			g.drawPixmap(Assets.loadingbarIn, 395, 655, 0, 0, world.missileCounter * 2, 90);
		else
			g.drawPixmap(Assets.loadingbarReady, 395, 655);
		
		drawNum(world.score, 1060, 30);
	}

	private void drawGameOverUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.gameover, 415, 280);
		
		drawNum(world.score, 1060, 30);
	}

	@Override
	public void pause() {
		Assets.music.pause();
		if (state == GameState.Running)
			state = GameState.Paused;
		else if (state == GameState.GameOver){
			Settings.addScore(world.score);
			Settings.save(game.getFileIO());
			Asteroid.resetSpeed();
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
