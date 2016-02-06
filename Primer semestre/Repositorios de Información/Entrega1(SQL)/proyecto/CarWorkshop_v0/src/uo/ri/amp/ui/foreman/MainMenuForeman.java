package uo.ri.amp.ui.foreman;

import alb.util.menu.BaseMenu;

public class MainMenuForeman extends BaseMenu {

	public MainMenuForeman() {
		menuOptions = new Object[][] { 
			{ "Menu ampliacion > Jefe de taller", null },
			{ "Gestión averias", 	AveriasMenu.class },

		};
	}

}
