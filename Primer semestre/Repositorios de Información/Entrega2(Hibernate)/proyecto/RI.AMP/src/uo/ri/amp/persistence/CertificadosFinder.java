package uo.ri.amp.persistence;

import uo.ri.amp.model.Certificado;
import uo.ri.amp.persistence.util.Jpa;

public class CertificadosFinder {

	public static void existe(Long idMecanico, Long idTipo) {
		Jpa.getManager()
				.createNamedQuery("Certificado.existe", Certificado.class)
				.setParameter(1, idMecanico).setParameter(2, idTipo)
				.getSingleResult();
	}

}
