package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.Averia;
import uo.ri.amp.persistence.util.Jpa;

public class AveriasFinder {

	public static Averia buscarPorID(Long id) {
		return Jpa.getManager()
				.createNamedQuery("Averia.buscarPorID", Averia.class)
				.setParameter(1, id).getSingleResult();
	}

	public static List<Averia> listarPorIDVehiculo(Long id) {
		return Jpa.getManager()
				.createNamedQuery("Averia.listarPorIDVehiculo", Averia.class)
				.setParameter(1, id).getResultList();
	}

}
