package com.overhedgames.buckstar.enums;

public class CustomerTypeFrequency {
	
	private final CustomerType customerType;
	private final int frequency;
	
	public CustomerTypeFrequency(CustomerType type, int frequency) {
		this.customerType = type;
		this.frequency = frequency;
	}
	
	public CustomerType CustomerType() { 
		return this.customerType;
	}
	
	public int Frequency() { 
		return this.frequency;
	}

}
