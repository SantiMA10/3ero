package uo.ri.amp.conf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uo.ri.common.BusinessException;

public class SantiUtil {
	
	/**
	 * Metodo que trasnforma el string introducido por el usuario en un objeto data de java
	 * @param string con la fecha introducida por el usuario
	 * @return Date con la fecha del string de usuario
	 * @throws BusinessException si el usuario comete algun error al introducir la fecha
	 */
	public static Date checkFormatoFecha(String source) throws BusinessException{
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha;
		try {
			formateador.setLenient(false);
			fecha = formateador.parse(source);
		} catch (ParseException e) {
			throw new BusinessException("Formato de la fecha incorrecto.");
		}
		return fecha;
	}
	
	/**
	 * Metodo que comprueba que el string pasado no es vacio
	 * @param source string a comrpobar
	 * @return el mismo string que recibe
	 * @throws BusinessException si el string esta vacio
	 */
	public static String checkStringCorrecto(String source) throws BusinessException{
		if(source.length() == 0)
			throw new BusinessException("No puedes dejar ese campo en vacio.");
		
		return source;
	}
	
	/**
	 * Metodo que comprueba que lo pasado es un int
	 * @param source string con el int introducido por el usuario
	 * @return un int con el contenido del string
	 * @throws BusinessException si no es un numero
	 */
	public static int checkNumero(String source) throws BusinessException{
		checkStringCorrecto(source);
		int resultado;
		try{
			resultado = Integer.parseInt(source);
		} catch(NumberFormatException e){
			throw new BusinessException("Eso no es un numero");
		}
		return resultado;
	}
	
	/**
	 * Metodo que cambia el formato de la fecha del de HSQLDB(yyyy-MM-dd) al que introduce el usuario(dd/MM/yyyy)
	 * @param string con la fecha en el formato de HSQLDB
	 * @return string con la fecha en del usuario
	 * @throws BusinessException si hay algun error al cambiar el formato, que no deberia
	 */
	public static String formatearFecha(String source) throws BusinessException{
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		String fechaConFormato = "";
		try {
			formateador.setLenient(false);
			fecha = formateador.parse(source);
			formateador.applyPattern("dd/MM/yyyy");
			fechaConFormato = formateador.format(fecha);
		} catch (ParseException e) {
			throw new BusinessException("Formato de la fecha incorrecto.");
		}
		return fechaConFormato;
	}

}
