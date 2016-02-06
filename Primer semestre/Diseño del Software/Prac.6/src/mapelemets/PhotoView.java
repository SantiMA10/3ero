package mapelemets;

import model.Photo;
import components.Coordinates;
import views.MapElement;

public class PhotoView implements MapElement{
	
	Photo photo;
	
	public PhotoView(Photo photo) {
		this.photo = photo;
	}

	@Override
	public String getName() {
		return photo.getDescription();
	}

	@Override
	public Coordinates getCoordinates() {
		return photo.getCoordinates();
	}

	@Override
	public String getTooltipInfo() {
		return photo.getDescription() + " por: " + photo.getUser();
	}

	@Override
	public void open() {
		photo.show();
	}

}
