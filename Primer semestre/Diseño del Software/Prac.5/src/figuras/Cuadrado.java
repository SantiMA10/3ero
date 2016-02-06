package figuras;
import java.awt.Point;

import editor.*;


public class Cuadrado implements Figura {

	public Cuadrado(Point esquina, int ancho, int alto) {
		this.esquina = new Point(esquina);
		this.ancho = ancho;
		this.alto = alto;
	}

	public Cuadrado(Point inicio, Point fin) {
		this(inicio, fin.x - inicio.x, fin.y - inicio.y);
	}

	public void dibujar() {
		System.out.println("Cuadrado: x = " + esquina.x + ", y = " + esquina.y + ", ancho = " + ancho + ", alto = " + alto);
	}
	
	public String toString(){
		return "Cuadrado: x = " + esquina.x + ", y = " + esquina.y + ", ancho = " + ancho + ", alto = " + alto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alto;
		result = prime * result + ancho;
		result = prime * result + ((esquina == null) ? 0 : esquina.hashCode());
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
		Cuadrado other = (Cuadrado) obj;
		if (alto != other.alto)
			return false;
		if (ancho != other.ancho)
			return false;
		if (esquina == null) {
			if (other.esquina != null)
				return false;
		} else if (!esquina.equals(other.esquina))
			return false;
		return true;
	}

	public void mover(int dx, int dy) {
		esquina.translate(dx, dy);
	}

	public boolean contiene(int x, int y) {
		return (esquina.x <= x && x <= esquina.x + ancho) && (esquina.y <= y && y <= esquina.y + alto);
	}

	private Point esquina;
	private int ancho;
	private int alto;
	
	@Override
	public Figura copiar() {
		return new Cuadrado(esquina, ancho, alto);
	}
}
