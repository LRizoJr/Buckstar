package com.overhedgames.buckstar.parameters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.content.*;

import com.overhedgames.buckstar.R;
import com.overhedgames.buckstar.enums.CustomerType;
import com.overhedgames.buckstar.enums.CustomerTypeFrequency;
import com.overhedgames.buckstar.enums.FacilityType;
import com.overhedgames.buckstar.globals.ApplicationData;

public final class Parameters_Facility {
	public static Activity context;
	public static final String TAG = Parameters_Facility.class.getSimpleName();
	
	public Parameters_Facility() {
		
	}
	
	public static final String COFFEE_TRUCK_NAME = "Coffee Truck";
	public static final double COFFEE_TRUCK_COST = 5000.00;
	public static final double COFFEE_TRUCK_DAILY_RENT = 100.00;
	public static final int COFFEE_TRUCK_EMPLOYEE_CAPACITY = 2;	
	private static Bitmap COFFEE_TRUCK_BITMAP; 
														
	private static final Point COFFEE_TRUCK_ENTRANCE_LEFT = new Point(0,1100);
	private static final Point COFFEE_TRUCK_ENTRANCE_RIGHT = new Point(1080,1100);
	public static final Point[] COFFEE_TRUCK_ENTRANCE_LOCATIONS = new Point[] { COFFEE_TRUCK_ENTRANCE_LEFT, COFFEE_TRUCK_ENTRANCE_RIGHT };																					
	public static final Point COFFEE_TRUCK_EXIT_LOCATION = new Point(1080,1100);
	public static final Point COFFEE_TRUCK_MENU_LOCATION = new Point(800,1100);
	public static final CustomerTypeFrequency COFFEEE_TRUCK_CUSTOMER_TYPE_FREQ_ADULT = new CustomerTypeFrequency(CustomerType.Adult, 100);
	
	public static Bitmap getFacilityBitmap(FacilityType type) {
		try {
			
			if(Parameters_Facility.COFFEE_TRUCK_BITMAP == null) {
				ApplicationData appData = (ApplicationData) Parameters_Facility.context.getApplication();
				
				Parameters_Facility.COFFEE_TRUCK_BITMAP = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Parameters_Facility.context.getResources(), 
																													R.drawable.facility_coffee_truck), 
																					appData.getScreenWidth(), appData.getScreenHeight(),false);																													
			}
		}catch(Exception ex) {
			Log.d(Parameters_Facility.TAG, "Exception caught in getFacilityBitmap: " + ex.toString());
		}
		return Parameters_Facility.COFFEE_TRUCK_BITMAP;
	}

}
