package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameplayView extends SurfaceView implements Callback {
	
	private static final String TAG = GameplayView.class.getSimpleName();	
	
	private BuckstarMainThread mainThread;
	
	private ArrayList<Customer> customers;
	
	public GameplayView(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		this.mainThread = new BuckstarMainThread(this.getHolder(), this);
		this.customers = new ArrayList<Customer>();
		this.setFocusable(true);
		
	}
	
	public void update() {
		// update customers
		for(int i = 0; i < this.customers.size(); i++) {
			this.customers.get(i).update();
		}
	}
	
	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);		
		// render customers		
		for(int i = 0; i < this.customers.size(); i++) { 
			this.customers.get(i).render(canvas);
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		//when the surface is created, we can safely start our main loop
		mainThread.setRunning(true);
		mainThread.start(); // invoke start of main loop
		
		addCustomer();
		
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
	
	private void addCustomer() {
		Bitmap animationImages[] = new Bitmap[3];
		
		animationImages[0] = BitmapFactory.decodeResource(this.getResources(), R.drawable.person1);
		animationImages[1] = BitmapFactory.decodeResource(this.getResources(), R.drawable.person2);
		animationImages[2] = BitmapFactory.decodeResource(this.getResources(), R.drawable.person3);
		
		Point loc = new Point(200,600);
		
		Speed speed = new Speed(1, 1);
		
		Customer c = new Customer(animationImages, loc, speed, false, 0, null, 250);
		this.customers.add(c);
	}
}
