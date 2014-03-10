package com.overhedgames.buckstar.parameters;

import java.util.ArrayList;

import com.overhedgames.buckstar.R;
import com.overhedgames.buckstar.Speed;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.util.Pair;

import com.overhedgames.buckstar.enums.*;
import com.overhedgames.buckstar.globals.ApplicationData;

public final class Parameters_Customer {
	public static String TAG = Parameters_Customer.class.getSimpleName();
	
	public static Activity context; 
	private final static int CUSTOMER_SPEED_X = 5;
	private final static int CUSTOMER_SPEED_Y = 5;
	private final static int CUSTOMER_BITMAP_FRAMES = 6;
	public final static Speed CUSTOMER_SPEED = new Speed(Parameters_Customer.CUSTOMER_SPEED_X,Parameters_Customer.CUSTOMER_SPEED_Y);
	public static final int CUSTOMER_FPS = 15;
	public static final CustomerState CUSTOMER_DEFAULT_STATE = CustomerState.Browsing;
	public static final int CUSTOMER_ADULT_MAX_WAIT_TIME = 75; // 1 tick = 33ms @ target 30FPS (75 x 33ms = ~2.5 secs)
	
	public static ArrayList<Pair<DrinkType, AttributeLevel>> getCustDrinkTypeRatings(CustomerType custType) { 
		ArrayList<Pair<DrinkType, AttributeLevel>> drinkTypeRatings = new ArrayList<Pair<DrinkType,AttributeLevel>>();
		
		try {
			switch(custType) {
			case Adult:
				drinkTypeRatings.add(new Pair<DrinkType, AttributeLevel>(DrinkType.Standard, AttributeLevel.High));
				//@todo add drink type rating definitions
				break;
			case Elderly:
				break;
			case Executive:
				break;
			case Kid:
				break;
			case Teenager:
				break;
			default:
				break; 			
			}
			
		}catch(Exception ex) { 
			//@todo
		}
		return drinkTypeRatings;
	}
	
	public static Bitmap[] getAnimationBitmaps(CustomerType custType) { 
		Bitmap[] custBitmaps = new Bitmap[Parameters_Customer.CUSTOMER_BITMAP_FRAMES];
		try {
			switch(custType) {
				case Adult:
						custBitmaps[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.person1);
						custBitmaps[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.person1);
						custBitmaps[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.person2);
						custBitmaps[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.person2);
						custBitmaps[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.person3);
						custBitmaps[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.person3);
					break;
				case Elderly:
					break;
				case Executive:
					break;
				case Kid:
					break;
				case Teenager:
					break;
				default:
					break;			
			}
		}catch(Exception ex) { 
			//@todo
		}
		return custBitmaps;
	}
	
	public static int getCustomerFacingDirection(Point spawnPoint) { 
		try { 
			ApplicationData appData = (ApplicationData) Parameters_Customer.context.getApplication();
			int widthMidPoint = appData.getScreenWidth() / 2;
			if(spawnPoint.x < widthMidPoint) { 
				return Direction.Right;
			} else {
				return Direction.Left;
			}
			
		}catch(Exception ex) { 
			Log.d(Parameters_Customer.TAG, "Exception caught in getCustomerFacingDireciton: " + ex.toString());
			return Direction.Left;
		}
	}

}
