package com.overhedgames.buckstar;

import com.overhedgames.buckstar.enums.Direction;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

public class GameObject {
	private static final int DEF_ANIMATION_FPS = 5;
	protected Point currentLocation;
	protected Point targetLocation;
	
	protected Bitmap[] animationBitmaps;
	protected int currentBitmapIndex;
	private Boolean isAnimating;
		
	private int framePeriod;
	private long frameTicker;
		
	private Speed speed;
	
	public GameObject(Point location) { 
		this.setCurrentLocation(location);
		this.isAnimating = false;
		this.init(0);
	}
	
	public GameObject(Bitmap objectBitmap, Point location) {
		this.animationBitmaps = new Bitmap[] { objectBitmap };
		this.setCurrentLocation(location);
		this.isAnimating = false;
		
		this.init(0);
	}
	public GameObject(Bitmap[] animationBitmaps, Point location, Speed speed, 
					Boolean isAnimating, int animationFPS) {
		this.animationBitmaps = animationBitmaps;
		this.setCurrentLocation(location);
		this.speed = speed;
		this.isAnimating = isAnimating;		
		
		init(animationFPS);
	}
	
	public GameObject(Bitmap[] animationBitmaps, Point location, Speed speed, 
					Boolean isAnimating, int animationFPS, Point targetLocation) {
		this(animationBitmaps, location, speed, isAnimating, animationFPS);
		this.targetLocation = targetLocation;
	}
	
	private void init(int animationFPS) {
		this.currentBitmapIndex = 0;
		if(animationFPS == 0) {
			animationFPS = GameObject.DEF_ANIMATION_FPS;  
		}
		
		this.framePeriod = 1000 / animationFPS;
		this.frameTicker = 1;		
	}
	
	public void update() {
		long gameTime = System.currentTimeMillis();
		
		if(gameTime > frameTicker + framePeriod)
		{
			frameTicker = gameTime;
			if(this.isAnimating) {
				this.currentBitmapIndex = (this.currentBitmapIndex + 1) % this.animationBitmaps.length;
			}
		}
		
		if(this.speed != null && this.targetLocation != null) {
			if(this.targetLocation.x != this.currentLocation.x) {
				this.currentLocation.x += (this.speed.getxDirection() * this.speed.getXv());
			}
			
			if(this.targetLocation.y != this.currentLocation.y){
				this.currentLocation.y += (this.speed.getyDirection() * this.speed.getYv());
			}			
		}
	}
	
	public void render(Canvas canvas) {
		if(animationBitmaps.length > 0) {
			//canvas.drawBitmap(animationBitmaps[currentBitmapIndex],
				//		currentLocation.x - (animationBitmaps[currentBitmapIndex].getWidth() / 2),
					//	currentLocation.y - (animationBitmaps[currentBitmapIndex].getHeight() / 2),
						//null);
			
			canvas.drawBitmap(animationBitmaps[currentBitmapIndex], currentLocation.x,  currentLocation.y, null);					
		}
	}
	
	private void setCurrentLocation(Point p) {
		if(p == null) {
			this.currentLocation = null;
		} else {
			this.currentLocation = new Point(p.x, p.y);		
		}
	}
	public Point getCurrentLocation() {
		return this.currentLocation;
	}
	
	public Point getTargetLocation() {
		return this.targetLocation;
	}
	
	public Speed getSpeed() {
		return this.speed;
	}
	
	public Boolean getIsAnimating() {
		return this.isAnimating;
	}
	
	public void setIsAnimating(Boolean isAnimating) {
		this.isAnimating = isAnimating;
	}
	
	public void setTargetLocation(Point target) {		
			if(target == null) {
				this.targetLocation = null;
			} else {
				this.targetLocation = new Point(target.x, target.y);	
			}
			if(this.targetLocation != null) {
				if(this.targetLocation.x < this.currentLocation.x) {
					this.speed.setxDirection(Direction.Left);				
				} else {
					this.speed.setxDirection(Direction.Right);
				}
				
				if(this.targetLocation.y < this.currentLocation.y) {
					this.speed.setyDirection(Direction.Up);
				} else {
					this.speed.setyDirection(Direction.Down);
				}							
			}
	}
}
