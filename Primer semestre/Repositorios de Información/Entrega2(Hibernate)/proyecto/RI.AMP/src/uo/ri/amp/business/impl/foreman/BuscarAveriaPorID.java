package uo.ri.amp.business.impl.foreman;

import javax.persistence.NoResultException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.AveriasFinder;

public class BuscarAveriaPorID implements Comand {

	private Long id;

	public BuscarAveriaPorID(Long id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		Averia averia = null;
		try {
			averia = AveriasFinder.buscarPorID(id);
			averia.getVehiculo().getTipo().getId();
		} catch (NoResultException e) {
			throw new BusinessException("No hay averias con ese id.");
		}

		return averia;
	}

}
