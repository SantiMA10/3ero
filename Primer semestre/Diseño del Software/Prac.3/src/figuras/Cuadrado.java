package figuras;

public class Cuadrado implements Figura{

	int x, y, ancho, alto;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int x) {
		this.ancho = x - this.x;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int y) {
		this.alto = y - this.y;
	}
	
	@Override
	public String toString(){
		return "Cuadrado: x = " + x + ", y = " + y + ", alto = " + alto + ", ancho = " + ancho +"\n";
	}

	@Override
	public boolean estaPulsada(int x, int y) {
		return (this.x <= x && x <= this.x + ancho) && (this.y <= y && y <= this.y + alto);
	}

	@Override
	public void mover(int inicioX, int inicioY, int x, int y) {
		int movimientoX = this.x - inicioX;
		int movimientoY = this.y - inicioY;
		
		this.x = x + movimientoX;
		this.y = y + movimientoY;
		
	}
	

}
