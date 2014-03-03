package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Pair;
import android.view.MenuItem;

import com.overhedgames.buckstar.enums.*;
import com.overhedgames.buckstar.parameters.*;

public class Facility extends GameObject {
	private double dailyRent;
	private int employeeCapacity;
	private FacilityType facilityType;
	private CustomerFacilityInfo facilityInfo;
	
	private ArrayList<CustomerTypeFrequency> custTypeFrequency;
	private ArrayList<Customer> customers;
	
	public Facility(FacilityType type) {
		super(new Point(0,0));
		this.facilityType = type;
		init(type);
	}
	
	private void init(FacilityType type) {
		switch(facilityType) {				
			case CoffeeTruck:
				this.animationBitmaps = new Bitmap [] { Parameters_Facility.COFFEE_TRUCK_BITMAP };
				this.dailyRent = Parameters_Facility.COFFEE_TRUCK_DAILY_RENT;
				this.employeeCapacity = Parameters_Facility.COFFEE_TRUCK_EMPLOYEE_CAPACITY;	
				this.facilityInfo = new CustomerFacilityInfo(new ArrayList<Customer>(), new ArrayList<CoffeeDrink>(), 
						Parameters_Facility.COFFEE_TRUCK_MENU_LOCATION, Parameters_Facility.COFFEE_TRUCK_EXIT_LOCATION);
				break;
			case BeachsideCoffeeShop:
				break;
			case CampusCoffeeShop:
				break;
			case MallCoffeeShop:
				break;
			case StripmallCoffeeShop:
				break;
			default:
				break;
		}
		this.custTypeFrequency = Facility.generateCustTypeFrequency(facilityType);
	}
	
	public void update() {
		// @todo
		try { 
			if(this.customers.size() == 0) {
				this.addCustomer();			
			}
			
			//@todo run through facility logic (serve next customer in line)
			// customer should be asked for order
			// customer should be served
			// customer should give feedback
			// customer should leave
			
			for(int i = 0; i < this.customers.size(); i++) {
				this.customers.get(i).update(this.facilityInfo);
			}
		}catch(Exception ex) {
			
		}
	}
	
	public void render(Canvas canvas) {
		try {
			super.render(canvas);
			for(int i = 0; i < this.customers.size(); i++) { 
				this.customers.get(i).render(canvas);				
			}
		}catch(Exception ex) {
			
		}		
	}
	
	private void addCustomer() {
		Point loc = new Point(200,600);		
		Speed speed = new Speed(1, 1);
		Customer c = new Customer(CustomerType.Adult, loc, true, this.facilityInfo);		
		this.customers.add(c);
	}
	
	private static ArrayList<CustomerTypeFrequency> generateCustTypeFrequency(FacilityType facilityType) {
		try {
			ArrayList<CustomerTypeFrequency> custTypeFreq = new ArrayList<CustomerTypeFrequency>();
			
			switch(facilityType) { 
				case CoffeeTruck:
					custTypeFreq.add(Parameters_Facility.COFFEEE_TRUCK_CUSTOMER_TYPE_FREQ_ADULT);
					break;
				default:
					break;			
			}
			
			return custTypeFreq;
		} catch(Exception ex) { 
			// @todo
		}
		return null;
	}
}
