package herramientas;

import java.awt.Point;

import editor.Dibujo;
import figuras.Triangulo;

public class TrianguloHerramienta implements Herramienta{
	
	private Dibujo dibujo;
	private Triangulo triangulo;
	
	public TrianguloHerramienta(Dibujo dibujo){
		this.dibujo = dibujo;
	}

	@Override
	public void pinchar(int x, int y) {
		if(triangulo == null)
			triangulo = new Triangulo();
		
		if(triangulo.getV1() == null){
			triangulo.setV1(new Point(x,y));
		}
		else if(triangulo.getV2() == null){
			triangulo.setV2(new Point(x,y));
		}
		else if(triangulo.getV3() == null){
			triangulo.setV3(new Point(x,y));
			dibujo.addFigura(triangulo);
			triangulo = null;
		}
	}

	@Override
	public void soltar(int x, int y) {		
	}

	@Override
	public void mover(int x, int y) {		
	}

}
