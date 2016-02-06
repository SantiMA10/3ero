package uo.ri.amp.business.impl.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Certificado;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.PorcentajeTipo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.Calificacion;
import uo.ri.amp.persistence.CertificadosFinder;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.amp.persistence.util.Jpa;

public class GenerarCertificados implements Comand {

	@Override
	public Object execute() throws BusinessException {
		List<Mecanico> mecanicos = MecanicoFinder.findAll();

		for (Mecanico mecanico : mecanicos) {
			/**
			 * Recorro todas las asistencias de un mecanico y voy guardando en
			 * el map el numero de horas que ha asistido a cada tipo de vehiculo
			 */
			Set<Asistencia> asistencias = mecanico.getAsistencias();
			Map<Long, Double> cursado = new HashMap<Long, Double>();

			for (Asistencia asistencia : asistencias) {
				if (asistencia.getCalificacion().equals(Calificacion.APTO)) {

					Set<PorcentajeTipo> porcentajeTipos = asistencia.getCurso()
							.getPorcentajeTipo();

					for (PorcentajeTipo porcentajeTipo : porcentajeTipos) {
						Long id = porcentajeTipo.getTipo().getId();
						double horasCursadas = ((porcentajeTipo.getPorcentaje() / 100.0) * asistencia
								.getCurso().getNumHoras())
								* (asistencia.getAsistencia() / 100.0);

						if (cursado.containsKey(id)) {
							horasCursadas += cursado.get(id);
						}

						cursado.put(id, horasCursadas);

						/**
						 * Compruebo si ha superado las horas de formacion, si
						 * las ha superado busco si ya tiene un certificado, si
						 * no lo tiene y la query me devuelve la excepcion creo
						 * el certificado y lo hago persist.
						 */
						if (horasCursadas >= porcentajeTipo.getTipo()
								.getNumHorasExperto()) {

							try {
								CertificadosFinder.existe(mecanico.getId(),
										porcentajeTipo.getTipo().getId());
							} catch (NoResultException e) {
								Certificado certificado = new Certificado(
										mecanico, porcentajeTipo.getTipo(),
										new Date());
								Jpa.getManager().persist(certificado);
							}

						}

					}
				}
			}
		}

		return null;
	}

}
