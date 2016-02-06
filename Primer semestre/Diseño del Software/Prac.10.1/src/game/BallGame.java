package game;

import java.awt.Point;

import platform.Image2D;
import platform.plataforma.*;

/* Esta clase/paquete sería el código del videojuego, el cual se quiere reutilizar
 * en las distintas plataformas 
 */

//Adapter
public class BallGame {

	//private Plataforma plataforma = new PlataformaAndroid();
	//private Plataforma plataforma = new PlataformaPlayStation();
	private Plataforma plataforma = new PlataformaWindows();

	public void play() {

		Image2D image = plataforma.loadImage("Bola.jpg");

		// Lógica principal del juego
		for (int i = 0; i < 10; i++) {
			Point point = plataforma.getPosition();
			plataforma.drawBall(image, point);
		}
	}
	
}
