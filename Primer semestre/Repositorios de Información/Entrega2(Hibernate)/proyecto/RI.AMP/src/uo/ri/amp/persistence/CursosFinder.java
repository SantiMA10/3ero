package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.Curso;
import uo.ri.amp.persistence.util.Jpa;

public class CursosFinder {

	public static List<Curso> listar() {
		return Jpa.getManager().createNamedQuery("Curso.listar", Curso.class)
				.getResultList();
	}

	public static Curso buscarPorCodigo(String codigo) {
		return Jpa.getManager()
				.createNamedQuery("Curso.buscarPorCodigo", Curso.class)
				.setParameter(1, codigo).getSingleResult();
	}

}
