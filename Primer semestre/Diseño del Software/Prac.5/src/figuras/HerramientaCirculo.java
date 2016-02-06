package figuras;

import herramientas.HerramientaCreacion;

import java.awt.Point;

import editor.*;


public class HerramientaCirculo extends HerramientaCreacion {

	public HerramientaCirculo(Editor editor) {
		super(editor);
	}

	protected Figura doCreaFigura(Point inicio, Point fin) {
		Point centro = new Point((inicio.x + fin.x) / 2, (inicio.y + fin.y) / 2);
		int radio = Math.max(fin.x - inicio.x, fin.y - inicio.y) / 2;
		return new Circulo(centro, radio);
	}
}
