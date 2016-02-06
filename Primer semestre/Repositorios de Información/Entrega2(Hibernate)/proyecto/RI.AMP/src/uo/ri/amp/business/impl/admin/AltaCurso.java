package uo.ri.amp.business.impl.admin;

import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.PorcentajeTipo;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.TipoVehiculoFinder;
import uo.ri.amp.persistence.util.Jpa;

public class AltaCurso implements Comand {

	private Curso curso;
	private Map<TipoVehiculo, Integer> porcentajes;

	public AltaCurso(Curso curso, Map<TipoVehiculo, Integer> porcentajes) {
		this.curso = curso;
		this.porcentajes = porcentajes;
	}

	@Override
	public Object execute() throws BusinessException {

		EntityManager eM = Jpa.getManager();

		Set<TipoVehiculo> tipos = porcentajes.keySet();
		for (TipoVehiculo tipo : tipos) {
			tipo = TipoVehiculoFinder.buscarPorId(tipo.getId());
			PorcentajeTipo porcentajeTipo = new PorcentajeTipo(tipo, curso,
					porcentajes.get(tipo));
			try {
				eM.persist(curso);
				eM.persist(porcentajeTipo);
			} catch (PersistenceException e) {
				throw new BusinessException("Ya hay un curso con ese codigo.");
			}
		}

		return null;
	}

}
