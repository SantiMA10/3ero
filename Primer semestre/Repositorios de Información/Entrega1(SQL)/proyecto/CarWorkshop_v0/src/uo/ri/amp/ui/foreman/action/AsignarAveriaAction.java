package uo.ri.amp.ui.foreman.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AsignarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		
		int idAveria = SantiUtil.checkNumero(Console.readString("Id averia"));
		
		List<Map<String, Object>> lista = ServiceFactory.getForemanService().listarMecanicosExpertos(idAveria);
		if(lista.size() != 0){
			Console.println("Lista de mecanicos expertos en el vehiculo de la averia:");
			for(int i = 0; i < lista.size(); i++){
				Map<String, Object> mecanico = lista.get(i);
				Console.println("\tid: " + mecanico.get("id") + ", nombre: " + mecanico.get("nombre"));
			}
		}
		else{
			throw new BusinessException("No hay expertos en el tipo de vehiculo de la averia.");
		}
		
		
		long idMecanico = SantiUtil.checkNumero(Console.readString("Id mecanico"));
		boolean mecanicoCorrecto = false;
		for(int i = 0; i < lista.size(); i++){
			if(idMecanico == (long)lista.get(i).get("id")){
				mecanicoCorrecto = true;
			}
		}
		
		if(!mecanicoCorrecto)
			throw new BusinessException("No has elegido un mecanico de la lista");
		
		ServiceFactory.getForemanService().asignarAveria(idAveria, idMecanico);
		Console.println("Averia asignada correctamente.");
		
	}

}
