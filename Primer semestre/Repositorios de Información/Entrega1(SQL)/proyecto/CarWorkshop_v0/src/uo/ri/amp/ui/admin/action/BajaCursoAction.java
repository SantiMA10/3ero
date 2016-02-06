package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class BajaCursoAction implements Action{

	@Override
	public void execute() throws Exception {
		List<Map<String, Object>> cursos = ServiceFactory.getAdminService().ListarCursos();
		
		boolean codigoCorrecto = false;
		String codigo;
		
		do{
			codigo = SantiUtil.checkStringCorrecto(Console.readString("Codigo del curso a borrar"));
			
			for(int i = 0; i < cursos.size(); i++){
				if(cursos.get(i).get("codigo").equals(codigo)){
					codigoCorrecto = true;
				}
			}
			if(!codigoCorrecto){
				Console.println("Ese codigo no pertenece a ningun curso. Introduce uno valido.");
			}
		} while(!codigoCorrecto);
		
		if(ServiceFactory.getAdminService().borrarCurso(codigo)){
			Console.println("Curso borrado con exito.");
		}
		else{
			Console.println("No hay ningun curso con ese codigo.");
		}
		
	}

}
