package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.ListarFormacionMecanicoAction;
import uo.ri.amp.ui.admin.action.ListarFormacionTipoVehiculoAction;
import alb.util.menu.BaseMenu;

public class MenuListados extends BaseMenu {

	public MenuListados() {
		menuOptions = new Object[][] {
				{ "Menu administrador > listados", null },
				{ "Formacion de un mecanico",
						ListarFormacionMecanicoAction.class },
				{ "Formacion por tipo de vehiculo",
						ListarFormacionTipoVehiculoAction.class }, };
	}
}
