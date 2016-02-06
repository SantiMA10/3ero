package uo.ri.amp.ui.admin;

import alb.util.menu.BaseMenu;

public class MainMenuAdmin extends BaseMenu {

	public MainMenuAdmin() {
		menuOptions = new Object[][] { 
			{ "Menu ampliacion > Administrador", null },
			{ "Gestión cursos", 	CursosMenu.class },
			{ "Gestión asistencia", 	AsistenciaMenu.class },
			{ "Gestión certificados", 	CertificadosMenu.class },
			{ "Listados", 	ListadosMenu.class },

		};
	}

}
