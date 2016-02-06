package comprobaciones.impl.valido;

import comprobaciones.ComprobacionValido;

public class ComprobacionValidoLongitud implements ComprobacionValido{
	
	private int longitud;
	
	public ComprobacionValidoLongitud(int longitud) {
		this.longitud = longitud;
	}

	@Override
	public boolean isValido(String text) {
		return text.length() == longitud;
	}

}
