package com.badlogic.androidgames.asteroids;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		
		/*MAIN MENU*/
		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.logo = g.newPixmap("title.png", PixmapFormat.ARGB4444);
		
		Assets.playButton = g.newPixmap("buttons/button_play.png", PixmapFormat.ARGB4444);
		Assets.instructionsButton = g.newPixmap("buttons/button_instructions.png", PixmapFormat.ARGB4444);
		Assets.highscoresButton = g.newPixmap("buttons/button_highscores.png", PixmapFormat.ARGB4444);
		Assets.soundonButton = g.newPixmap("buttons/soundon.png", PixmapFormat.ARGB4444);
		Assets.soundoffButton = g.newPixmap("buttons/soundoff.png", PixmapFormat.ARGB4444);
		
		/*INSTRUCTIONS*/
		Assets.infoSpaceship = g.newPixmap("info_spaceship.png", PixmapFormat.ARGB4444);
		Assets.infoAsteroid = g.newPixmap("info_ast.png", PixmapFormat.ARGB4444);
		Assets.infoShoot = g.newPixmap("info_shoot.png", PixmapFormat.ARGB4444);
		
		Assets.iconSpaceship = g.newPixmap("icon_spaceship.png", PixmapFormat.ARGB4444);
		Assets.iconAsteroid = g.newPixmap("asteroids/ast1big.png", PixmapFormat.ARGB4444);
		Assets.iconShoot = g.newPixmap("target.png", PixmapFormat.ARGB4444);
		
		Assets.backButton = g.newPixmap("buttons/button_back.png", PixmapFormat.ARGB4444);
		
		Assets.highscoresHeader = g.newPixmap("buttons/button_highscores.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		
		/*GAME CONTROL*/
		Assets.controlUp = g.newPixmap("control/control_up.png", PixmapFormat.ARGB4444);
		Assets.controlDown = g.newPixmap("control/control_down.png", PixmapFormat.ARGB4444);
		Assets.controlShoot = g.newPixmap("control/control_shoot.png", PixmapFormat.ARGB4444);
		Assets.controlStop = g.newPixmap("control/control_stop.png", PixmapFormat.ARGB4444);
		Assets.controlPause = g.newPixmap("control/control_pause.png", PixmapFormat.ARGB4444);
		
		/*GAME GRAPHICS*/
		Assets.spaceship = g.newPixmap("spaceship.png", PixmapFormat.ARGB4444);
		Assets.laser = g.newPixmap("laser.png", PixmapFormat.ARGB4444);
		Assets.loadingbarOut = g.newPixmap("loadingbar_outside.png", PixmapFormat.ARGB4444);
		Assets.loadingbarIn = g.newPixmap("loadingbar_inside.png", PixmapFormat.ARGB4444);
		Assets.loadingbarReady = g.newPixmap("loadingbar_ready.png", PixmapFormat.ARGB4444);
		Assets.ast1Big = g.newPixmap("asteroids/ast1big.png", PixmapFormat.ARGB4444);
		Assets.ast1Medium = g.newPixmap("asteroids/ast1medium.png", PixmapFormat.ARGB4444);
		Assets.ast1Small = g.newPixmap("asteroids/ast1small.png", PixmapFormat.ARGB4444);
		Assets.ast2Big = g.newPixmap("asteroids/ast2big.png", PixmapFormat.ARGB4444);
		Assets.ast2Medium = g.newPixmap("asteroids/ast2medium.png", PixmapFormat.ARGB4444);
		Assets.ast2Small = g.newPixmap("asteroids/ast2small.png", PixmapFormat.ARGB4444);
		Assets.ast3Big = g.newPixmap("asteroids/ast3big.png", PixmapFormat.ARGB4444);
		Assets.ast3Medium = g.newPixmap("asteroids/ast3medium.png", PixmapFormat.ARGB4444);
		Assets.ast3Small = g.newPixmap("asteroids/ast3small.png", PixmapFormat.ARGB4444);
		//Assets.missile = g.newPixmap("wtf.png", PixmapFormat.ARGB4444);
		
		/*GAME STATES*/
		Assets.ready = g.newPixmap("buttons/button_ready.png", PixmapFormat.ARGB4444);
		Assets.resume = g.newPixmap("buttons/button_resume.png", PixmapFormat.ARGB4444);
		Assets.gameover = g.newPixmap("buttons/button_gameover.png", PixmapFormat.ARGB4444);
		
		/*SOUNDS*/
		Assets.click = game.getAudio().newSound("sounds/click.ogg");
		Assets.shot = game.getAudio().newSound("sounds/shot.ogg");
		Assets.explode = game.getAudio().newSound("sounds/explosion.ogg");
		Assets.gameOver = game.getAudio().newSound("sounds/gameover.ogg");
		Assets.music = game.getAudio().newMusic("sounds/Space Pirates.mp3");
		
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

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
