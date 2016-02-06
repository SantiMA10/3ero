package uo.ri.amp.business.impl.admin;

import javax.persistence.NoResultException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.AsistenciasFinder;

public class BuscarAsistenciaPorCursoYMecanico implements Comand {

	private String codigo;
	private Long id;

	public BuscarAsistenciaPorCursoYMecanico(String codigo, Long id) {
		this.codigo = codigo;
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		Asistencia asistencia = null;
		try {
			asistencia = AsistenciasFinder.buscarPorCursoYMecanico(codigo, id);
		} catch (NoResultException e) {
			throw new BusinessException(
					"No hay asistencia para ese curso y/o ese mecanico.");
		}

		return asistencia;
	}

}
