package comprobaciones.impl.valido;

import comprobaciones.ComprobacionValido;

public class ComprobacionValidoMayor implements ComprobacionValido{
	
	private int mayor;
	
	public ComprobacionValidoMayor(int mayor) {
		this.mayor = mayor;
	}

	@Override
	public boolean isValido(String text) {
		return Integer.parseInt(text) > mayor;
	}



}
