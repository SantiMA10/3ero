package uo.ri.amp.business.impl.foreman;

import javax.persistence.NoResultException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.AveriaStatus;
import uo.ri.amp.persistence.AveriasFinder;
import uo.ri.amp.persistence.util.Jpa;

public class BajaAveria implements Comand {

	private Long id;

	public BajaAveria(Long id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		Averia averia = null;
		try {
			averia = AveriasFinder.buscarPorID(id);
		} catch (NoResultException e) {
			throw new BusinessException("No hay averias con ese id.");
		}
		if (averia.getStatus().equals(AveriaStatus.ABIERTA)) {
			Association.Averiar.unlink(averia);
		} else if (averia.getStatus().equals(AveriaStatus.ASIGNADA)) {
			Association.Averiar.unlink(averia);
			Association.Asignar.unlink(averia.getMecanico(), averia);
		} else {
			throw new BusinessException(
					"No puedes borrar una averia terminada/facturada.");
		}
		Jpa.getManager().remove(averia);

		return null;
	}

}
