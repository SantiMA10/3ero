package uo.ri.amp.ui.admin.action;

import java.util.Date;
import java.util.List;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.Calificacion;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AltaAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {

		List<Mecanico> mecanicos = ServiceFactory.getAdminService()
				.listarMecanicos();
		for (int i = 0; i < mecanicos.size(); i++) {
			Console.println("num.: " + i + " || "
					+ mecanicos.get(i).getNombre() + " || "
					+ mecanicos.get(i).getApellidos());
		}

		int id = SantiUtil
				.checkIntPositivo(Console.readString("Num. mecanico"));
		if (id >= mecanicos.size()) {
			throw new BusinessException("No existe ese mecanico.");
		}
		Long mecanico = mecanicos.get(id).getId();
		String codigo = SantiUtil.checkNoVacio(Console
				.readString("Codigo curso"));

		Date fechaInicio = SantiUtil.checkFecha(Console
				.readString("Fecha inicio"));
		Date fechaFin = SantiUtil.checkFecha(Console.readString("Fecha fin"));

		SantiUtil.checkFechaPosterior(fechaInicio, fechaFin);

		int asistencia = SantiUtil.checkPorcentaje(Console
				.readString("Porcentaje asistencia"));
		String calificacionString = SantiUtil.checkNoVacio(Console
				.readString("Calificacion('apto' o 'no apto')"));

		Calificacion calificacion = SantiUtil.checkCalificacion(asistencia,
				calificacionString);

		ServiceFactory.getAdminService().altaAsistencia(mecanico, codigo,
				calificacion, fechaInicio, fechaFin, asistencia);
		Console.println("Asistencia registrada con extio.");

	}

}
