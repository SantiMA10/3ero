package uo.ri.amp.business.impl.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.PorcentajeTipo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.util.Jpa;

public class ListarFormacionMecanico implements Comand {

	private Mecanico mecanico;

	public ListarFormacionMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	@Override
	public Object execute() throws BusinessException {
		EntityManager eM = Jpa.getManager();
		mecanico = eM.merge(mecanico);
		double horasTotal = 0.0, horasAsistidas = 0.0;
		Map<String, Double> asistenciaTipos = new HashMap<String, Double>();
		Set<Asistencia> asistencias = mecanico.getAsistencias();

		/*
		 * Recorro las asistencias del mecanico, guardando en el map las horas
		 * de asistencia a cada tipo.
		 */
		for (Asistencia asistencia : asistencias) {

			/*
			 * Como el listado tambien muestra las horas totales, las voy
			 * guardando.
			 */
			horasAsistidas += (asistencia.getAsistencia() / 100.0)
					* asistencia.getCurso().getNumHoras();

			horasTotal += asistencia.getCurso().getNumHoras();

			Set<PorcentajeTipo> porcentajeTipos = asistencia.getCurso()
					.getPorcentajeTipo();

			for (PorcentajeTipo porcentajeTipo : porcentajeTipos) {
				/*
				 * Calculo la suma de horas por tipo
				 */
				if (asistenciaTipos.containsKey(porcentajeTipo.getTipo()
						.getNombre())) {
					asistenciaTipos
							.put(porcentajeTipo.getTipo().getNombre(),
									((porcentajeTipo.getPorcentaje() / 100.0)
											* (asistencia.getAsistencia() / 100.0) * asistencia
											.getCurso().getNumHoras())
											+ asistenciaTipos
													.get(porcentajeTipo
															.getTipo()
															.getNombre()));
				} else {
					asistenciaTipos
							.put(porcentajeTipo.getTipo().getNombre(),
									((porcentajeTipo.getPorcentaje() / 100.0)
											* (asistencia.getAsistencia() / 100.0) * asistencia
											.getCurso().getNumHoras()));
				}
			}

		}

		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("horasTotal", horasTotal);
		datos.put("horasAsistidas", horasAsistidas);
		datos.put("asistenciaTipos", asistenciaTipos);

		return datos;
	}

}
