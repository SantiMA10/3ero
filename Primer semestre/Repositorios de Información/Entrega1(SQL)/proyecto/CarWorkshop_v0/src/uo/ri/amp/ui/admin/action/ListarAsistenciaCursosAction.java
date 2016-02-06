package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarAsistenciaCursosAction implements Action{

	@Override
	public void execute() throws Exception {
		String codigo_curso = SantiUtil.checkStringCorrecto(Console.readString("Codigo curso"));
		
		List<Map<String, Object>> listado = ServiceFactory.getAdminService().listarAsistenciaCursos(codigo_curso);
		
		for(int i = 0; i < listado.size(); i++){
			Map<String, Object> elemento = listado.get(i);
			Console.println("\t" + elemento.get("nombre") + ", " + elemento.get("fechafin") +", " + elemento.get("asistencia") + "% asistencia, " + elemento.get("calificacion"));
		}
		
		if(listado.size() == 0){
			Console.println("No hay asistencia registrar a ese curso");
		}
		
	}

}
