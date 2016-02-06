package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.persistence.util.Jpa;

public class TipoVehiculoFinder {

	public static TipoVehiculo buscarPorId(Long id) {
		return Jpa.getManager().find(TipoVehiculo.class, id);
	}

	public static List<TipoVehiculo> listar() {
		return Jpa.getManager()
				.createNamedQuery("TipoVehiculo.listar", TipoVehiculo.class)
				.getResultList();
	}

}
