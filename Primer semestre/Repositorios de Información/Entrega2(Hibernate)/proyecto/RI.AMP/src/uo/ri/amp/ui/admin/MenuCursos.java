package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.AltaCursoAction;
import uo.ri.amp.ui.admin.action.BajaCursoAction;
import uo.ri.amp.ui.admin.action.ListarCursosAction;
import uo.ri.amp.ui.admin.action.ModificarCursoAction;
import alb.util.menu.BaseMenu;

public class MenuCursos extends BaseMenu {

	public MenuCursos() {
		menuOptions = new Object[][] { { "Menu administrador > Cursos", null },
				{ "Alta", AltaCursoAction.class },
				{ "Baja", BajaCursoAction.class },
				{ "Modificacion", ModificarCursoAction.class },
				{ "Listado", ListarCursosAction.class }, };
	}

}
