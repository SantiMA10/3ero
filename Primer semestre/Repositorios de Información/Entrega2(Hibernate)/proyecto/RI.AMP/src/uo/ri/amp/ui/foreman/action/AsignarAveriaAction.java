package uo.ri.amp.ui.foreman.action;

import java.util.List;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AsignarAveriaAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = SantiUtil.checkLong(Console.readString("Id averia"));
		Averia averia = ServiceFactory.getForemanService()
				.buscarAveriaPorID(id);

		List<Mecanico> mecanicos = ServiceFactory.getForemanService()
				.listarMecanicosExpertosEnTipo(
						averia.getVehiculo().getTipo().getId());
		if (mecanicos.size() == 0) {
			throw new BusinessException(
					"No hay expertos para ese tipo de vehiculo");
		}
		for (int i = 0; i < mecanicos.size(); i++) {
			Console.println("num.: " + i + " || "
					+ mecanicos.get(i).getNombre() + " || "
					+ mecanicos.get(i).getApellidos());
		}

		int i = SantiUtil.checkIntPositivo(Console.readString("Num. mecanico"));
		if (i >= mecanicos.size()) {
			throw new BusinessException("No existe ese mecanico.");
		}

		ServiceFactory.getForemanService().asignarAveria(averia,
				mecanicos.get(i));
		Console.println("Averia asignada con exito.");

	}

}
