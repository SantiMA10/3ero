import java.io.*;
import java.util.*;

public class Main {

	private static List<Instruccion> instrucciones = new ArrayList<Instruccion>();
	private static int ip = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader fichero = new BufferedReader(new FileReader("factorial.txt"));

		String linea;
		while ((linea = fichero.readLine()) != null)
			cargaInstruccion(linea);
		fichero.close();

		ejecutaPrograma();
	}

	private static void ejecutaPrograma() {
		while (ip < instrucciones.size()) {
			
			ip = instrucciones.get(ip).ejecutar(ip);
			
		}
	}

	private static void cargaInstruccion(String linea) {
		if (linea.trim().length() == 0)
			return;

		String[] palabras = linea.split(" ");
		
		if (palabras[0].equals("push")) {
			instrucciones.add(new Push(Integer.parseInt(palabras[1])));
		} else if (palabras[0].equals("add")) {
			instrucciones.add(new Add());
		} else if (palabras[0].equals("sub")) {
			instrucciones.add(new Sub());
		} else if (palabras[0].equals("mul")) {
			instrucciones.add(new Mul());
		} else if (palabras[0].equals("jmp")) {
			instrucciones.add(new Jmp(Integer.parseInt(palabras[1])));
		} else if (palabras[0].equals("jmpg")) {
			instrucciones.add(new Jmpg(Integer.parseInt(palabras[1])));
		} else if (palabras[0].equals("load")) {
			instrucciones.add(new Load());
		} else if (palabras[0].equals("store")) {
			instrucciones.add(new Store());
		} else if (palabras[0].equals("input")) {
			instrucciones.add(new Input());
		} else if (palabras[0].equals("output")) {	
			instrucciones.add(new Output());
		}
		
	}
/*
	private static void push(int valor) {
		pila[sp] = valor;
		sp++;
	}

	private static int pop() {
		sp--;
		return pila[sp];
	}
*/
	
}
