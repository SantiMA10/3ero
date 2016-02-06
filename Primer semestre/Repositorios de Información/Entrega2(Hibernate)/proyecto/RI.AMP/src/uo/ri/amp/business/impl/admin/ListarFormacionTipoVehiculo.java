package uo.ri.amp.business.impl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.PorcentajeTipo;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.TipoVehiculoFinder;

public class ListarFormacionTipoVehiculo implements Comand {

	@Override
	public Object execute() throws BusinessException {

		/*
		 * Recorro la lista de tipos y guardo los datos de cada mecanico en el
		 * map
		 */
		List<TipoVehiculo> tipos = TipoVehiculoFinder.listar();
		Map<String, Map<Mecanico, Double>> datos = new HashMap<String, Map<Mecanico, Double>>();

		for (TipoVehiculo tipo : tipos) {
			List<PorcentajeTipo> porcentajeTipos = tipo.getPorcentajeTipo();

			Map<Mecanico, Double> datosMecanicos = new HashMap<Mecanico, Double>();
			for (PorcentajeTipo porcentajeTipo : porcentajeTipos) {
				int numHoras = porcentajeTipo.getCurso().getNumHoras();

				Set<Asistencia> asistencias = porcentajeTipo.getCurso()
						.getAsistencia();
				for (Asistencia asistencia : asistencias) {

					double horas = (asistencia.getAsistencia() / 100.0)
							* (porcentajeTipo.getPorcentaje() / 100.0)
							* numHoras;

					if (datosMecanicos.containsKey(asistencia.getMecanico())) {
						horas += datosMecanicos.get(asistencia.getMecanico());
					}
					datosMecanicos.put(asistencia.getMecanico(), horas);

				}

			}

			datos.put(tipo.getNombre(), datosMecanicos);
		}

		return datos;
	}

}
