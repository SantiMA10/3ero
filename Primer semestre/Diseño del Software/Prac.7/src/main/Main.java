package main;

import java.io.*;

import decoradores.*;
import outputs.*;

public class Main {

	public static void main(String[] args) throws IOException {

		FileSystem system = new FileSystem();

		system.copyFile("privado.txt", new ContadorCaracteres("Al principio", new Encriptar(
				new Normalizar(new ContadorCaracteres("Al final", new FileOutput("b.txt"))))));
		
		system.copyFile("privado.txt", new Normalizar(new FileOutput("a.txt")));
		
		system.copyFile("privado.txt", new Encriptar(new Internet("1.1.1.1")));
		
		system.copyFile("privado.txt", new Encriptar(new Normalizar(new Internet("1.1.1.1"))));
		
		system.copyFile("privado.txt",  new ContadorCaracteres("Al principio",
				new Encriptar(new EliminarEspaciosRepetidos( new ContadorCaracteres("Al final", new Bluetooth("iPhone"))))));
		
	}

}
