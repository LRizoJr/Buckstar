package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.graphics.Point;

public class CustomerFacilityInfo {
	private ArrayList<Customer> custsInLine;
	private ArrayList<CoffeeDrink> drinksMenu;
	private Point menuLocation;
	
	public CustomerFacilityInfo(ArrayList<Customer> custsInLine, ArrayList<CoffeeDrink> drinksMenu, Point menuLocation) {
		this.custsInLine = custsInLine;
		this.drinksMenu = drinksMenu;
		this.menuLocation = menuLocation;
	}

}
