package uo.ri.amp.ui.foreman.action;

import java.util.Date;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class RegistrarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		String matricula = SantiUtil.checkStringCorrecto(Console.readString("Matricula"));
		String descripcion = SantiUtil.checkStringCorrecto(Console.readString("Descripcion"));
		Date fecha = SantiUtil.checkFormatoFecha(Console.readString("Fecha de alta"));
		
		ServiceFactory.getForemanService().registrarAveria(matricula, descripcion, fecha);
		Console.print("Averia registrada con exito.");
	}

}
