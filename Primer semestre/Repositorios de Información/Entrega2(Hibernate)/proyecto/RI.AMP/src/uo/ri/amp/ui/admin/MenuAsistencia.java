package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.AltaAsistenciaAction;
import uo.ri.amp.ui.admin.action.BajaAsistenciaAction;
import uo.ri.amp.ui.admin.action.ListarAsistenciaAction;
import uo.ri.amp.ui.admin.action.ModificarAsistenciaAction;
import alb.util.menu.BaseMenu;

public class MenuAsistencia extends BaseMenu {

	public MenuAsistencia() {
		menuOptions = new Object[][] {
				{ "Menu administrador > asistencia", null },
				{ "Alta", AltaAsistenciaAction.class },
				{ "Baja", BajaAsistenciaAction.class },
				{ "Modificacion", ModificarAsistenciaAction.class },
				{ "Lista asistencia a curso", ListarAsistenciaAction.class }, };
	}

}
