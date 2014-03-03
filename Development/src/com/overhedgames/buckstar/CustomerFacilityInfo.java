package com.overhedgames.buckstar;

import java.util.ArrayList;

import android.graphics.Point;

public class CustomerFacilityInfo {
	private ArrayList<Customer> custsInLine;
	private ArrayList<CoffeeDrink> drinksMenu;
	private Point menuLocation;
	private Point exitLocation;
	
	public CustomerFacilityInfo(ArrayList<Customer> custsInLine, ArrayList<CoffeeDrink> drinksMenu, Point menuLocation, Point exitLocation) {
		this.custsInLine = custsInLine;
		this.drinksMenu = drinksMenu;
		this.menuLocation = menuLocation;
		this.exitLocation = exitLocation;
	}
	
	public int getCustsInLineCount() {
		if(this.custsInLine != null) {
			return this.custsInLine.size();
		} else {
			return 0;
		}		
	}
	
	public ArrayList<CoffeeDrink> getDrinksMenu() {
		return this.drinksMenu;
	}
	
	public Point getMenuLocation() { 
		return this.menuLocation;
	}
	
	public Point getExitLocation() { 
		return this.exitLocation;
	}

}
