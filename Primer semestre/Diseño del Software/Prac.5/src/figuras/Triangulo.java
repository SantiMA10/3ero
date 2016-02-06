package figuras;

import java.awt.*;

import editor.*;


public class Triangulo implements Figura {

	public Triangulo(Point v1, Point v2, Point v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public void dibujar() {
		System.out.println("Triangulo: v1 = " + v1 + ", v2 = " + v2 + ", v3 = " + v3);
	}
	
	public String toString(){
		return "Triangulo: v1 = " + v1 + ", v2 = " + v2 + ", v3 = " + v3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		result = prime * result + ((v3 == null) ? 0 : v3.hashCode());
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
		Triangulo other = (Triangulo) obj;
		if (v1 == null) {
			if (other.v1 != null)
				return false;
		} else if (!v1.equals(other.v1))
			return false;
		if (v2 == null) {
			if (other.v2 != null)
				return false;
		} else if (!v2.equals(other.v2))
			return false;
		if (v3 == null) {
			if (other.v3 != null)
				return false;
		} else if (!v3.equals(other.v3))
			return false;
		return true;
	}

	public void mover(int dx, int dy) {
		v1.translate(dx, dy);
		v2.translate(dx, dy);
		v3.translate(dx, dy);
	}

	public boolean contiene(int x, int y) {
		
		Point posicion = new Point(x,y);
		return posicion.equals(v1) || posicion.equals(v2) || posicion.equals(v3);
	}

	private Point v1, v2, v3;

	@Override
	public Figura copiar() {
		return new Triangulo(v1, v2, v3);
	}
}
