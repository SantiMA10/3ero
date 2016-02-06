package platform.android;

import java.awt.Point;

import platform.*;

public class AndroidAPI {

	public Image2D loadResource(String name) {
		System.out.println("(Android) Cargada imagen '" + name + "' de la memoria flash.");
		return new Image2D(name, 10, 10);
	}

	public void draw(int x, int y, Image2D image) {
		System.out.println("(Android) Dibujando '" + image.getName() + "' en [" + x + "," + y + "]");
	}

	// Posición en la que el usuario toca con el dedo en la pantalla
	public Point getTouch() {
		point.translate(10, 10);
		return new Point(point);
	}

	private Point point = new Point(0, 0);
}
