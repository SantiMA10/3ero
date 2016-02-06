package main;

import herramientas.*;

import java.io.*;

import editor.*;
import figuras.*;


public class KeyboardController {

	public KeyboardController(Reader input, Editor editor) {
		in = new BufferedReader(input);
		this.editor = editor;
		creaHerramientas();
	}

	private void creaHerramientas() {
		circulo = new HerramientaCirculo(editor);
		cuadrado = new HerramientaCuadrado(editor);
		triangulo = new HerramientaTriangulo(editor);
	}

	public void run() throws IOException {
		System.out.println("Comandos Herramientas: cuadrado, circulo, triangulo, seleccion");
		System.out.println("Comandos Ratón: pinchar x,y / mover x,y / soltar x,y");
		System.out.println("Otros Comandos: dibujar, exit");
		do {
			System.out.print(">");
			String[] line = in.readLine().split("[ ,]");
			if (line[0].equals("exit"))
				return;
			if (line[0].equals("cuadrado"))
				editor.setHerramienta(cuadrado);
			else if (line[0].equals("circulo"))
				editor.setHerramienta(circulo);
			else if (line[0].equals("triangulo"))
				editor.setHerramienta(triangulo);
			else if (line[0].equals("seleccion"))
				editor.setHerramienta(editor.getDefaultTool());
			else if (line[0].equals("pinchar"))
				editor.pinchar(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			else if (line[0].equals("mover"))
				editor.mover(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			else if (line[0].equals("soltar"))
				editor.soltar(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			else if (line[0].equals("dibujar"))
				editor.dibujar();
			else if (line[0].equals("undo"))
				editor.getDibujo().getHistorial().undo(editor.getDibujo());
			else if (line[0].equals("redo"))
				editor.getDibujo().getHistorial().redo(editor.getDibujo());
			else
				System.out.println("Comando no válido");

		} while (true);
	}

	private Editor editor;
	private BufferedReader in;

	private Herramienta circulo, cuadrado, triangulo;
}
