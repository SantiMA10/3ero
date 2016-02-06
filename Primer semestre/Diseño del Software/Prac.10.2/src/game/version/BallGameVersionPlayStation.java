package game.version;

import game.BallGame;

import java.awt.Point;

import platform.Image2D;
import platform.playstation.Playstation5API;

public class BallGameVersionPlayStation extends BallGame{
	
	private Playstation5API playstation;
	
	public BallGameVersionPlayStation() {
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
