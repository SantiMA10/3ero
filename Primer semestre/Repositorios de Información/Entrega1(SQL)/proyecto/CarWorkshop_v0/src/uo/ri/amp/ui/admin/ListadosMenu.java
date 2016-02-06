package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.ListadoFormacionMecanicoAction;
import uo.ri.amp.ui.admin.action.ListadoHorasAsistenciaTipoVehiculoAction;
import alb.util.menu.BaseMenu;

public class ListadosMenu  extends BaseMenu {

	public ListadosMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador > Listados", null },
			{ "Formacion de un mecancio", 	ListadoFormacionMecanicoAction.class },
			{ "Horas de asistencia por tipo de vehiculo", 	ListadoHorasAsistenciaTipoVehiculoAction.class }

		};
	}

}