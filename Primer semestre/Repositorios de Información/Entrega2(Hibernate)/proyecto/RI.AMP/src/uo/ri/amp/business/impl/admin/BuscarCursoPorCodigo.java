package uo.ri.amp.business.impl.admin;

import javax.persistence.NoResultException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.CursosFinder;

public class BuscarCursoPorCodigo implements Comand {

	private String codigo;

	public BuscarCursoPorCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public Object execute() throws BusinessException {
		Curso curso = null;
		try {
			curso = CursosFinder.buscarPorCodigo(codigo);
		} catch (NoResultException e) {
			throw new BusinessException("No hay curso con ese codigo.");
		}
		return curso;
	}

}
