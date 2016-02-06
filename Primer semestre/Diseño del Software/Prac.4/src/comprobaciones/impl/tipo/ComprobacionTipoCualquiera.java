package comprobaciones.impl.tipo;

import comprobaciones.ComprobacionTipo;

public class ComprobacionTipoCualquiera implements ComprobacionTipo{

	@Override
	public boolean isTipo(char ch) {
		return true;
	}

}