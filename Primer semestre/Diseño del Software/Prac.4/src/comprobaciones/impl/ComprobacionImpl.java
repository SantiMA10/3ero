package comprobaciones.impl;

import comprobaciones.Comprobacion;
import comprobaciones.ComprobacionTipo;
import comprobaciones.ComprobacionValido;

public class ComprobacionImpl implements Comprobacion{
	
	private ComprobacionTipo ct;
	private ComprobacionValido cv;
	
	public ComprobacionImpl(ComprobacionTipo ct, ComprobacionValido cv) {
		this.ct = ct;
		this.cv = cv;
	}

	@Override
	public boolean comprobar(String text) {
		boolean tipo = true;
		
		tipo = true;
		for (char ch : text.toCharArray()) {
			if (!ct.isTipo(ch)) {
				tipo = false;
				break;
			}
		}
		
		return tipo && cv.isValido(text);
	}

}
