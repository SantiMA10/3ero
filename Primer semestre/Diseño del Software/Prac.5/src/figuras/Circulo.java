package figuras;
import java.awt.Point;

import editor.*;


public class Circulo implements Figura {

	public Circulo(Point centro, int radio) {
		this.centro = centro;
		this.radio = radio;
	}

	public void dibujar() {
		System.out.println("C�rculo: centro = " + centro + ", radio = " + radio);
	}
	
	public String toString(){
		return "C�rculo: centro = " + centro + ", radio = " + radio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime * result + radio;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circulo other = (Circulo) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (radio != other.radio)
			return false;
		return true;
	}

	public void mover(int dx, int dy) {
		centro.translate(dx, dy);
	}

	public boolean contiene(int x, int y) {
		double distancia = Math.sqrt(Math.pow(x - centro.x, 2) + Math.pow(y - centro.y, 2));
		return distancia < radio;
	}
	

	private Point centro;
	private int radio;
	
	@Override
	public Figura copiar() {
		return new Circulo(centro, radio);
	}
}
