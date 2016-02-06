package platform.plataforma;

import java.awt.Point;

import platform.Image2D;
import platform.playstation.Playstation5API;

public class PlataformaPlayStation implements Plataforma{
	
	private Playstation5API playstation;
	
	public PlataformaPlayStation() {
		playstation = new Playstation5API();
	}

	@Override
	public Image2D loadImage(String file) {
		return playstation.loadGraphics(file);
	}

	@Override
	public Point getPosition() {
		return playstation.getJoystick();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		playstation.render(point.x, point.y, image);
	}

}
