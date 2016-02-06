package main;
import java.io.*;

import editor.*;


public class Main {

	public static void main(String[] args) throws IOException {
		Editor editor = new Editor(new Dibujo());
		KeyboardController controller = new KeyboardController(new InputStreamReader(System.in), editor);
		controller.run();
	}

}
