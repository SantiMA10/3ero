package uo.ri.amp.persistence;

import uo.ri.amp.model.Vehiculo;
import uo.ri.amp.persistence.util.Jpa;

public class VehiculosFinder {

	public static Vehiculo buscarVehiculoPorMatricula(String matricula) {
		return Jpa
				.getManager()
				.createNamedQuery("Vehiculo.buscarPorMatricula", Vehiculo.class)
				.setParameter(1, matricula).getSingleResult();
	}

}
