package uo.ri.ui.cash;

import uo.ri.ui.cash.action.*;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Caja de Taller", null },
			{ "Buscar reparaciones no facturadas de un cliente", NotYetImplementedAction.class }, 
			{ "Buscar reparación por matrícula", 	NotYetImplementedAction.class }, 
			{ "Facturar reparaciones", 				FacturarReparacionesAction.class },
			{ "Liquidar factura ", 					NotYetImplementedAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
