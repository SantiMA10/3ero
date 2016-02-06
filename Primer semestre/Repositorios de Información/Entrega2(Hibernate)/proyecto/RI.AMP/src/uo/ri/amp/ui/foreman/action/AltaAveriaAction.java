package uo.ri.amp.ui.foreman.action;

import java.util.Date;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Vehiculo;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AltaAveriaAction implements Action {

	@Override
	public void execute() throws Exception {

		String matricula = SantiUtil.checkNoVacio(Console
				.readString("Matricula"));
		Vehiculo vehiculo = ServiceFactory.getForemanService()
				.buscarVehiculoPorMatricula(matricula);

		String descripcion = SantiUtil.checkNoVacio(Console
				.readString("Descripcion"));
		Date fecha = SantiUtil.checkFecha(Console.readString("Fecha de alta"));

		Averia averia = new Averia(vehiculo, descripcion, fecha);

		ServiceFactory.getForemanService().altaAveria(averia);
		Console.println("Averia registrada con exito.");

	}

}
