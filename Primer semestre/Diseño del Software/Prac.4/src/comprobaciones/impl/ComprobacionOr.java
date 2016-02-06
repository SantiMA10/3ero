package comprobaciones.impl;

import comprobaciones.Comprobacion;

public class ComprobacionOr  implements Comprobacion{
	
	private Comprobacion[] comprobaciones;
	
	public ComprobacionOr(Comprobacion... comprobacion) {
		this.comprobaciones = comprobacion;
	}

	@Override
	public boolean comprobar(String text) {
		boolean check = false;
		for(Comprobacion comprobacion : comprobaciones){
			check = check || comprobacion.comprobar(text);
		}
		return check;
	}

}
