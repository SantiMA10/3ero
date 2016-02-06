package encuesta;

import encuesta.representacion.Representacion;

public class Encuesta {

	private int si, no;
	private String pregunta;
	private Representacion[] representaciones;
	
	public Encuesta(String pregunta, Representacion... representaciones) {
		this.pregunta = pregunta;
		this.representaciones = representaciones;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void incrementaSi() {
		si++;
		representarEncuesta();
	}

	public void incrementaNo() {
		no++;
		representarEncuesta();
	}

	public int getVotosSi() {
		return si;
	}

	public int getVotosNo() {
		return no;
	}

	private void representarEncuesta(){
		for(Representacion representacion : representaciones){
			representacion.representar(this);
		}
	}


}
