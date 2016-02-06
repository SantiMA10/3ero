package uo.ri.amp.ui.admin.action;

import java.util.Map;
import java.util.Set;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Mecanico;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarFormacionTipoVehiculoAction implements Action {

	@Override
	public void execute() throws Exception {
		Map<String, Map<Mecanico, Double>> listado = ServiceFactory
				.getAdminService().listarFormacionTipoVehiculo();
		Set<String> tipos = listado.keySet();

		for (String tipo : tipos) {
			Console.println(tipo);
			Map<Mecanico, Double> datosMecanicos = listado.get(tipo);

			Set<Mecanico> mecanicos = datosMecanicos.keySet();
			for (Mecanico mecanico : mecanicos) {
				Console.println("\t" + mecanico.getNombre() + ": "
						+ datosMecanicos.get(mecanico) + " horas");
			}

		}

	}

}
