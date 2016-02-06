package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarCursosAction implements Action{

	@Override
	public void execute() throws Exception {
		
		List<Map<String, Object>> cursos = ServiceFactory.getAdminService().ListarCursos();
		
		for(Map<String, Object> curso : cursos){
			Console.printf("*Codigo: %s, nombre: %s, descripcion: %s, numero de horas: %d\n", 
					curso.get("codigo"),
					curso.get("nombre"),
					curso.get("descripcion"),
					curso.get("numeroHoras"));
			
			Console.print("\tPorcentajes:");
			
			@SuppressWarnings("unchecked")
			List<String> tipos = (List<String>) curso.get("tipos");
			@SuppressWarnings("unchecked")
			List<String> porcentajes = (List<String>) curso.get("porcentajes");
			
			for(int i = 0; i < tipos.size(); i++){
				Console.printf("\n\t\ttipo: %s  %s", tipos.get(i), porcentajes.get(i));
				Console.print("% de las horas");
			}
			Console.println();
			
		}
		
		if(cursos.size() == 0){
			Console.print("No hay cursos que mostrar.");
		}
		
	}

}
