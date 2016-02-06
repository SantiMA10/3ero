package figuras;

import herramientas.HerramientaCreacion;

import java.awt.Point;

import editor.*;


public class HerramientaCuadrado extends HerramientaCreacion {

	public HerramientaCuadrado(Editor editor) {
		super(editor);
	}

	protected Figura doCreaFigura(Point inicio, Point fin) {
		return new Cuadrado(inicio, fin);
	}
}
