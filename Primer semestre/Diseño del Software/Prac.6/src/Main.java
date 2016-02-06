import views.*;
import mapelemets.MonumentView;
import mapelemets.PhotoView;
import mapelemets.RestaurantView;
import model.*;

public class Main {

	public static void main(String[] args) {

		Database db = new Database();
		MapView map = new MapView();

		// 1. Meter elementos en el mapa
		System.out.println("\n 1. Metiendo elementos en el mapa");

		for (Monument monumento : db.selectMonuments()) {
			map.add(new MonumentView(monumento));
		}

		for (Photo foto : db.selectPhotos()) {
			map.add(new PhotoView(foto));
		}

		for (Restaurant restaurante : db.selectRestaurant()) {
			map.add(new RestaurantView(restaurante));
		}

		// 2. En el mapa se dibujan los marcadores para los elementos a�adidos al mapa
		System.out.println("\n 2. Mostrando mapa");
		map.draw();

		// 3. El usuario presiona brevemente la pantalla para recibir informaci�n de
		// cada elemento
		System.out.println("\n 3. Pulsaci�n breve sobre cada elemento: informaci�n en tooltip");
		map.userTouch(11, 11); // Nombre y autor del monumento (coliseo)
		map.userTouch(21, 21); // Descripci�n de la foto y usuario que la ha subido
		map.userTouch(31, 31); // Nombre y tel�fono del restaurante

		// 4. El usuario deja pulsado un marcador para abrir un elemento
		System.out.println("\n 4. Pulsaci�n larga sobre cada elemento: abrir elemento");
		map.userLongTouch(11, 11); // Navegar hasta el coliseo con el GPS
		map.userLongTouch(21, 21); // Bajarse foto
		map.userLongTouch(31, 31); // Llamar al restaurante
	}

}
