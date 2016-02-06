package uo.ri.amp.business.impl.foreman;

import javax.persistence.EntityManager;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.amp.persistence.util.Jpa;

public class AsignarAveria implements Comand {

	private Averia averia;
	private Mecanico mecanico;

	public AsignarAveria(Averia averia, Mecanico mecanico) {
		this.averia = averia;
		this.mecanico = mecanico;
	}

	@Override
	public Object execute() throws BusinessException {
		EntityManager eM = Jpa.getManager();
		mecanico = MecanicoFinder.findById(mecanico.getId());
		averia = eM.merge(averia);

		Association.Asignar.link(mecanico, averia);
		return null;
	}

}
