package uo.ri.amp.ui.admin.action;

import java.util.List;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class BajaAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {
		String codigo = SantiUtil.checkNoVacio(Console
				.readString("Codigo curso"));
		List<Mecanico> mecanicos = ServiceFactory.getAdminService()
				.listarMecanicos();
		for (int i = 0; i < mecanicos.size(); i++) {
			Console.println("num.: " + i + " " + mecanicos.get(i));
		}

		int i = SantiUtil.checkInt(Console.readString("Num. mecanico"));
		if (i >= mecanicos.size()) {
			throw new BusinessException("Ese mecanico no esta en la lista.");
		}
		Long id = mecanicos.get(i).getId();

		ServiceFactory.getAdminService().bajaAsistencia(codigo, id);
		Console.println("Asistencia borrada con exito.");

	}

}
