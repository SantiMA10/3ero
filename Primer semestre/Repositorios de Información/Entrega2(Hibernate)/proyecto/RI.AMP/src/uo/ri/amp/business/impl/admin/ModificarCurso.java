package uo.ri.amp.business.impl.admin;

import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.PorcentajeTipo;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.TipoVehiculoFinder;
import uo.ri.amp.persistence.util.Jpa;

public class ModificarCurso implements Comand {

	private Curso curso;
	private Map<TipoVehiculo, Integer> porcentajes;

	public ModificarCurso(Curso curso, Map<TipoVehiculo, Integer> porcentajes) {
		this.curso = curso;
		this.porcentajes = porcentajes;
	}

	@Override
	public Object execute() throws BusinessException {

		EntityManager eM = Jpa.getManager();
		curso = eM.merge(curso);

		Set<PorcentajeTipo> porcentajeTipos = curso.getPorcentajeTipo();
		for (PorcentajeTipo porcentajeTipo : porcentajeTipos) {
			eM.remove(porcentajeTipo);
			Association.Impartir.unlink(porcentajeTipo);
		}

		Set<TipoVehiculo> tipos = porcentajes.keySet();
		for (TipoVehiculo tipo : tipos) {
			tipo = TipoVehiculoFinder.buscarPorId(tipo.getId());
			PorcentajeTipo porcentajeTipo = new PorcentajeTipo(tipo, curso,
					porcentajes.get(tipo));

			eM.persist(porcentajeTipo);
		}

		return null;
	}

}
