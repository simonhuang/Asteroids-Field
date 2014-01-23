package com.badlogic.androidgames.asteroids;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.asteroids.Assets;
import com.badlogic.androidgames.asteroids.HelpScreen;
import com.badlogic.androidgames.asteroids.Settings;

public class MainMenuScreen extends Screen{

	public MainMenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List <TouchEvent> touchEvents = game.getInput().getTouchEvents();
		for (int i=0;i<touchEvents.size();i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				if (inBounds(event, 505, 320, 270, 90)){
					game.setScreen(new GameScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if (inBounds(event, 415, 390, 500, 90)){
					game.setScreen(new HelpScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if (inBounds(event, 50, 600, 450, 90)){
					game.setScreen(new HighscoreScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				if (inBounds(event, 780, 600, 450, 90)){
					Settings.soundEnabled = !Settings.soundEnabled;
					if (Settings.soundEnabled)
						Assets.click.play(1);
				}
			}
		}
	}
	
	public boolean inBounds (TouchEvent e, int x, int y, int width, int height){
		if (e.x > x && e.x < x+width-1 && e.y > y && e.y < y+height-1)
			return true;
		return false;
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.logo, 190, 50);
		g.drawPixmap(Assets.playButton, 505, 320);
		g.drawPixmap(Assets.instructionsButton, 390, 420);
		g.drawPixmap(Assets.highscoresButton, 50, 600);
		if (Settings.soundEnabled)
			g.drawPixmap(Assets.soundonButton, 780, 600);
		else
			g.drawPixmap(Assets.soundoffButton, 780, 600);
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
