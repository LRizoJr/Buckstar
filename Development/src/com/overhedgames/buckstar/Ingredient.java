package com.overhedgames.buckstar;

import com.overhedgames.buckstar.enums.*;

public class Ingredient extends Buyable {
	private IngredientType type;
	private QualityLevel qualityLevel;
	
	private boolean hasQuality;
	
	public Ingredient(String name, double cost, IngredientType type, QualityLevel qualityLevel) {
		super(name, cost);
		this.type = type;
		this.qualityLevel = qualityLevel;
	}
}
