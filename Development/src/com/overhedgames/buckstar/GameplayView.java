package com.overhedgames.buckstar;

import java.util.ArrayList;

import com.overhedgames.buckstar.enums.FacilityType;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameplayView extends SurfaceView implements Callback {
	
	private static final String TAG = GameplayView.class.getSimpleName();	
	
	private BuckstarMainThread mainThread;
	
	private Facility facility;
	
	public GameplayView(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		this.mainThread = new BuckstarMainThread(this.getHolder(), this);		
		this.setFocusable(true);
		init();
	}
	
	private void init() {
		try {
			this.facility = new Facility(getContext(), FacilityType.CoffeeTruck);
		}catch(Exception ex) { 
			Log.d(this.TAG, "Exception caught in init: " + ex.toString());
		}				
	}
	
	public void update() {
		// update		
		try {
			if(this.facility == null) {
				Log.d(this.TAG, "Exception: facility is null");
			}
			this.facility.update();
		} catch(Exception ex) { 
			Log.d(this.TAG, "Exception caught in GameplayViewUpdate: " + ex.toString());
			
		}
	}
	
	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);		
		// render customers		
		this.facility.render(canvas);
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {		
		//when the surface is created, we can safely start our main loop
		Log.d(this.TAG, "Surface created");
		mainThread.setRunning(true);
		mainThread.start(); // invoke start of main loop						
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// when the surface is destroyed, we need to shut down
		// tell the mainThread to shut down and wait until it finishes
		boolean retry = true;
		while (retry) {
			try {
				mainThread.join(); // blocks thread until it dies
				retry = false; // if we hit this point, the thread is now dead
								// (success!)
			} catch (InterruptedException ex) {
				// try again
			}
		}
	}
}
