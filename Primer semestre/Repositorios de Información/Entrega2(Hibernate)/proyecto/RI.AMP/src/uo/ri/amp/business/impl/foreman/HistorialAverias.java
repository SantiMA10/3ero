package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Vehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.AveriasFinder;

public class HistorialAverias implements Comand {

	private String matricula;

	public HistorialAverias(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public Object execute() throws BusinessException {
		Long id = ((Vehiculo) new BuscarVehiculoPorMatricula(matricula)
				.execute()).getId();
		return AveriasFinder.listarPorIDVehiculo(id);
	}

}
