package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.Mecanico;
import uo.ri.amp.persistence.util.Jpa;

public class MecanicoFinder {

	public static Mecanico findById(Long id) {
		return Jpa.getManager().find(Mecanico.class, id);
	}

	public static List<Mecanico> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Mecanico.listar", Mecanico.class)
				.getResultList();
	}

	public static List<Mecanico> listarExpertos(Long idTipo) {
		return Jpa.getManager()
				.createNamedQuery("Mecanico.expertos", Mecanico.class)
				.setParameter(1, idTipo).getResultList();
	}

}
