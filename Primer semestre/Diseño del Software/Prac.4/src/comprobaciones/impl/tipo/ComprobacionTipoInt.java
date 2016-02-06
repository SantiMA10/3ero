package comprobaciones.impl.tipo;

import comprobaciones.ComprobacionTipo;

public class ComprobacionTipoInt implements ComprobacionTipo{

	@Override
	public boolean isTipo(char ch) {
		return Character.isDigit(ch);
	}

}
