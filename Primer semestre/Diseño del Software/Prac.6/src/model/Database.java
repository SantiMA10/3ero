package model;

import java.util.*;

import components.Coordinates;

public class Database {

	public Collection<Monument> selectMonuments() {
		List<Monument> monumentos = new ArrayList<Monument>();
		monumentos.add(new Monument("Coliseo", "Vespasiano", "Avenida del Coliseo 1. Roma"));
		monumentos.add(new Monument("Fontana di Trevi", "Niccolo Salvi", "Piazza di Trevi. Roma"));
		return monumentos;
	}

	public Collection<Photo> selectPhotos() {
		List<Photo> fotos = new ArrayList<Photo>();
		fotos.add(new Photo("El Coliseo de noche", "Raúl", new Coordinates(20, 20)));
		fotos.add(new Photo("Un perro mordiendo a un turista", "Raúl", new Coordinates(40, 40)));
		return fotos;
	}

	public Collection<Restaurant> selectRestaurant() {
		List<Restaurant> restaurantes = new ArrayList<Restaurant>();
		restaurantes.add(new Restaurant("El Gladiador", "Calle del Triunfo 2. Roma", "555 123 456"));
		restaurantes.add(new Restaurant("Mario", "Via della Dataria . Roma", "555 123 457"));
		return restaurantes;
	}
}
