package comprobaciones.impl.valido;

import comprobaciones.ComprobacionValido;

public class ComprobacionValidoPredefinido implements ComprobacionValido{
	
	private String[] opciones;
	
	public ComprobacionValidoPredefinido(String... opcion) {
		this.opciones = opcion;
	}

	@Override
	public boolean isValido(String text) {
		
		for(String opcion : opciones){
			if(opcion.equals(text)){
				return true;
			}
		}
		
		return false;
	}

}
