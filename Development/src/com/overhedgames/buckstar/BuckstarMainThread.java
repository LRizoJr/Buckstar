package com.overhedgames.buckstar;

import java.text.DecimalFormat;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class BuckstarMainThread extends Thread {
	private static final String TAG = BuckstarMainThread.class.getSimpleName();
	
	private static final int MAX_FPS = 30;
	private static final int MAX_FRAME_SKIPS = 5;
	private static final int FRAME_PERIOD = 1000 / MAX_FPS; // time duration of a single duration
	
	private boolean running;
		
	private SurfaceHolder surfaceHolder;	
	private GameplayView gameView; 
	
	private DecimalFormat df = new DecimalFormat("0.##"); // used to display the fps in decimal format
	
	public BuckstarMainThread(SurfaceHolder surfHolder, GameplayView view) { 
		super();
		this.surfaceHolder = surfHolder;
		this.gameView = view;		
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	@Override
	public void run() {
		Log.d(this.TAG, "Game loop start");
		
		Canvas canvas;
		long tickCount = 0L;
		long beginTime;
		
		while(this.running) { 
			canvas = null;
			tickCount++;
			
			// try to lock the canvas for exclusive pixel editing on the surface
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized(surfaceHolder) {
					beginTime = System.currentTimeMillis();
					this.gameView.update();
					
					this.gameView.render(canvas);
					
					this.fpsSynch(beginTime);
				}
			} finally {
				// in case of exception prevents the surface from being left in inconsistent state
				if(canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas); // finalizes drawing and displays canvas on the screen
				}
			} // end finally			
		}
		Log.d(this.TAG, "Game loop executed");
	}
	
	// function sleeps or calls update of the gameView depending on FPS performance
	public void fpsSynch(long beginTime) {
		int framesSkipped = 0;
		long timeDiff = System.currentTimeMillis() - beginTime;
		int sleepTime = (int) (BuckstarMainThread.FRAME_PERIOD - timeDiff);
		
		// if sleeptime is > 0, we must sleep to keep our FPS constant
		if(sleepTime > 0) {
			try {
				BuckstarMainThread.sleep(sleepTime);
			} catch(InterruptedException ex) {}						
		}
		// if sleeptime is < 0, we took too long to render
		// must call update now to keep the game responsive
		// we "skip" the rendering of a frame to save time, while still processing game events
		// add "frame_period" diff to our sleep time until we've caught up (sleepTime >= 0)
		while(sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
			this.gameView.update();
			sleepTime += (int) BuckstarMainThread.FRAME_PERIOD;
			framesSkipped++;
		}
	}

}

