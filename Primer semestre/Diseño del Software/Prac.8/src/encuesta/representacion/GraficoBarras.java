package encuesta.representacion;

import encuesta.Encuesta;

public class GraficoBarras implements Representacion{

	@Override
	public void representar(Encuesta encuesta) {
		System.out.println("Dibujando gráfico de barras");
	}

}
