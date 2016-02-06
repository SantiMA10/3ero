package comprobaciones.impl;

import comprobaciones.Comprobacion;

public class ComprobacionAnd implements Comprobacion{
	
	private Comprobacion[] comprobaciones;
	
	public ComprobacionAnd(Comprobacion... comprobacion) {
		this.comprobaciones = comprobacion;
	}

	@Override
	public boolean comprobar(String text) {
		boolean check = true;
		for(Comprobacion comprobacion : comprobaciones){
			check = check && comprobacion.comprobar(text);
		}
		return check;
	}

}
