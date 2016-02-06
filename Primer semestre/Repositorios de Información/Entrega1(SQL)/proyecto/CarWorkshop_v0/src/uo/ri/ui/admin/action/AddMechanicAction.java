package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		//Procesar
		ServiceFactory.getAdminService().addMechanic(nombre, apellidos);
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
