package uo.ri.amp.business.impl.admin;

import java.util.List;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.TipoVehiculoFinder;

public class ListarTiposVehiculo implements Comand {

	@Override
	public Object execute() throws BusinessException {
		List<TipoVehiculo> tipos = TipoVehiculoFinder.listar();
		for (TipoVehiculo tipo : tipos) {
			tipo.getPorcentajeTipo();
		}
		return tipos;
	}

}
