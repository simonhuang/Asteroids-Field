package com.badlogic.androidgames.asteroids;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class HelpScreen extends Screen {

	public HelpScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List <TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for (int i=0;i<len;i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				if (event.x > 1050 && event.y > 650){
					game.setScreen(new MainMenuScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.instructionsButton, 415, 50);
		g.drawPixmap(Assets.infoSpaceship, 170, 150);
		g.drawPixmap(Assets.infoAsteroid, 170, 350);
		g.drawPixmap(Assets.infoShoot, 170, 550);
		
		g.drawPixmap(Assets.iconSpaceship, 870, 150);
		g.drawPixmap(Assets.iconAsteroid, 870, 350);
		g.drawPixmap(Assets.iconShoot, 870, 550);
		
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
