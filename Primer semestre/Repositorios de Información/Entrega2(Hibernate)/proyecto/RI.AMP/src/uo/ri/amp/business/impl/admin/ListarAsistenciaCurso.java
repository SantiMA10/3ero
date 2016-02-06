package uo.ri.amp.business.impl.admin;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.AsistenciasFinder;

public class ListarAsistenciaCurso implements Comand {

	private String codigo;

	public ListarAsistenciaCurso(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public Object execute() throws BusinessException {
		return AsistenciasFinder.listarPorCurso(codigo);
	}

}
