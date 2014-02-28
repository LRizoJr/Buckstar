package com.overhedgames.buckstar;

import com.overhedgames.buckstar.enums.FacilityType;

public class Facility extends Buyable {
	private double dailyRent;
	private int employeeCapacity;
	private FacilityType facilityType;
	
	// @todo custTypeFreqList
	public Facility(FacilityType type) {
		super("", 0);
		this.facilityType = type;
		init(type);
	}
	
	private void init(FacilityType type) {
		switch(facilityType) {
					
		case CoffeeTruck:
			this.name = "Coffee Truck";
			this.cost = 5000.00;
			this.dailyRent = 100.00;
			this.employeeCapacity = 2;			
		}
	}
}
