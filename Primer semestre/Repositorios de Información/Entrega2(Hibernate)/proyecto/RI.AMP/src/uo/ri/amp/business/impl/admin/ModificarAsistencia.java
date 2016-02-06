package uo.ri.amp.business.impl.admin;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.AsistenciasFinder;

public class ModificarAsistencia implements Comand {

	private Asistencia asistencia;

	public ModificarAsistencia(Asistencia asistencia) {
		this.asistencia = asistencia;
	}

	@Override
	public Object execute() throws BusinessException {

		/**
		 * Utilizo el objeto asistencia para encontrar y cambiar los datos ya
		 * que pensaba que podia hacerle un merge y para no tener que cambiar
		 * nada lo deje asi.
		 */
		Asistencia persistente = AsistenciasFinder.buscarPorCursoYMecanico(
				asistencia.getCurso().getCodigo(), asistencia.getMecanico()
						.getId());

		persistente.setAsistencia(asistencia.getAsistencia());
		persistente.setCalificacion(asistencia.getCalificacion());
		persistente.setFechaFin(asistencia.getFechaFin());
		persistente.setFechaInicio(asistencia.getFechaInicio());

		// eM.merge(asistencia);

		return null;
	}

}
