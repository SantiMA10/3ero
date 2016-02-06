package uo.ri.amp.ui.admin.action;

import java.util.List;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.util.SantiUtil;
import uo.ri.amp.util.OutputUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {
		String codigo = SantiUtil.checkNoVacio(Console
				.readString("Codigo curso"));
		List<Asistencia> asistencias = ServiceFactory.getAdminService()
				.listarAsistenciaCurso(codigo);
		if (asistencias.size() == 0) {
			Console.println("No hay asistencias al curso.");
		}

		for (Asistencia asistencia : asistencias) {
			Console.println(asistencia.getMecanico().getNombre() + " || "
					+ OutputUtil.formatDate(asistencia.getFechaFin()) + " || "
					+ asistencia.getAsistencia() + "% || "
					+ asistencia.getCalificacion());
		}

	}

}
