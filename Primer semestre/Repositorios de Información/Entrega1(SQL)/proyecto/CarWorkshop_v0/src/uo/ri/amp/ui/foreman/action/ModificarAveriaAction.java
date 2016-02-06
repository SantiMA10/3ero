package uo.ri.amp.ui.foreman.action;

import java.util.Date;

import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.conf.ServiceFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ModificarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		int id = SantiUtil.checkNumero(Console.readString("Id Averia"));
		String matricula = SantiUtil.checkStringCorrecto(Console.readString("Matricula"));
		String descripcion = SantiUtil.checkStringCorrecto(Console.readString("Descripcion"));
		Date fecha = SantiUtil.checkFormatoFecha(Console.readString("Fecha de alta"));
		
		ServiceFactory.getForemanService().modificarAveria(id, matricula, descripcion, fecha);
		Console.print("Averia modificada con exito.");
		
	}

}
