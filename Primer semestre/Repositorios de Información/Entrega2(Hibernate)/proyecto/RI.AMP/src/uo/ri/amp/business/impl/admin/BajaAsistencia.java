package uo.ri.amp.business.impl.admin;

import javax.persistence.EntityManager;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.util.Jpa;

public class BajaAsistencia implements Comand {

	private String codigo;
	private Long id;

	public BajaAsistencia(String codigo, Long id) {
		this.codigo = codigo;
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		EntityManager eM = Jpa.getManager();

		Asistencia asistencia = (Asistencia) new BuscarAsistenciaPorCursoYMecanico(
				codigo, id).execute();
		eM.remove(asistencia);
		Association.Asistir.unlink(asistencia);

		return null;
	}

}
