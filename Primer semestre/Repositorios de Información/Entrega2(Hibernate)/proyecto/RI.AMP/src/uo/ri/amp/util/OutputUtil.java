package uo.ri.amp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OutputUtil {

	/**
	 * Formatea la fecha pasada a dd/MM/yyyy y la devuelve como string
	 * 
	 * @param date
	 *            a formatear
	 * @return un string con la fecha en formato dd/MM/yyyy
	 */
	public static String formatDate(Date date) {

		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		formateador.applyPattern("dd/MM/yyyy");
		String fechaConFormato = formateador.format(date);

		return fechaConFormato;
	}

}
