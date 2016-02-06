package mapelemets;

import model.Monument;
import components.Coordinates;
import components.GPS;
import views.MapElement;

public class MonumentView implements MapElement{
	
	Monument monument;
	
	public MonumentView(Monument monument) {
		this.monument = monument;
	}

	@Override
	public String getName() {
		return monument.getName();
	}

	@Override
	public Coordinates getCoordinates() {
		return new GPS().getCoordinates(monument.getAddress());
	}

	@Override
	public String getTooltipInfo() {
		return "Nombre: " + monument.getName() + ", autor: " + monument.getAuthor();
	}

	@Override
	public void open() {
		new GPS().navigate(monument.getAddress());
	}

}
