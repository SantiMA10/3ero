package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.AltaAsistenciaAction;
import uo.ri.amp.ui.admin.action.BorrarAsistenciaAccion;
import uo.ri.amp.ui.admin.action.ListarAsistenciaCursosAction;
import uo.ri.amp.ui.admin.action.ModificarAsistenciaAction;
import alb.util.menu.BaseMenu;

public class AsistenciaMenu extends BaseMenu{
	
	public AsistenciaMenu() {
		menuOptions = new Object[][] { 
				{"Administrador > Gestión de asistencia", null},
				
				{ "Añadir asistencia", AltaAsistenciaAction.class },
				{ "Modificar asistencia", ModificarAsistenciaAction.class },
				{ "Eliminar asistencia", BorrarAsistenciaAccion.class },
				{ "Listar asistencia a curso", ListarAsistenciaCursosAction.class },
			};
	}

}
