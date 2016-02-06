package editor;

public class Editor {
	
	private Dibujo dibujo;

	public Editor(Dibujo dibujo) {
		setDibujo(dibujo);
	}

	public void setDibujo(Dibujo dibujo) {
		this.dibujo = dibujo;
	}

	public Dibujo getDibujo() {
		return dibujo;
	}

	public void dibujar() {
		dibujo.dibujar();
	}
	
}
