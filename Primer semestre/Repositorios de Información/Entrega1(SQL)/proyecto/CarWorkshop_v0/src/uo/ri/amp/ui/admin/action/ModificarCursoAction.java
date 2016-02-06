package uo.ri.amp.ui.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ModificarCursoAction implements Action{

	@Override
	public void execute() throws Exception {
		Map<String, Object> curso = new HashMap<String, Object>();
		List<Map<String, Object>> cursos = ServiceFactory.getAdminService().ListarCursos();
		
		boolean codigoCorrecto = false;

		do{
			curso.put("codigo", SantiUtil.checkStringCorrecto(Console.readString("Codigo del curso a modificar")));
			
			for(int i = 0; i < cursos.size(); i++){
				if(cursos.get(i).get("codigo").equals(curso.get("codigo"))){
					codigoCorrecto = true;
				}
			}
			if(!codigoCorrecto){
				Console.println("Ese codigo no pertenece a ningun curso. Introduce uno valido.");
			}
		} while(!codigoCorrecto);
		
		curso.put("nombre", Console.readString("Nombre"));
		curso.put("descripcion", Console.readString("Descripcion"));
				
		curso.put("numeroHoras", SantiUtil.checkNumero(Console.readString("Numero de horas")));
		
		List<Integer> tipos = new ArrayList<Integer>();
		List<Integer> porcentajes = new ArrayList<Integer>();
		
		int porcentaje = 0;
		List<Map<String, Object>> tiposVehiculo = ServiceFactory.getAdminService().ListarTiposVehiculo();
		
		Console.println("Tipos de vehiculos disponibles");
		for(int i = 0; i < tiposVehiculo.size(); i++){
			Console.printf("\tid: %d, nombre: %s\n", tiposVehiculo.get(i).get("id"), tiposVehiculo.get(i).get("nombre"));
		}
		
		do{
			tipos.add( SantiUtil.checkNumero(Console.readString("Tipo vehiculo(ID)")));
			porcentajes.add( SantiUtil.checkNumero(Console.readString("Porcentaje")));
			porcentaje += porcentajes.get(porcentajes.size() -1);
			if(porcentaje > 100){
				Console.println("Te has pasado del 100%, volvemos a empezar");
				porcentaje = 0;
				tipos = new ArrayList<Integer>();
				porcentajes = new ArrayList<Integer>();
			}
		}while(porcentaje < 100);
		
		curso.put("tipos", tipos);
		curso.put("porcentajes", porcentajes);
		
		ServiceFactory.getAdminService().actualizarCurso(curso);
		
		Console.print("Curso actualizado correctamente.");
		
	}

}
