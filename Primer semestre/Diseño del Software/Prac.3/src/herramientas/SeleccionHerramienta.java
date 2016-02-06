package herramientas;

import java.util.List;

import editor.Dibujo;
import figuras.Figura;

public class SeleccionHerramienta implements Herramienta{
	
	private Dibujo dibujo;
	private Figura figura;
	int inicioX, inicioY;
	
	public SeleccionHerramienta(Dibujo dibujo){
		this.dibujo = dibujo;
	}

	@Override
	public void pinchar(int x, int y) {
		List<Figura> figuras = dibujo.getFiguras();
		
		for(int i = figuras.size() - 1; i >= 0; i--){
			if(figura == null && figuras.get(i).estaPulsada(x, y)){
				figura = figuras.get(i);
				inicioX = x;
				inicioY = y;
			}
		}
		
	}

	@Override
	public void soltar(int x, int y) {
		mover(x,y);
		figura = null;
	}

	@Override
	public void mover(int x, int y) {
		if(figura != null){
			figura.mover(inicioX, inicioY, x, y);
		}
	}

}
