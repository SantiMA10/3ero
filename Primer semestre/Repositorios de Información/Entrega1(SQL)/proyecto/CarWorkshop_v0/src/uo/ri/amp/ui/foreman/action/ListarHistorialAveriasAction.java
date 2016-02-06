package uo.ri.amp.ui.foreman.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarHistorialAveriasAction implements Action{

	@Override
	public void execute() throws Exception {
		String matricula = SantiUtil.checkStringCorrecto(Console.readString("Matricula"));
		
		List<Map<String, Object>> lista = ServiceFactory.getForemanService().listarHistorialAverias(matricula);
		for(int i = 0; i < lista.size(); i++){
			Map<String, Object> averia = lista.get(i);
			Console.println("id: " + averia.get("id") + ", fecha: " + averia.get("fecha") + 
					", descripcion: " + averia.get("descripcion"));
		}
		if(lista.size() == 0){
			Console.print("No hay averias para ese vehiculo.");
		}
		
	}

}
