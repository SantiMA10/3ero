package platform.plataforma;

import java.awt.Point;

import platform.Image2D;
import platform.android.AndroidAPI;

public class PlataformaAndroid implements Plataforma {
	
	private AndroidAPI android;
	
	public PlataformaAndroid() {
		android = new AndroidAPI();
	}

	@Override
	public Image2D loadImage(String file) {
		return android.loadResource(file);
	}

	@Override
	public Point getPosition() {
		return android.getTouch();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		android.draw(point.x, point.y, image);
	}

}
