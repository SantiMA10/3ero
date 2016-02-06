package uo.ri.ui.admin;

import uo.ri.amp.ui.admin.CertificadosMenu;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gestión de mecánicos", 			MecanicosMenu.class }, 
			{ "Gestión de repuestos", 			RepuestosMenu.class },
			{ "Gestión de tipos de vehículo", 	TiposVehiculoMenu.class },
			{ "Gestión certificados", 	CertificadosMenu.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
