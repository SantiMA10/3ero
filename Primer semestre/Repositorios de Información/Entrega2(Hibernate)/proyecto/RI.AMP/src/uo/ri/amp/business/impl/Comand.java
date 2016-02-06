package uo.ri.amp.business.impl;

import uo.ri.amp.model.exception.BusinessException;

public interface Comand {

	public Object execute() throws BusinessException;

}
