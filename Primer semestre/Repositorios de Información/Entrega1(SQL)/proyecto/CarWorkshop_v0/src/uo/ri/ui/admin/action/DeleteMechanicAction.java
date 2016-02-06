package uo.ri.ui.admin.action;

import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
		ServiceFactory.getAdminService().deleteMechanic(idMecanico);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
