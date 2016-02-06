package editor;
import java.util.*;

public class Dibujo {
	public void AddFigura(Figura figura) {
		figuras.add(figura);
	}
	
	public void dibuja() {
		for (Figura figura : figuras)
			figura.dibujar();
	}

	public Figura getFigura(int x, int y) {
		for (Figura figura : figuras)
			if (figura.contiene(x, y))
				return figura;
		return null;
	}
	
	List<Figura> figuras = new ArrayList<Figura>();
	private Historial historial = new Historial();
	
	public Historial getHistorial(){
		return historial;
	}

	public void cambiarFigura(Figura despues, Figura antes) {
		if(antes != null && despues != null)
			for(int i = 0; i < figuras.size(); i++){
				if(figuras.get(i).equals(despues)){
					figuras.add(i, antes);
					break;
				}
			}
		if(despues == null && antes != null)
			figuras.add(antes);
		else
			for(int i = 0; i < figuras.size(); i++){
				//System.out.println(figuras.get(i).equals(despues));
				//System.out.println(figuras.get(i) + "\n" + despues);
				if(figuras.get(i).equals(despues)){
					figuras.remove(i);
					break;
				}
			}
		
	}
}
