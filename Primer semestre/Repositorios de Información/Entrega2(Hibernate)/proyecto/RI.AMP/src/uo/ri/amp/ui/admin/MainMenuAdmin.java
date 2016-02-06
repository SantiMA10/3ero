package uo.ri.amp.ui.admin;

import alb.util.menu.BaseMenu;

public class MainMenuAdmin extends BaseMenu {

	public MainMenuAdmin() {
		menuOptions = new Object[][] { { "Menu administrador", null },
				{ "Gestion de cursos", MenuCursos.class },
				{ "Gestion de asitencia", MenuAsistencia.class },
				{ "Gestion de certificados", MenuCertificados.class },
				{ "Listados", MenuListados.class }, };
	}

}
