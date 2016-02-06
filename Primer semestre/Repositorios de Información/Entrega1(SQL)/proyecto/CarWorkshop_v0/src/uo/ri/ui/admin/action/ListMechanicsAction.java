package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  

		List<Map<String, Object>> lista = ServiceFactory.getAdminService().listMechanics();
		
		for(Map<String, Object> map: lista){
			
			Console.printf("\t%d %s %s\n",  
				(long)map.get("id"),  
				(String)map.get("nombre"),  
				(String)map.get("apellidos")
			);
			
		}

	}
}
