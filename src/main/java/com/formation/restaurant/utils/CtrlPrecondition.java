package com.formation.restaurant.utils;

import com.formation.restaurant.exceptions.RessourceNotFoundException;
import com.formation.restaurant.models.Restaurant;

public final class CtrlPrecondition {
	
	public static Restaurant checkIsFound(Restaurant restaurant) {
		if(restaurant == null) {
			throw new RessourceNotFoundException();
		}
		return restaurant;
	}
}
