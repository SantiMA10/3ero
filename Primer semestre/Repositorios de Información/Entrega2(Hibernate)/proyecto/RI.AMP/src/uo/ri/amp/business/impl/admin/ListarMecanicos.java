package uo.ri.amp.business.impl.admin;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.MecanicoFinder;

public class ListarMecanicos implements Comand {

	@Override
	public Object execute() throws BusinessException {
		return MecanicoFinder.findAll();
	}

}
