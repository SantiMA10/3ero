package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.GenerarCertificadosAction;
import alb.util.menu.BaseMenu;

public class CertificadosMenu extends BaseMenu{
	
	public CertificadosMenu() {
		menuOptions = new Object[][] { 
				{"Administrador > Gestión de certificados", null},
				
				{ "Generar certificados", GenerarCertificadosAction.class },
			};
	}

}
