package com.badlogic.androidgames.framework.impl;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;

import android.view.WindowManager;
import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Screen;

public class AndroidGame extends Activity implements Game{
	
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = getResources().getConfiguration().orientation
				== Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 1280 : 720;
		int frameBufferHeight = isLandscape ? 720 : 1280;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);
		
		float scaleX = (float) frameBufferWidth
				/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight
				/ getWindowManager().getDefaultDisplay().getHeight();
		
		
		//ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(5);
		renderView = new AndroidFastRenderView (this, frameBuffer);
		//stpe.scheduleAtFixedRate(renderView, 0, 5, TimeUnit.SECONDS);
		
		
		graphics = new AndroidGraphics (getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager) getSystemService (Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
	
	public void onResume(){
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}
	
	public void onPause(){
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		
		if(isFinishing())
			screen.dispose();
	}
	@Override
	public Input getInput() {
		// TODO Auto-generated method stub
		return input;
	}
	@Override
	public FileIO getFileIO() {
		// TODO Auto-generated method stub
		return fileIO;
	}
	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return graphics;
	}
	@Override
	public Audio getAudio() {
		// TODO Auto-generated method stub
		return audio;
	}
	@Override
	public void setScreen(Screen screen) {
		// TODO Auto-generated method stub
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	@Override
	public Screen getCurrentScreen() {
		// TODO Auto-generated method stub
		return screen;
	}
	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return null;
	}

}
