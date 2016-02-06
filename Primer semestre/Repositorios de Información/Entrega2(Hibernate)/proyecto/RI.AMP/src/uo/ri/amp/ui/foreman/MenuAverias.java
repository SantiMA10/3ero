package uo.ri.amp.ui.foreman;

import uo.ri.amp.ui.foreman.action.AltaAveriaAction;
import uo.ri.amp.ui.foreman.action.AsignarAveriaAction;
import uo.ri.amp.ui.foreman.action.BajaAveriaAction;
import uo.ri.amp.ui.foreman.action.HistorialAveriasAction;
import uo.ri.amp.ui.foreman.action.ModificarAveriaAction;
import alb.util.menu.BaseMenu;

public class MenuAverias extends BaseMenu {

	public MenuAverias() {
		menuOptions = new Object[][] {
				{ "Menu jefe de taller > Averias", null },
				{ "Registrar", AltaAveriaAction.class },
				{ "Modificar", ModificarAveriaAction.class },
				{ "Eliminar", BajaAveriaAction.class },
				{ "Ver historial del vehiculo", HistorialAveriasAction.class },
				{ "Asignar", AsignarAveriaAction.class }, };
	}
}
