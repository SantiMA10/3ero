package uo.ri.amp.business.impl.admin;

import java.util.List;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.CursosFinder;

public class ListarCursos implements Comand {

	@Override
	public Object execute() throws BusinessException {
		List<Curso> cursos = CursosFinder.listar();
		for (Curso curso : cursos) {
			curso.getPorcentajeTipo();
		}
		return cursos;
	}

}
