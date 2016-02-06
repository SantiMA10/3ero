package platform.windows;

import java.awt.Point;

import platform.*;


public class WindowsAPI {

	public Image2D loadFile(String name) {
		System.out.println("(Windows) Cargada imagen '" + name + "' del disco duro.");
		return new Image2D(name, 10, 10);
	}

	public void paint(int x, int y, Image2D image) {
		System.out.println("(Windows) Dibujando '" + image.getName() + "' en [" + x + "," + y + "]");
	}

	// Posición en la que el usuario pincha con el ratón 
	public Point getMouseClick() {
		point.translate(10, 10);
		return new Point(point);
	}

	private Point point = new Point(0, 0);

}
