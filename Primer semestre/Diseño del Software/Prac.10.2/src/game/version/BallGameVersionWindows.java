package game.version;

import java.awt.Point;

import platform.Image2D;
import platform.windows.WindowsAPI;
import game.BallGame;

public class BallGameVersionWindows extends BallGame{

	private WindowsAPI windows;
	
	public BallGameVersionWindows() {
		windows = new WindowsAPI();
	}

	@Override
	public Image2D loadImage(String file) {
		return windows.loadFile(file);
	}

	@Override
	public Point getPosition() {
		return windows.getMouseClick();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		windows.paint(point.x, point.y, image);
	}

}
