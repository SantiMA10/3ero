package encuesta.representacion.decoradores;

import encuesta.Encuesta;
import encuesta.representacion.Representacion;

public class NumVotosParaMostrar implements Representacion{
	
	private int numVotosParaDibujar;
	private Representacion representacion;
	
	public NumVotosParaMostrar(int numVotosParaDibujar, Representacion representacion) {
		this.numVotosParaDibujar = numVotosParaDibujar;
		this.representacion = representacion;
	}

	@Override
	public void representar(Encuesta encuesta) {
		if((encuesta.getVotosNo() + encuesta.getVotosSi()) >= numVotosParaDibujar){
			representacion.representar(encuesta);
		}
		
	}

}
