package com.example.helloandroid;

import java.text.DecimalFormat;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class HelloAndroidMainThread extends Thread {
	private static final String TAG = HelloAndroidMainThread.class.getSimpleName();
	
	private static final int MAX_FPS = 60;
	private static final int MAX_FRAME_SKIPS = 5;
	private static final int FRAME_PERIOD = 1000 / MAX_FPS; // time duration of a single duration
		
	private boolean running;
	
	private SurfaceHolder surfaceHolder;
	private HelloAndroidView gameView;
	
	/** Variables needed for statistics **/
	private int frameCountPerStatCycle;
	private int totalFrameCount;
	private int statsCount; // used to keep track of how many times we've called the Stats method
	private int framesSkippedPerStatCycle;
	private int totalFramesSkipped;
	
	private long statusIntervalTimer;
	private long lastStatusStore; // stores the endtime of the last storeFPSStats() call
	
	private static final int STAT_INTERVAL = 1000; // duration of how frequency to calculate FPS stat (stored in ms)
	private static final int FPS_HISTORY_NR = 10; // indicates how many fps values to keep while calculating our running avg
	private double fpsStore[];
	private double avgFPS;
	
	private DecimalFormat df = new DecimalFormat("0.##"); // used to display the fps in decimal format
	
	public HelloAndroidMainThread(SurfaceHolder surfHolder, HelloAndroidView view) {
		super();
		this.surfaceHolder = surfHolder;
		this.gameView = view;
		this.fpsStore = new double[HelloAndroidMainThread.FPS_HISTORY_NR];
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}	
 
	@Override
	public void run() {		
		
		Canvas canvas;
		long tickCount = 0L;
		Log.d(this.TAG, "Starting game loop");
		
		long beginTime; 	// marks start time of the main loop iteration
		long timeDiff;		// diff between beginTime and the time it took call update()
		int sleepTime;		// number of frames to sleep
		int framesSkipped;	// number of frames skipped 
	
		sleepTime = 0;	
		
		while(this.running) {
			canvas = null;
			tickCount++;
			// try to lock the canvas for exclusive pixel editing on the surface
			try {
				canvas = this.surfaceHolder.lockCanvas();
				
				synchronized (surfaceHolder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0; 
					
					// update game state
					this.gameView.update();
					// draw canvas on the panel
					this.gameView.render(canvas);
					
					timeDiff = System.currentTimeMillis() - beginTime;
					sleepTime = (int) (HelloAndroidMainThread.FRAME_PERIOD - timeDiff);
					
					// if sleeptime is > 0, we must sleep to keep our FPS constant
					if(sleepTime > 0) {
						try {
							HelloAndroidMainThread.sleep(sleepTime);
						} catch(InterruptedException ex) {}						
					}
					// if sleeptime is < 0, we took too long to render
					// must call update now to keep the game responsive
					// we "skip" the rendering of a frame to save time, while still processing game events
					// add "frame_period" diff to our sleep time until we've caught up (sleepTime >= 0)
					while(sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
						this.gameView.update();
						sleepTime += (int) HelloAndroidMainThread.FRAME_PERIOD;
						framesSkipped++;
					}	
					
					framesSkippedPerStatCycle += framesSkipped;
					storeFPSStats();
				}
			} finally {
				// in case of exception prevents the surface from being left in inconsistent state
				if(canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas); // finalizes drawing and displays canvas on the screen
				}
			} // end finally

			// render state to the screen
		}
		
		Log.d(this.TAG, "Game loop executed");
	}	
	
	// runs every tick, but only calculates FPS depending on the status_interval set time
	private void storeFPSStats() { 
		frameCountPerStatCycle++;
		totalFrameCount++;
		
		statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer); // Store the actual time passed since last tick
		
		if(statusIntervalTimer >= lastStatusStore + HelloAndroidMainThread.STAT_INTERVAL) {
			// calculate actual fps  per status check interval
			double actualFPS = (double)(frameCountPerStatCycle / (HelloAndroidMainThread.STAT_INTERVAL / 1000));
			
			// store the calculated FPS in an array so we can calculate (& display) a running avg
			int arrPosition = (int) statsCount % HelloAndroidMainThread.FPS_HISTORY_NR; // calculate position in which to store the fps
			fpsStore[arrPosition] = actualFPS;
			
			statsCount++;
			
			// calculate avg fps
			double totalFPS = 0.0;
			// sum up the stored fps values
			for(int i = 0; i < HelloAndroidMainThread.FPS_HISTORY_NR; i++) {
				totalFPS += fpsStore[i];
			}
			
			//for first N - 1 reads, use statCount as the divisor
			if(statsCount < HelloAndroidMainThread.FPS_HISTORY_NR) {
				avgFPS = totalFPS / statsCount;
			} else {
				avgFPS = totalFPS / HelloAndroidMainThread.FPS_HISTORY_NR;
			}			
			
			totalFramesSkipped += framesSkippedPerStatCycle;
			// reset stats after completed IntervalTimer
			framesSkippedPerStatCycle = 0;
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;
			
			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
			
			gameView.setAVGFPS("FPS: " + df.format(avgFPS));
		}
	}

}
