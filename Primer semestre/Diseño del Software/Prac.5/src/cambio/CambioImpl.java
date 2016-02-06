package cambio;

import editor.Figura;

public class CambioImpl implements Cambio{
	
	Figura antes, despues;
	
	public CambioImpl(Figura antes, Figura despues) {
		registrarCambio(antes, despues);
	}

	@Override
	public void registrarCambio(Figura antes, Figura despues) {
		if(antes != null)
			this.antes = antes.copiar();
		else
			this.antes = antes;
		if(despues != null)
			this.despues = despues.copiar();
		else
			this.despues = despues;
		
	}

	@Override
	public Figura getAntes() {
		return antes;
	}

	@Override
	public Figura getDespues() {
		return despues;
	}

	@Override
	public String toString() {
		return "CambioImpl [antes=" + antes + ", despues=" + despues + "]";
	}
	
	

}
