import java.io.*;

import encuesta.*;
import encuesta.representacion.GraficoBarras;
import encuesta.representacion.GraficoCircular;
import encuesta.representacion.GuardarResultados;
import encuesta.representacion.LineaProgreso;
import encuesta.representacion.decoradores.NumVotosParaMostrar;

public class Main {

	public static void main(String[] args) throws IOException {
		Encuesta encuesta = new Encuesta("¿Está a favor de la energia nuclear?", 
				new LineaProgreso(), new NumVotosParaMostrar(4, new GraficoBarras()),
				new NumVotosParaMostrar(3, new GraficoCircular()), new GuardarResultados());

		Encuestador encuestador = new Encuestador();
		encuestador.rellena(encuesta);
	}

}

