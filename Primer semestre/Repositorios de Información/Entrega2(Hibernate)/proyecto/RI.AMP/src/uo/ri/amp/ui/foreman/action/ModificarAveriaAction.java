package uo.ri.amp.ui.foreman.action;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Averia;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ModificarAveriaAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = SantiUtil.checkLong(Console.readString("Id averia"));
		Averia averia = ServiceFactory.getForemanService()
				.buscarAveriaPorID(id);

		String descripcion = SantiUtil.checkNoVacio(Console
				.readString("Descripcion"));

		averia.setDescripcion(descripcion);

		ServiceFactory.getForemanService().modifcarAveria(averia);
		Console.println("Averia modificada con exito.");
	}

}
