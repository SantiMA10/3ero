package model;

import components.*;

public class Photo{

	private String description;
	private String user; // Usuario que ha subido la foto
	private Coordinates coordinates;	// Coordenadas de la foto

	public Photo(String description, String user, Coordinates coordinates) {
		this.description = description;
		this.user = user;
		this.coordinates = coordinates;
	}

	public String getDescription() {
		return description;
	}

	public String getUser() {
		return user;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	// Muestra la foto
	public void show() {
		System.out.println("Bajando foto: " + description);
	}

}
