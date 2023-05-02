package com.formation.restaurant.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.restaurant.models.Restaurant;
import com.formation.restaurant.services.RestaurantService;
import com.formation.restaurant.utils.CtrlPrecondition;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restoService; // Injecte le restaurantService

	@GetMapping()
	public List<Restaurant> findAll() {
		return restoService.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurant findById(@PathVariable("id") String identifiant) {
		Restaurant response = restoService.findById(identifiant);
		CtrlPrecondition.checkIsFound(response);
		return response;
	};
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public String create(@RequestBody Restaurant restaurant) {
		return restoService.create(restaurant);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@PathVariable("id") String identifiant, @RequestBody Restaurant restaurant) {
		CtrlPrecondition.checkIsFound(restoService.findById(identifiant));
		restoService.update(identifiant, restaurant);		
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(code= HttpStatus.OK)
	public void partialUpdate(@PathVariable("id") String identifiant, @RequestBody Map<String, Object> updates) {
		CtrlPrecondition.checkIsFound(restoService.findById(identifiant));
		restoService.partialUpdate(identifiant, updates);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable("id") String identifiant) {
		CtrlPrecondition.checkIsFound(restoService.findById(identifiant));
		restoService.deleteById(identifiant);
	}
}
