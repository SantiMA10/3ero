package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class BorrarAsistenciaAccion implements Action{

	@Override
	public void execute() throws Exception {
		String codigo = Console.readString("Codigo curso");
		
		List<Map<String, Object>> mecanicos = ServiceFactory.getAdminService().listarMecanicos();

		for(int i = 0; i < mecanicos.size(); i++){
			Console.printf("id: %d, nombre: %s", 
					mecanicos.get(i).get("id"), 
					mecanicos.get(i).get("nombre")); 
			Console.println();
		}
		int id = Console.readInt("Id mecanico");
		
		ServiceFactory.getAdminService().borrarAsistencia(codigo, id);
		Console.print("Asistencia borrada con exito.");
		
	}

}
