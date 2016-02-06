package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.util.Jpa;

public class ModificarAveria implements Comand {

	private Averia averia;

	public ModificarAveria(Averia averia) {
		this.averia = averia;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(averia);
		return null;
	}

}
