package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListadoHorasAsistenciaTipoVehiculoAction implements Action{

	@Override
	public void execute() throws Exception {
		List<Map<String, Object>> tipos = ServiceFactory.getAdminService().listarHorasAsistenciaTipoVehiculo();
		for(int i = 0; i < tipos.size(); i++){
			Console.println(tipos.get(i).get("nombre"));
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> mecanicos = (List<Map<String, Object>>) tipos.get(i).get("mecanicos");
			for(int j = 0; j < mecanicos.size(); j++){
				Console.println("\t" + mecanicos.get(j).get("nombre") + " " + mecanicos.get(j).get("numhoras") + " horas.");
			}
		}
		
		if(tipos.size() == 0){
			Console.print("No hay registrada asistencia para ningun tipo de vehiculo.");
		}
		
	}

}
