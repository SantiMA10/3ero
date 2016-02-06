package figuras;

import java.awt.Point;

public class Triangulo implements Figura{
	
	private Point v1, v2, v3;

	public Point getV1() {
		return v1;
	}

	public void setV1(Point v1) {
		this.v1 = v1;
	}

	public Point getV2() {
		return v2;
	}

	public void setV2(Point v2) {
		this.v2 = v2;
	}

	public Point getV3() {
		return v3;
	}

	public void setV3(Point v3) {
		this.v3 = v3;
	}

	@Override
	public boolean estaPulsada(int x, int y) {
		Point posicion = new Point(x,y);
		return posicion.equals(v1) || posicion.equals(v2) || posicion.equals(v3);
	}
	
	public String toString(){
		return "Triangulo: v1 = " + v1 + ", v2 = " + v2 + ", v3 = " + v3 + "\n";
	}

	@Override
	public void mover(int inicioX, int inicioY, int x, int y) {
		int movimientoX = x - inicioX;
		int movimientoY = y - inicioY;
		
		v1 = new Point(v1.x + movimientoX, v1.y + movimientoY);
		v2 = new Point(v2.x + movimientoX, v2.y + movimientoY);
		v3 = new Point(v3.x + movimientoX, v3.y + movimientoY);
		
	}

}
