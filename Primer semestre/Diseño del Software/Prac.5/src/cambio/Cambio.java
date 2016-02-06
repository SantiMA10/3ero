package cambio;

import editor.Figura;

public interface Cambio {
	
	public void registrarCambio(Figura antes, Figura despues);
	public Figura getAntes();
	public Figura getDespues();

}
