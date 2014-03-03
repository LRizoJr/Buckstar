package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.util.Log;

public class CoffeeDrink extends Buyable {
	private static final String TAG = CoffeeDrink.class.getSimpleName();
	
	private boolean isUnlocked;
	private ArrayList<Ingredient> requiredIngredients;
	
	public CoffeeDrink(String name, ArrayList<Ingredient> requiredIngredients) {
		super(name,0);
		this.requiredIngredients = requiredIngredients;		
		this.cost = calculateCost();
	}
	
	private double calculateCost() {
		double totalCost = 0;
		try {			
			for(int i = 0; i < this.requiredIngredients.size(); i++){
				totalCost += requiredIngredients.get(i).cost;			
			}
		} catch(Exception ex) {
			Log.d(this.TAG, "Exception caught while trying to calculate cost. Details: " + ex.getMessage());
		}
		return totalCost;
	}
	
}
