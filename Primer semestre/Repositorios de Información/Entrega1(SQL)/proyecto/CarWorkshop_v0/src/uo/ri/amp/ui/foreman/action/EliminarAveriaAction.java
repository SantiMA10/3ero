package uo.ri.amp.ui.foreman.action;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class EliminarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		int idAveria = SantiUtil.checkNumero(Console.readString("Id averia"));

		ServiceFactory.getForemanService().eliminarAveria(idAveria);
		Console.println("Averia eliminada correctamente.");
	}

}
