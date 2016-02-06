package figuras;

import java.awt.Point;

public class Circulo implements Figura{
	
	private int inicioX, inicioY, radio;
	private Point centro;

	@Override
	public boolean estaPulsada(int x, int y) {
		double distancia = Math.sqrt(Math.pow(x - centro.x, 2) + Math.pow(y - centro.y, 2));
		return distancia < radio;
	}

	public int getInicioX() {
		return inicioX;
	}

	public void setInicioX(int inicioX) {
		this.inicioX = inicioX;
	}

	public int getInicioY() {
		return inicioY;
	}

	public void setInicioY(int inicioY) {
		this.inicioY = inicioY;
	}
	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio){
		this.radio = radio;
	}
	
	private void updateRadio(int y) {
		this.radio = y - centro.y;
	}

	public Point getCentro() {
		return centro;
	}

	public void setCentro(int x, int y) {
		int centroX = (this.getInicioX() + x)/2;
		int centroY = (this.getInicioY() + y)/2;
		
		this.centro = new Point(centroX, centroY);
		this.updateRadio(y);
	}
	
	public String toString(){
		return "Circulo: centro = " + this.centro.toString() + ", radio = " + radio + "\n"; 
	}

	@Override
	public void mover(int inicioX, int inicioY, int x, int y) {
		int movimientoX = centro.x - inicioX;
		int movimientoY = centro.y - inicioY;
		
		this.centro = new Point(x + movimientoX, y + movimientoY);
		
	}

}
