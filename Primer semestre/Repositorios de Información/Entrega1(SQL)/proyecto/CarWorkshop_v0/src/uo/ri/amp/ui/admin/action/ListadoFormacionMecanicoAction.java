package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListadoFormacionMecanicoAction implements Action{

	@Override
	public void execute() throws Exception {
		List<Map<String, Object>> mecanicos = ServiceFactory.getAdminService().listarMecanicos();

		for(int i = 0; i < mecanicos.size(); i++){
			Console.printf("id: %d, nombre: %s", 
					mecanicos.get(i).get("id"), 
					mecanicos.get(i).get("nombre")); 
			Console.println();
		}
		
		long id = SantiUtil.checkNumero(Console.readString("Id mecanico"));
		Map<String, Object> listado = ServiceFactory.getAdminService().listarFormacionMecanico(id);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> tipo = (List<Map<String, Object>>) listado.get("horasportipo");
		
		if(tipo.size() == 0){
			Console.print("Este mecanico no ha asistido a ningun curso.");
		}
		else{
			Console.println("Total de horas de los cursos: " + listado.get("horascurso"));
			Console.println("Total de horas asistidas: " + listado.get("horasasistidas"));
			
			
			for(int i = 0; i < tipo.size(); i++){
				Console.println(tipo.get(i).get("nombre") + ": " + tipo.get(i).get("numhoras"));
			}
		}
		
	}

}
