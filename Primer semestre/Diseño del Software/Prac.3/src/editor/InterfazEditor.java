package editor;

import herramientas.CirculoHerramienta;
import herramientas.CuadradoHerramienta;
import herramientas.Herramienta;
import herramientas.SeleccionHerramienta;
import herramientas.TrianguloHerramienta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterfazEditor {
	
	private BufferedReader in;
	
	public void run() throws IOException{
		
		in = new BufferedReader(new InputStreamReader(System.in));
		Editor editor = new Editor(new Dibujo());
		Herramienta herramienta = null;

		System.out.println("Comandos Herramientas: cuadrado, circulo, triangulo, seleccion");
		System.out.println("Comandos Raton: pinchar x,y / mover x,y / soltar x,y");
		System.out.println("Otros Comandos: dibujar, exit");

		do {
			System.out.print("> ");
			String[] line = in.readLine().split("[ ,]");

			if (line[0].equals("exit"))
				return;
			if (line[0].equals("cuadrado"))
				herramienta = new CuadradoHerramienta(editor.getDibujo()); //	editor.xxx
			else if (line[0].equals("circulo"))
				herramienta = new CirculoHerramienta(editor.getDibujo());
			else if (line[0].equals("triangulo"))
				herramienta = new TrianguloHerramienta(editor.getDibujo());
			else if (line[0].equals("seleccion"))
				herramienta = new SeleccionHerramienta(editor.getDibujo());
			else if (line[0].equals("pinchar")) {
				int x = Integer.parseInt(line[1]);
				int y = Integer.parseInt(line[2]);
				herramienta.pinchar(x, y);
			} else if (line[0].equals("mover")) {
				int x = Integer.parseInt(line[1]);
				int y = Integer.parseInt(line[2]);
				herramienta.mover(x, y);
			} else if (line[0].equals("soltar")) {
				int x = Integer.parseInt(line[1]);
				int y = Integer.parseInt(line[2]);
				herramienta.soltar(x, y);
			} else if (line[0].equals("dibujar"))
				editor.dibujar();
			else
				System.out.println("Comando no valido");

		} while (true);
		
	}
	
}
