package mapelemets;

import model.Restaurant;
import components.Coordinates;
import components.GPS;
import views.MapElement;

public class RestaurantView implements MapElement{
	
	Restaurant restaurant;
	
	public RestaurantView(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String getName() {
		return restaurant.getName();
	}

	@Override
	public Coordinates getCoordinates() {
		return new GPS().getCoordinates(restaurant.getAddress());
	}

	@Override
	public String getTooltipInfo() {
		return "Nombre: " + restaurant.getName() + ", teléfono: " + restaurant.getPhone();
	}

	@Override
	public void open() {
		restaurant.call();
	}

}
