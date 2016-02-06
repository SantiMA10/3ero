package uo.ri.amp.ui.admin.action;

import java.util.List;
import java.util.Set;

import uo.ri.amp.conf.ServiceFactory;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.PorcentajeTipo;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarCursosAction implements Action {

	@Override
	public void execute() throws Exception {
		List<Curso> cursos = ServiceFactory.getAdminService().listarCursos();
		if (cursos.size() == 0) {
			Console.println("No hay cursos que listar.");
		}
		for (Curso curso : cursos) {
			Console.println(curso.getCodigo() + " || " + curso.getNombre()
					+ " || " + curso.getNumHoras() + " horas || "
					+ curso.getDescripcion());
			Set<PorcentajeTipo> porcentajeTipos = curso.getPorcentajeTipo();
			for (PorcentajeTipo porcentajeTipo : porcentajeTipos) {
				Console.println("\t-" + porcentajeTipo.getTipo().getNombre()
						+ " || " + porcentajeTipo.getPorcentaje() + "%");
			}
		}

	}

}
