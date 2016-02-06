package game;

import java.awt.Point;

import platform.Image2D;

/* Esta clase/paquete sería el código del videojuego, el cual se quiere reutilizar
 * en las distintas plataformas 
 */

public abstract class BallGame {

	public void play() {

		Image2D image = loadImage("Bola.jpg");

		// Lógica principal del juego
		for (int i = 0; i < 10; i++) {
			Point point = getPosition();
			drawBall(image, point);
		}
	}

	protected abstract Image2D loadImage(String file);
	protected abstract Point getPosition();
	protected abstract void drawBall(Image2D image, Point point);

}
