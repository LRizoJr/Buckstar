package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Pair;

import com.overhedgames.buckstar.enums.AttributeLevel;
import com.overhedgames.buckstar.enums.CustomerType;
import com.overhedgames.buckstar.enums.DrinkType;
import com.overhedgames.buckstar.enums.CustomerState;
import com.overhedgames.buckstar.parameters.Parameters_Customer;

public class Customer extends GameObject {
	private CustomerType custType;
	
	private AttributeLevel menuSatisfaction;
	private AttributeLevel purchaseSatisfaction;
	private AttributeLevel wealth;
	private ArrayList<Pair<DrinkType, AttributeLevel>> drinkTypeRatings;
	private CustomerFacilityInfo facilityInfo;
	
	private long waitTime;	
	private long maxWaitTime;
	
	private CustomerState currentState;
	
	public Customer(CustomerType custType, Point location, Boolean isAnimating, CustomerFacilityInfo facilityInfo) {
		
		super(Parameters_Customer.getAnimationBitmaps(custType), location, Parameters_Customer.CUSTOMER_SPEED, 
				isAnimating, Parameters_Customer.CUSTOMER_FPS);
		
		init(custType, facilityInfo);
		
	}			
	private void init(CustomerType custType, CustomerFacilityInfo facilityInfo) {
		try {
			switch(custType) {
				case Adult:
					this.maxWaitTime = Parameters_Customer.CUSTOMER_ADULT_MAX_WAIT_TIME;
					break;
				case Elderly:
					//@todo
					break;
				case Executive:
					//@todo
					break;
				case Kid:
					//@todo
					break;
				case Teenager:
					//@todo
					break;
				default:
					//@todo
					break;
			}
			this.drinkTypeRatings = Parameters_Customer.getCustDrinkTypeRatings(custType);						
			this.facilityInfo = facilityInfo;
			this.custType = custType;
			this.currentState = Parameters_Customer.CUSTOMER_DEFAULT_STATE;	// default initial state			
		}catch(Exception ex) { 
			// @todo
		}
	}
	public void update(CustomerFacilityInfo facilityInfo) {
		switch(currentState) {
			case Browsing:
				// @todo 
				// check current facility for location of menu, set as targetLoc
				// once at loc, process menu options
				// hold for a few seconds (5 sec or so)
				// change state to Leaving or Waiting depending on menu choices avail
				break;
			case Waiting:
				waitTime++;
				if(this.waitTime > this.maxWaitTime) {
					this.currentState = CustomerState.Leaving;
				}
				break;
			case Buying:
				break;
			case Leaving:
				if(this.getTargetLocation() == null) {
					this.setTargetLocation(new Point(500,600));
					this.setIsAnimating(true);
				} else if (this.getTargetLocation().equals(this.getCurrentLocation())) {
					this.setIsAnimating(false);
				}
				break;
		}
		
		super.update();
	}

}
