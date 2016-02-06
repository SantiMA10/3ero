package encuesta.representacion;

import encuesta.Encuesta;

public class LineaProgreso implements Representacion{

	@Override
	public void representar(Encuesta encuesta) {
		System.out.println("Votos:: si=" + encuesta.getVotosSi() + " no=" + encuesta.getVotosNo());
	}

}