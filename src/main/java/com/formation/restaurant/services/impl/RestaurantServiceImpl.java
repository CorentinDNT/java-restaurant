package com.formation.restaurant.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.restaurant.dao.RestaurantRepository;
import com.formation.restaurant.models.Restaurant;
import com.formation.restaurant.services.RestaurantService;


/*Signifie que c'est un composant injectable*/
@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restoRepository; 

	@Override
	public List<Restaurant> findAll() {
		List<Restaurant> liste = new ArrayList<>();
		restoRepository.findAll().forEach(liste::add);// ajoute a notyre liste, l'élément courant
		//pour chaque élément de la methode findall on ajoute l'élément
		return liste;
	}

	@Override
	public Restaurant findById(String id) {
		if(restoRepository.findById(id).isPresent()) {
			return restoRepository.findById(id).get();
		} 
		return null;
	}
	
	public String create(Restaurant restaurant) {
		return restoRepository.save(restaurant).getId();
	}

	@Override
	public void update(String identifiant, Restaurant restaurant) {
		restaurant.setId(identifiant);
		restoRepository.save(restaurant);
	}

	@Override
	public void partialUpdate(String identifiant, Map<String, Object> updates) {
		Restaurant restoToUpdate = restoRepository.findById(identifiant).get();
		for (String key : updates.keySet()) {
			switch (key) {
			case "nom": {
				restoToUpdate.setNom((String) updates.get(key));
				break;
			}
			case "adress": {
				restoToUpdate.setNom((String) updates.get(key));
				break;
			}
			}
		}
		restoRepository.save(restoToUpdate);
	}

	@Override
	public void deleteById(String identifiant) {
		restoRepository.deleteById(identifiant);
	}
	

}
