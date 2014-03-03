package com.overhedgames.buckstar.parameters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.overhedgames.buckstar.R;
import com.overhedgames.buckstar.enums.CustomerType;
import com.overhedgames.buckstar.enums.CustomerTypeFrequency;

public final class Parameters_Facility {
	public static Activity Context;
	
	public static final String COFFEE_TRUCK_NAME = "Coffee Truck";
	public static final double COFFEE_TRUCK_COST = 5000.00;
	public static final double COFFEE_TRUCK_DAILY_RENT = 100.00;
	public static final int COFFEE_TRUCK_EMPLOYEE_CAPACITY = 2;	
	public static final Bitmap COFFEE_TRUCK_BITMAP = BitmapFactory.decodeResource(Context.getResources(), R.drawable.facility_coffee_truck);	
	public static final Point COFFEE_TRUCK_EXIT_LOCATION = new Point(500,500);
	public static final Point COFFEE_TRUCK_MENU_LOCATION = new Point(400,500);
	public static final CustomerTypeFrequency COFFEEE_TRUCK_CUSTOMER_TYPE_FREQ_ADULT = new CustomerTypeFrequency(CustomerType.Adult, 100);	

}
