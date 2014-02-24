package com.example.helloandroid;

import java.util.ArrayList;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;

public class HelloAndroidView extends SurfaceView implements
		SurfaceHolder.Callback {
	// Constant used for logging purposes, present in every class
	private static final String TAG = HelloAndroidView.class.getSimpleName();

	private HelloAndroidMainThread mainThread;
	private ArrayList<Person> people;

	private String avgFPS;

	public HelloAndroidView(Context context) {
		super(context);
		this.getHolder().addCallback(this);

		// Instantiate game loop thread when this View is initialized
		this.mainThread = new HelloAndroidMainThread(this.getHolder(), this);
		this.people = new ArrayList<Person>();
		this.avgFPS = "";
		this.setFocusable(true);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		// when the surface is created, we can safely start our main loop

		mainThread.setRunning(true);
		mainThread.start(); // invoke start of main loop
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
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

	private boolean played = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (event.getY() > this.getHeight() - 50) {
				mainThread.setRunning(false);
				((Activity) this.getContext()).finish(); // this exits the
															// application
			} else {
				Log.d(this.TAG,
						"Coords: x= " + event.getX() + ", y= " + event.getY());
				Log.d(this.TAG, "Creating person at current coordinates");

				Bitmap animationImages[] = new Bitmap[3];
				animationImages[0] = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.person1);
				animationImages[1] = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.person2);
				animationImages[2] = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.person3);
				for (int i = 0; i < animationImages.length; i++) {
					animationImages[i] = Bitmap.createScaledBitmap(
							animationImages[i],
							(int) (animationImages[i].getWidth() * .2),
							(int) (animationImages[i].getHeight() * .2), false);
					

				}

				Person p = new Person(animationImages, (int) event.getX(),
						(int) event.getY(), 5);

				people.add(p);
				Log.d(this.TAG, "P Coordinates: " + p.getX() + "," + p.getY());
			}

		}
		return super.onTouchEvent(event);
	}

	/*
	 * @Override protected void onDraw(Canvas canvas) { // Draw/Create objects
	 * here using the canvas object /*Log.d(this.TAG, "Drawing..."); for(int i =
	 * 0; i < people.size(); i++) { people.get(i).draw(canvas); } }
	 */

	protected void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);		
		fontTesting(canvas);
		for (int i = 0; i < people.size(); i++) {
			people.get(i).draw(canvas);
		}
		// Log.d(TAG, "FPS: " + this.avgFPS);
		displayFPS(canvas, this.avgFPS);
	}

	private void displayFPS(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps, this.getWidth() - 50, 20, paint);			
		}
	}

	private void fontTesting(Canvas canvas) {
		Paint p = new Paint();
		p.setAntiAlias(true);
		
		// Use of dimension with screen-independent font size
		p.setTextSize(40);
		p.setUnderlineText(true);
		canvas.drawText("Density-Independent Size", 50, 210, p);
		
		p.setTextSize(this.getResources().getDimension(R.dimen.font_size_std));
		p.setColor(Color.WHITE);
		
		p.setTypeface(Typeface.SERIF);
		canvas.drawText("Test String - Serif", 50, 50, p);
		
		p.setTypeface(Typeface.SANS_SERIF);
		canvas.drawText("Test String - Sans Serif", 50, 100, p);
		
		p.setTypeface(Typeface.MONOSPACE);
		canvas.drawText("Test String - Monospace", 50, 150, p);
		
		// Use of non-independent font size
		p.setTextSize(40);
		p.setUnderlineText(true);
		canvas.drawText("Non-Independent Size", 50, 210, p);
		p.setTextSize(30);
		p.setUnderlineText(false);
		p.setTypeface(Typeface.SERIF);
		canvas.drawText("Test String - Serif", 50, 250, p);
		
		p.setTypeface(Typeface.SANS_SERIF);
		canvas.drawText("Test String - Sans Serif", 50, 300, p);
		
		p.setTypeface(Typeface.MONOSPACE);
		canvas.drawText("Test String - Monospace", 50, 350, p);
	}
	
	protected void update() {
		for (int i = 0; i < people.size(); i++) {
			Person p = people.get(i);
			checkForCollision(p);
			p.update();
		}
	}

	private void checkForCollision(Person p) {
		// check for collision against right
		if (p.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
				&& p.getX() + p.getBitmap().getWidth() / 2 >= this.getWidth()) {
			p.getSpeed().toggleXDirection();
		}

		// check for collision against left
		if (p.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
				&& p.getX() - p.getBitmap().getWidth() / 2 <= 0) {
			p.getSpeed().toggleXDirection();
		}

		// check for collision against top
		if (p.getSpeed().getyDirection() == Speed.DIRECTION_UP
				&& p.getY() + p.getBitmap().getHeight() / 2 >= this.getHeight()) {
			p.getSpeed().toggleYDirection();
		}

		// check for collision against left
		if (p.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
				&& p.getY() - p.getBitmap().getHeight() / 2 <= 0) {
			p.getSpeed().toggleYDirection();
		}
	}

	public void setAVGFPS(String fps) {
		this.avgFPS = fps;
	}

}
