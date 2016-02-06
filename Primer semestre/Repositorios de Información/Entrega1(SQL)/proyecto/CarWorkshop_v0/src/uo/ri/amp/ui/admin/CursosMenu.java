package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.*;
import alb.util.menu.BaseMenu;

public class CursosMenu extends BaseMenu{
	
	public CursosMenu() {
		menuOptions = new Object[][] { 
				{"Administrador > Gestión de cursos", null},
				
				{ "Alta curso", AltaCursoAction.class },
				{ "Baja curso", BajaCursoAction.class },
				{ "Modificar curso", ModificarCursoAction.class },
				{ "Listar cursos", ListarCursosAction.class },
			};
	}

}
