package uo.ri.amp.ui.foreman;

import uo.ri.amp.ui.foreman.action.AsignarAveriaAction;
import uo.ri.amp.ui.foreman.action.EliminarAveriaAction;
import uo.ri.amp.ui.foreman.action.ListarHistorialAveriasAction;
import uo.ri.amp.ui.foreman.action.ModificarAveriaAction;
import uo.ri.amp.ui.foreman.action.RegistrarAveriaAction;
import alb.util.menu.BaseMenu;

public class AveriasMenu extends BaseMenu{
	
	public AveriasMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de taller > Gestión averias", null },
			{ "Registrar averia", 	RegistrarAveriaAction.class },
			{ "Modificar averia", 	ModificarAveriaAction.class },
			{ "Eliminar averia", 	EliminarAveriaAction.class },
			{ "Historial de averias de un vehiculo", 	ListarHistorialAveriasAction.class },
			{ "Asignar averia", 	AsignarAveriaAction.class },

		};
	}

}
