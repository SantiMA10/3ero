package comprobaciones.impl.valido;

import comprobaciones.ComprobacionValido;

public class ComprobacionValidoCualquiera implements ComprobacionValido{

	@Override
	public boolean isValido(String text) {
		return true;
	}

}
