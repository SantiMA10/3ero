package uo.ri.amp.ui.admin.action;

import java.util.Date;
import java.util.List;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.Calificacion;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ModificarAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {
		String codigo = SantiUtil.checkNoVacio(Console
				.readString("Codigo curso"));
		List<Mecanico> mecanicos = ServiceFactory.getAdminService()
				.listarMecanicos();
		for (int i = 0; i < mecanicos.size(); i++) {
			Console.println("num.: " + i + " || "
					+ mecanicos.get(i).getNombre() + " || "
					+ mecanicos.get(i).getApellidos());
		}

		int i = SantiUtil.checkIntPositivo(Console.readString("Num. mecanico"));
		if (i >= mecanicos.size()) {
			throw new BusinessException("Ese mecanico no esta en la lista.");
		}
		Long id = mecanicos.get(i).getId();

		Asistencia asistencia = ServiceFactory.getAdminService()
				.buscarAsistenciaPorCursoYMecanico(codigo, id);

		Date fechaInicio = SantiUtil.checkFecha(Console
				.readString("Fecha inicio"));
		Date fechaFin = SantiUtil.checkFecha(Console.readString("Fecha fin"));
		SantiUtil.checkFechaPosterior(fechaInicio, fechaFin);

		int porcentaje = SantiUtil.checkPorcentaje(Console
				.readString("Porcentaje asistencia"));
		String calificacionString = SantiUtil.checkNoVacio(Console
				.readString("Calificacion('apto' o 'no apto')"));

		Calificacion calificacion = SantiUtil.checkCalificacion(porcentaje,
				calificacionString);

		asistencia.setAsistencia(porcentaje);
		asistencia.setCalificacion(calificacion);
		asistencia.setFechaFin(fechaFin);
		asistencia.setFechaInicio(fechaInicio);

		ServiceFactory.getAdminService().modificarAsistencia(asistencia);
		Console.println("Asistencia modificada con exito.");

	}

}
