package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.GenerarCertificadosAction;
import alb.util.menu.BaseMenu;

public class MenuCertificados extends BaseMenu {

	public MenuCertificados() {
		menuOptions = new Object[][] {
				{ "Menu administrador > certificados", null },
				{ "Generar", GenerarCertificadosAction.class }, };
	}

}
