package recorrerarboles;

import java.util.*;
import nodos.*;

public class Main {

	public static void main(String[] args) {
		/*
		 * read ancho; 
		 * read alto; 
		 * area = alto * ancho / 2; 
		 * print area + 10;
		 */

		List<Sentencia> sentencias = new ArrayList<Sentencia>();

		// read ancho;
		sentencias.add(new Read(new Variable("ancho")));

		// read alto;
		sentencias.add(new Read(new Variable("alto")));

		// area = alto * ancho / 2;
		Producto prod = new Producto(new Variable("alto"), new Variable("ancho"));
		sentencias.add(new Asignacion(new Variable("area"), new Division(prod, new ConstanteInt("2"))));

		// print area + 10;
		sentencias.add(new Print(new Suma(new Variable("area"), new ConstanteInt("10"))));

		Programa prog = new Programa(sentencias);

		System.out.println("\n--- Recorrido Recursivo: todo en un método");
		RecorridoRecursivo rr = new RecorridoRecursivo();
		rr.visit(prog);

		// No compila
		System.out.println("\n--- Recorrido ideal: no compila");
		// VersionIdeal ideal = new VersionIdeal();
		// ideal.visit(prog);
		
		// Que funcione lo siguiente:
		System.out.println("\n--- Recorrido con Visitor: hecho!");
		PrintVisitor print = new PrintVisitor();
		prog.accept(print, null);
		
		System.out.println("\n--- Recorrido con Visitor ejecutando: hecho!");
		ExecuteVisitor execute = new ExecuteVisitor();
		prog.accept(execute, null);


	}
}
