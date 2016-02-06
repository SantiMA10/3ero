package editor;

import java.util.*;
import figuras.Figura;

public class Dibujo {
	
	List<Figura> figuras;
	
	public Dibujo(){
		figuras = new ArrayList<Figura>();
	}
	
	public void addFigura(Figura figura){
		figuras.add(figura);
	}

	public void dibujar() {
		for(Figura figura: figuras){
			System.out.print(figura.toString());
		}
	}
	
	public List<Figura> getFiguras(){
		return figuras;
	}

}
