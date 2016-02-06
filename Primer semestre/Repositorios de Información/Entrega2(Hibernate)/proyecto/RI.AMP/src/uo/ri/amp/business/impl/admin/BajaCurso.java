package uo.ri.amp.business.impl.admin;

import java.util.Set;

import javax.persistence.EntityManager;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.PorcentajeTipo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.util.Jpa;

public class BajaCurso implements Comand {

	private Curso curso;

	public BajaCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public Object execute() throws BusinessException {
		EntityManager eM = Jpa.getManager();

		curso = eM.merge(curso);
		if (curso.getAsistencia().size() != 0) {
			throw new BusinessException(
					"No puedes borrar el curso, porque hay asistencia registrada");
		}
		Set<PorcentajeTipo> porcentajeTipos = curso.getPorcentajeTipo();
		for (PorcentajeTipo porcentajeTipo : porcentajeTipos) {
			eM.remove(porcentajeTipo);
			Association.Impartir.unlink(porcentajeTipo);
		}
		eM.remove(curso);

		return null;
	}

}
