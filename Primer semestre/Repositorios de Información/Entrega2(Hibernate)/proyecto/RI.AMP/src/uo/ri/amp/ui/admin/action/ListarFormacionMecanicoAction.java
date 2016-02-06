package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarFormacionMecanicoAction implements Action {

	@Override
	public void execute() throws Exception {

		List<Mecanico> mecanicos = ServiceFactory.getAdminService()
				.listarMecanicos();
		for (int i = 0; i < mecanicos.size(); i++) {
			Console.println("num.: " + i + " || "
					+ mecanicos.get(i).getNombre() + " || "
					+ mecanicos.get(i).getApellidos());
		}

		int id = SantiUtil.checkInt(Console.readString("Num. mecanico"));
		if (id >= mecanicos.size()) {
			throw new BusinessException("No existe ese mecanico.");
		}

		Map<String, Object> datos = ServiceFactory.getAdminService()
				.listarFormacionMecanico(mecanicos.get(id));

		Console.println("Total de horas de los cursos: "
				+ datos.get("horasTotal"));
		Console.println("Total de horas asistidas: "
				+ datos.get("horasAsistidas"));
		@SuppressWarnings("unchecked")
		Map<String, Object> asistenciaTipos = ((Map<String, Object>) datos
				.get("asistenciaTipos"));
		Set<String> keys = asistenciaTipos.keySet();
		for (String key : keys) {
			Console.println(key + ": " + asistenciaTipos.get(key));
		}

	}

}
