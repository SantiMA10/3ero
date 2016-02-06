package uo.ri.amp.ui;

import uo.ri.amp.ui.admin.MainMenuAdmin;
import uo.ri.amp.ui.foreman.MainMenuForeman;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Menu ampliacion ", null },
			{ "Administrador", 	MainMenuAdmin.class },
			{ "Jefe de taller", 	MainMenuForeman.class },

		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
