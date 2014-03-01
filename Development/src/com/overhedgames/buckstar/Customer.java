package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Pair;

import com.overhedgames.buckstar.enums.AttributeLevel;
import com.overhedgames.buckstar.enums.DrinkType;
import com.overhedgames.buckstar.enums.CustomerState;

public class Customer extends GameObject {
	private static final CustomerState DEF_STATE = CustomerState.Browsing;
	
	private AttributeLevel menuSatisfaction;
	private AttributeLevel purchaseSatisfaction;
	private AttributeLevel wealth;
	private ArrayList<Pair<DrinkType, AttributeLevel>> drinkTypeRatings;
	private CustomerFacilityInfo facilityInfo;
	
	private long waitTime;	
	private long maxWaitTime;
	
	private CustomerState currentState;
	
	public Customer(Bitmap[] animationBitmaps, Point location, Speed speed,  Boolean isAnimating, int animationFPS, 
			ArrayList<Pair<DrinkType, AttributeLevel>> drinkTypeRatings, CustomerFacilityInfo facilityInfo, long maxWaitTime) {
		
		super(animationBitmaps, location, speed, isAnimating, animationFPS);
		
		this.drinkTypeRatings = drinkTypeRatings;
		this.maxWaitTime = maxWaitTime;
		this.currentState = Customer.DEF_STATE;	// default initial state
		this.facilityInfo = facilityInfo;
	}			
	
	public void update() {
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
				if(waitTime > maxWaitTime) {
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
