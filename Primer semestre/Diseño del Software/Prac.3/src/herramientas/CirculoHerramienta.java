package herramientas;

import editor.Dibujo;
import figuras.Circulo;

public class CirculoHerramienta implements Herramienta{
	
	private Circulo circulo;
	private Dibujo dibujo;
	
	public CirculoHerramienta(Dibujo dibujo) {
		this.dibujo = dibujo;
	}

	@Override
	public void pinchar(int x, int y) {
		if(circulo == null)
			this.circulo = new Circulo();
		
		this.circulo.setInicioX(x);
		this.circulo.setInicioY(y);
		
	}

	@Override
	public void soltar(int x, int y) {
		
		if(circulo != null){
			mover(x, y);
			dibujo.addFigura(circulo);
			circulo = null;
		}
		
	}

	@Override
	public void mover(int x, int y) {
		if(circulo != null)
			this.circulo.setCentro(x, y);
		
	}


}
