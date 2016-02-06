package herramientas;

import java.awt.Point;

import cambio.CambioImpl;
import editor.*;


public abstract class HerramientaCreacion implements Herramienta {

	public HerramientaCreacion(Editor editor) {
		this.editor = editor;
	}

	public void pinchar(int x, int y) {
		inicio = new Point(x, y);
	}

	public void mover(int x, int y) {
	}

	public void soltar(int x, int y) {
		fin = new Point(x, y);
		Figura figura = doCreaFigura(inicio, fin);
		editor.getDibujo().getHistorial().registrarCambio(new CambioImpl(null, figura));
		editor.getDibujo().AddFigura(figura);
		editor.finHerramienta();
	}

	protected abstract Figura doCreaFigura(Point inicio, Point fin);

	protected Editor editor;
	private Point inicio, fin;
}