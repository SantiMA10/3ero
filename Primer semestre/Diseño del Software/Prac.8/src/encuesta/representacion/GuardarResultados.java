package encuesta.representacion;

import encuesta.Encuesta;

public class GuardarResultados implements Representacion{

	@Override
	public void representar(Encuesta encuesta) {
		System.out.println("Guardando resultados");
	}

}
