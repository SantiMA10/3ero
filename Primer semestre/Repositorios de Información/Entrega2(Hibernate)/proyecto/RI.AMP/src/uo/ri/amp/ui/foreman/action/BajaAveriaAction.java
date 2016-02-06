package uo.ri.amp.ui.foreman.action;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class BajaAveriaAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = SantiUtil.checkLong(Console.readString("Id averia"));

		ServiceFactory.getForemanService().bajaAveria(id);
		Console.println("Averia eliminada con exito.");

	}

}
