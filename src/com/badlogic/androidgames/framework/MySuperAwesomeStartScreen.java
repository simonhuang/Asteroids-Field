package com.badlogic.androidgames.framework;

import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class MySuperAwesomeStartScreen extends Screen{
	Pixmap awesomePic;
	int x;	
	
	public MySuperAwesomeStartScreen(Game game) {
		super(game);
		awesomePic = game.getGraphics().newPixmap("data/pic.png", PixmapFormat.RGB565);
	}

	@Override
	public void update(float deltaTime) {
		x += 1;
		if (x > 100)
			x=0;		
	}

	@Override
	public void present(float deltaTime) {
		game.getGraphics().clear(0);
		game.getGraphics().drawPixmap(awesomePic, x, 0, 0, 0, awesomePic.getWidth(), awesomePic.getHeight());
		
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
		awesomePic.dispose();
		
	}

}
