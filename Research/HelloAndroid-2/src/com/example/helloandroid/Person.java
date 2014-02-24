package com.example.helloandroid;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
// public enum CustTypes { BusinessPerson, Teenager, OldPerson };

public class Person {	
	private Bitmap animationBitmaps[];
	private int currentBitmapIndex;
	
	private int animationFPS;
	private int framePeriod;
	private long frameTicker;
	
	private int x;
	private int y;
	
	private Speed speed;	
	
	public Person(Bitmap[] animationBitmaps, int x, int y, int animationFPS) {
		this.animationBitmaps = animationBitmaps;
		this.x = x;
		this.y = y;
		this.speed = new Speed(5, 0);
		this.currentBitmapIndex = 0;
		this.framePeriod = 1000 / animationFPS;
		this.frameTicker = 1;
	}
	
	public void update() {
		long gameTime = System.currentTimeMillis();
		
		if(gameTime > frameTicker + framePeriod)
		{
			frameTicker = gameTime;
			this.currentBitmapIndex = (this.currentBitmapIndex + 1) % this.animationBitmaps.length;	
		}
		this.x += (this.speed.getxDirection() * this.speed.getXv());
		this.y += (this.speed.getyDirection() * this.speed.getYv());
	}
	
	// method used for the Person object to draw itself on the active canvas
	public void draw(Canvas canvas) {		
		canvas.drawBitmap(this.animationBitmaps[currentBitmapIndex], 
							x - (this.animationBitmaps[currentBitmapIndex].getWidth() / 2), 
							y - (this.animationBitmaps[currentBitmapIndex].getHeight() / 2), null);
	}
	
	public Bitmap getBitmap() {
		return this.animationBitmaps[currentBitmapIndex];
	}
	
	public void setBitmapAnimation(Bitmap[] animationBitmaps) {
		this.animationBitmaps = animationBitmaps;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) { 
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Speed getSpeed() {
		return this.speed;
	}		
}
