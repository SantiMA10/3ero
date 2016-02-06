package platform.plataforma;

import java.awt.Point;

import platform.Image2D;

public interface Plataforma {
	
	Image2D loadImage(String file);
	Point getPosition();
	void drawBall(Image2D image, Point point);

}
