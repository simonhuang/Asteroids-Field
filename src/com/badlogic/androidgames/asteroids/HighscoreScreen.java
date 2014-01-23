package com.badlogic.androidgames.asteroids;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class HighscoreScreen extends Screen {

	public HighscoreScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
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

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List <TouchEvent> touchEvents = game.getInput().getTouchEvents();
		for (int i=0;i<touchEvents.size();i++){
			TouchEvent e = touchEvents.get(i);
			if (e.type == TouchEvent.TOUCH_UP){
				if (e.x > 1050 && e.y > 650)
					game.setScreen(new MainMenuScreen(game));
				if (Settings.soundEnabled)
					Assets.click.play(1);
				return;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.highscoresButton, 415, 50);
		for (int i=0;i<5;i++){
			drawNum(Settings.highscores[i], 415, 200 + 100 * i);
		}
		g.drawPixmap(Assets.backButton, 1050, 650);
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
