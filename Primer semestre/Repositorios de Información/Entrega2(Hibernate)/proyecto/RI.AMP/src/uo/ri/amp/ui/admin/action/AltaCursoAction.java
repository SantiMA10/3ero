package uo.ri.amp.ui.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AltaCursoAction implements Action {

	@Override
	public void execute() throws Exception {

		String codigo = SantiUtil.checkNoVacio(Console.readString("Codigo"));
		String nombre = Console.readString("Nombre");
		String descripcion = Console.readString("Descripcion");
		int numHoras = SantiUtil.checkIntPositivo(Console
				.readString("Numero horas"));

		Curso curso = new Curso(codigo, nombre, descripcion, numHoras);

		Map<TipoVehiculo, Integer> porcentajes = new HashMap<TipoVehiculo, Integer>();
		int totalPorcentajes = 0;
		while (totalPorcentajes < 100) {

			List<TipoVehiculo> tipos = ServiceFactory.getAdminService()
					.listarTiposVehiculo();
			for (int i = 0; i < tipos.size(); i++) {
				Console.println("num.: " + i + " || "
						+ tipos.get(i).getNombre());
			}

			int id = SantiUtil
					.checkIntPositivo(Console.readString("Num. tipo"));
			if (id >= tipos.size()) {
				throw new BusinessException("No existe ese tipo de vehiculo.");
			}
			int porcentaje = SantiUtil.checkIntPositivo(Console
					.readString("Porcentaje"));

			if (porcentajes.containsKey(tipos.get(id))) {
				throw new BusinessException(
						"No puedes registrar dos porcentajes"
								+ " del mismo tipo de vehiculo");
			}
			porcentajes.put(tipos.get(id), porcentaje);

			totalPorcentajes += porcentaje;
			SantiUtil.checkPorcentaje(totalPorcentajes);

		}

		ServiceFactory.getAdminService().altaCurso(curso, porcentajes);
		Console.print("Curso añadido con exito.");

	}

}
