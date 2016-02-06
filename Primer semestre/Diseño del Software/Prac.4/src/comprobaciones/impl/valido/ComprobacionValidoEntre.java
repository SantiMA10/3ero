package comprobaciones.impl.valido;

import comprobaciones.ComprobacionValido;

public class ComprobacionValidoEntre implements ComprobacionValido{
	
	private int mayor, menor;
	
	public ComprobacionValidoEntre(int menor, int mayor) {
		this.mayor = mayor;
		this.menor = menor;
	}

	@Override
	public boolean isValido(String text) {
		return Integer.parseInt(text) > menor && Integer.parseInt(text) < mayor;
	}

}
