package uo.ri.amp.business.impl.foreman;

import javax.persistence.NoResultException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Vehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.VehiculosFinder;

public class BuscarVehiculoPorMatricula implements Comand {

	private String matricula;

	public BuscarVehiculoPorMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public Object execute() throws BusinessException {
		Vehiculo vehiculo = null;
		try {
			vehiculo = VehiculosFinder.buscarVehiculoPorMatricula(matricula);
			vehiculo.getAverias();
		} catch (NoResultException e) {
			throw new BusinessException("No hay vehiculos con esa matricula.");
		}
		return vehiculo;
	}

}
