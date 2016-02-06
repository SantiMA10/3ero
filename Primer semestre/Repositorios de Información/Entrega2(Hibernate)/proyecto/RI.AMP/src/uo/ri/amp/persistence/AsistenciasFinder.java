package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.Asistencia;
import uo.ri.amp.persistence.util.Jpa;

public class AsistenciasFinder {

	public static List<Asistencia> listarPorCurso(String codigo) {
		return Jpa
				.getManager()
				.createNamedQuery("Asistencia.listarPorCurso", Asistencia.class)
				.setParameter(1, codigo).getResultList();
	}

	public static Asistencia buscarPorCursoYMecanico(String codigo, Long id) {
		return Jpa
				.getManager()
				.createNamedQuery("Asistencia.buscarPorCursoYMecanico",
						Asistencia.class).setParameter(1, codigo)
				.setParameter(2, id).getSingleResult();
	}

}
