package uo.ri.amp.ui.admin.action;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;

public class AltaAsistenciaAction implements Action{

	@Override
	public void execute() throws Exception {
		Map<String, Object> asistencia = new HashMap<String, Object>();
		List<Map<String, Object>> mecanicos = ServiceFactory.getAdminService().listarMecanicos();

		for(int i = 0; i < mecanicos.size(); i++){
			Console.printf("id: %d, nombre: %s", 
					mecanicos.get(i).get("id"), 
					mecanicos.get(i).get("nombre")); 
			Console.println();
		}

		asistencia.put("id_mecanico", SantiUtil.checkNumero(Console.readString("ID del mecanico")));
		asistencia.put("codigo_curso", SantiUtil.checkStringCorrecto(Console.readString("Codigo del curso")));
		asistencia.put("fechainicio", SantiUtil.checkFormatoFecha(Console.readString("Fecha inicio")));
		asistencia.put("fechafin", SantiUtil.checkFormatoFecha(Console.readString("Fecha fin")));
		if(DateUtil.isAfter((Date)asistencia.get("fechainicio"), (Date)asistencia.get("fechafin"))){
			throw new BusinessException("No puede ser la fecha de fin anterior a la fecha de inicio.");
		}
		asistencia.put("asistencia", SantiUtil.checkNumero(Console.readString("Porcentaje de asistencia")));
		asistencia.put("calificacion", SantiUtil.checkStringCorrecto(Console.readString("Calificacion('apto' o 'no apto')")));

		if((int)asistencia.get("asistencia") < 85 && asistencia.get("calificacion").equals("apto")){
			throw new BusinessException("Para ser apto necesita un minimo de 85% de asistencia.");
		}

		ServiceFactory.getAdminService().altaAsistencia(asistencia);
		Console.println("Asistencia registrada con exito.");


	}

}
