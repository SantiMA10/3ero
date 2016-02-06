package uo.ri.amp.ui.admin.action;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Curso;
import uo.ri.amp.util.SantiUtil;
import alb.util.console.Console;
import alb.util.menu.Action;

public class BajaCursoAction implements Action {

	@Override
	public void execute() throws Exception {
		String codigo = SantiUtil.checkNoVacio(Console.readString("Codigo"));

		Curso curso = ServiceFactory.getAdminService().buscarCursoPorCodigo(
				codigo);

		ServiceFactory.getAdminService().bajaCurso(curso);
		Console.println("Curso dado de baja con exito");
	}

}
