package comprobaciones.impl.tipo;

import comprobaciones.ComprobacionTipo;

public class ComprobacionTipoString implements ComprobacionTipo{

	@Override
	public boolean isTipo(char ch) {
		return Character.isLetter(ch);
	}

}
