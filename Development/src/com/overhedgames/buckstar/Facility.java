package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.util.Pair;

import com.overhedgames.buckstar.enums.*;
import com.overhedgames.buckstar.parameters.*;

public class Facility extends Buyable {
	private double dailyRent;
	private int employeeCapacity;
	private FacilityType facilityType;
	private CustomerFacilityInfo facilityInfo;
	private ArrayList<CustomerTypeFrequency> custTypeFrequency;	
	
	public Facility(FacilityType type) {
		super("", 0);
		this.facilityType = type;
		init(type);
	}
	
	private void init(FacilityType type) {
		switch(facilityType) {				
			case CoffeeTruck:
				this.name = Parameters_Facility.COFFEE_TRUCK_NAME;
				this.cost = Parameters_Facility.COFFEE_TRUCK_COST;
				this.dailyRent = Parameters_Facility.COFFEE_TRUCK_DAILY_RENT;
				this.employeeCapacity = Parameters_Facility.COFFEE_TRUCK_EMPLOYEE_CAPACITY;					
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
			
		}catch(Exception ex) {
			
		}
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
