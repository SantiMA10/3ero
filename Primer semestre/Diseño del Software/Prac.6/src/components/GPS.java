package components;

public class GPS {

	public void navigate(String address) {
		System.out.println("GPS: Gire a la derecha. Ha llegado a '" + address + "'");
	}

	public Coordinates getCoordinates(String address) {
		// Cálculo de relleno. 
		// Devuelve la posición en función de la primera letra
		double number = (address.toLowerCase().charAt(0) - 'a') * 10 + 10;
		return new Coordinates(number, number);
	}

	public String getAddress(Coordinates coordinates) {
		return coordinates.toString();
	}
}
