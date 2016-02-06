package herramientas;

import editor.Dibujo;
import figuras.Cuadrado;

public class CuadradoHerramienta implements Herramienta{
	
	private Cuadrado cuadrado;
	private Dibujo dibujo;
	
	public CuadradoHerramienta(Dibujo dibujo) {
		this.dibujo = dibujo;
	}

	@Override
	public void pinchar(int x, int y) {
		if(cuadrado == null)
			this.cuadrado = new Cuadrado();
		
		cuadrado.setX(x);
		cuadrado.setY(y);
	}

	@Override
	public void soltar(int x, int y) {
		
		if(cuadrado != null){
			mover(x, y);
			dibujo.addFigura(cuadrado);
			cuadrado = null;
		}
		
	}

	@Override
	public void mover(int x, int y) {
		
		if(cuadrado != null){
			cuadrado.setAncho(x);
			cuadrado.setAlto(y);
		}
		
	}

}
