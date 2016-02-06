package uo.ri.amp.model;

import uo.ri.amp.model.types.AveriaStatus;

public class Association {

	public static class Poseer {

		public static void link(Cliente cliente, Vehiculo vehiculo) {
			vehiculo._setCliente(cliente);
			cliente._getVehiculos().add(vehiculo);

		}

		public static void unlink(Cliente cliente, Vehiculo vehiculo) {
			cliente._getVehiculos().remove(vehiculo);
			vehiculo._setCliente(null);

		}
	}

	public static class Clasificar {

		public static void link(TipoVehiculo tipoVehiculo, Vehiculo vehiculo) {
			vehiculo._setTipo(tipoVehiculo);
			tipoVehiculo._getVehiculos().add(vehiculo);

		}

		public static void unlink(TipoVehiculo tipoVehiculo, Vehiculo vehiculo) {
			tipoVehiculo._getVehiculos().remove(vehiculo);
			vehiculo._setTipo(null);
		}
	}

	public static class Pagar {

		public static void link(Cliente cliente, MedioPago medioPago) {
			cliente._getMediosPago().add(medioPago);
		}

		public static void unlink(Cliente cliente, MedioPago medioPago) {
			cliente._getMediosPago().remove(medioPago);
			medioPago._setCliente(null);

		}
	}

	public static class Averiar {

		public static void link(Vehiculo vehiculo, Averia averia) {
			vehiculo._getAverias().add(averia);
			vehiculo.setNumAverias(vehiculo.getNumAverias() + 1);
		}

		public static void unlink(Averia averia) {
			averia.getVehiculo().setNumAverias(
					averia.getVehiculo().getNumAverias() - 1);
			averia.getVehiculo()._getAverias().remove(averia);
			averia._setVehiculo(null);
		}
	}

	/* package */static class Facturar {

		public static void link(Averia averia, Factura factura) {
			averia._setFactura(factura);
		}

		public static void unlink(Averia averia, Factura factura) {
			averia._setFactura(null);
		}
	}

	public static class Cargar {

		public static void link(Factura factura, MedioPago medioPago,
				Cargo cargo) {
			factura._getCargos().add(cargo);
			medioPago._getCargos().add(cargo);
			medioPago.setAcumulado(medioPago.getAcumulado()
					+ cargo.getImporte());
		}

		public static void unlink(Cargo cargo) {
			cargo.getFactura()._getCargos().remove(cargo);
			cargo.getMedioPago()._getCargos().remove(cargo);
			cargo.getMedioPago().setAcumulado(
					cargo.getMedioPago().getAcumulado() - cargo.getImporte());

			cargo._setFactura(null);
			cargo._setMedioPago(null);
		}
	}

	public static class Asignar {

		public static void link(Mecanico mecanico, Averia averia) {
			averia._setMecanico(mecanico);
			averia.setStatus(AveriaStatus.ASIGNADA);
			mecanico._getAsignadas().add(averia);

		}

		public static void unlink(Mecanico mecanico, Averia averia) {
			mecanico._getAsignadas().remove(averia);
			averia._setMecanico(null);

		}
	}

	public static class Intervenir {

		public static void link(Averia averia, Mecanico mecanico,
				Intervencion intervencion) {

			intervencion._setAveria(averia);
			intervencion._setMecanico(mecanico);

			mecanico._getIntervenciones().add(intervencion);
			averia._getIntervenciones().add(intervencion);
		}

		public static void unlink(Intervencion intervencion) {

			intervencion.getMecanico()._getIntervenciones()
					.remove(intervencion);
			intervencion.getAveria()._getIntervenciones().remove(intervencion);

			intervencion._setAveria(null);
			intervencion._setMecanico(null);

		}
	}

	public static class Sustituir {

		public static void link(Repuesto repuesto, Intervencion intervencion,
				Sustitucion sustitucion) {

			sustitucion._setIntervencion(intervencion);
			sustitucion._setRepuesto(repuesto);

			intervencion._getSustituciones().add(sustitucion);
			repuesto._getSustituciones().add(sustitucion);

		}

		public static void unlink(Sustitucion sustitucion) {

			sustitucion.getIntervencion()._getSustituciones()
					.remove(sustitucion);
			sustitucion.getRepuesto()._getSustituciones().remove(sustitucion);

			sustitucion._setIntervencion(null);
			sustitucion._setRepuesto(null);

		}
	}

	public static class Asistir {

		public static void link(Curso curso, Mecanico mecanico,
				Asistencia asistencia) {

			asistencia._setCurso(curso);
			asistencia._setMecanico(mecanico);

			curso._getAsistencias().add(asistencia);
			mecanico._getAsistencias().add(asistencia);

		}

		public static void unlink(Asistencia asistencia) {

			asistencia.getCurso()._getAsistencias().remove(asistencia);
			asistencia.getMecanico()._getAsistencias().remove(asistencia);

			asistencia._setCurso(null);
			asistencia._setMecanico(null);

		}

	}

	public static class Certificar {

		public static void link(TipoVehiculo tipo, Mecanico mecanico,
				Certificado certificado) {

			certificado._setMecanico(mecanico);
			certificado._setTipo(tipo);

			tipo._getCertificados().add(certificado);
			mecanico._getCertificados().add(certificado);

		}

		public static void unlink(Certificado certificado) {

			certificado.getMecanico()._getCertificados().remove(certificado);
			certificado.getTipo()._getCertificados().remove(certificado);

			certificado._setMecanico(null);
			certificado._setTipo(null);

		}

	}

	public static class Impartir {

		public static void link(TipoVehiculo tipo, Curso curso,
				PorcentajeTipo porcentaje) {

			porcentaje._setCurso(curso);
			porcentaje._setTipo(tipo);

			curso._getPorcentajeTipo().add(porcentaje);
			tipo._getPorcentajeTipo().add(porcentaje);

		}

		public static void unlink(PorcentajeTipo porcentaje) {

			porcentaje.getCurso()._getPorcentajeTipo().remove(porcentaje);
			porcentaje.getTipo()._getPorcentajeTipo().remove(porcentaje);

			porcentaje._setCurso(null);
			porcentaje._setTipo(null);

		}

	}

}
