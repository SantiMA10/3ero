package figuras;

import java.awt.*;

import editor.*;


import herramientas.*;

public class HerramientaTriangulo implements Herramienta {
	public HerramientaTriangulo(Editor editor) {
		this.editor = editor;
	}

	public void pinchar(int x, int y) {
		vertice[vertices++] = new Point(x,y);
		if (vertices == 3) {
			editor.getDibujo().AddFigura(new Triangulo(vertice[0], vertice[1], vertice[2]));
			vertices = 0;
			editor.finHerramienta();
		}
	}

	public void mover(int x, int y) {	}
	public void soltar(int x, int y) { 	}

	private int vertices = 0;
	private Point[] vertice = new Point[3];
	private Editor editor;
}
