package uo.ri.amp.ui.foreman.action;

import java.util.List;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Averia;
import uo.ri.amp.util.SantiUtil;
import uo.ri.amp.util.OutputUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class HistorialAveriasAction implements Action {

	@Override
	public void execute() throws Exception {
		String matricula = SantiUtil.checkNoVacio(Console
				.readString("Matricula"));
		List<Averia> averias = ServiceFactory.getForemanService()
				.historialAverias(matricula);
		if (averias.size() == 0) {
			Console.println("No hay averias para ese vehiculo.");
		}

		for (Averia averia : averias) {
			Console.println(averia.getStatus() + " || " + averia.getId()
					+ " || " + OutputUtil.formatDate(averia.getFecha())
					+ " || " + averia.getDescripcion());
		}

	}

}
