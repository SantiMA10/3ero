package uo.ri.amp.ui.admin.action;

import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class GenerarCertificadosAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("Generando certificados.");
		ServiceFactory.getAdminService().generarCertificados();
		Console.println("Certificados generados.");
	}

}
