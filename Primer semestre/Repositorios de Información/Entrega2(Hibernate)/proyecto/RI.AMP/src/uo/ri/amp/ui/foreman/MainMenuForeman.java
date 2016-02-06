package uo.ri.amp.ui.foreman;

import alb.util.menu.BaseMenu;

public class MainMenuForeman extends BaseMenu {

	public MainMenuForeman() {
		menuOptions = new Object[][] { { "Menu jefe de taller", null },
				{ "Gestion de averias", MenuAverias.class }, };
	}

}
