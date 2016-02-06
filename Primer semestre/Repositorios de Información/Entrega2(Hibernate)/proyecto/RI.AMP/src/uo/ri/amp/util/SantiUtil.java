package uo.ri.amp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import alb.util.date.DateUtil;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.Calificacion;

public class SantiUtil {

	/**
	 * Lanza una excepcion si el String pasado esta vacio
	 * 
	 * @param string
	 *            ha comprobar que no este vacio
	 * @return el mismo String que recibe
	 * @throws BusinessException
	 */
	public static String checkNoVacio(String string) throws BusinessException {
		string = string.trim();
		if (string.isEmpty()) {
			throw new BusinessException("No puedes dejar ese campo vacio");
		}
		return string;
	}

	/**
	 * Lanza una excepcion si el String pasado esta vacio o no es un int
	 * 
	 * @param string
	 *            ha comprobar que no esta vacio o sea un int
	 * @return un int
	 * @throws BusinessException
	 */
	public static int checkInt(String string) throws BusinessException {
		checkNoVacio(string);
		int numero = 0;
		try {
			numero = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new BusinessException("Eso no es numero.");
		}
		return numero;
	}

	/**
	 * Lanza una excepcion si el String pasado no es un int, esta vacio o es
	 * negativo.
	 * 
	 * @param string
	 *            ha comprobar
	 * @return un int
	 * @throws BusinessException
	 */
	public static int checkIntPositivo(String string) throws BusinessException {
		checkNoVacio(string);
		return checkIntPositivo(checkInt(string));
	}

	/**
	 * Lanza una excepcion si el int es negativo.
	 * 
	 * @param string
	 *            ha comprobar
	 * @return el int recibido
	 * @throws BusinessException
	 */
	public static int checkIntPositivo(int numero) throws BusinessException {
		if (numero < 0) {
			throw new BusinessException("No se admiten numeros negativos");
		}
		return numero;
	}

	/**
	 * Lanza una excepcion si el String pasado esta vacio o no es un Long
	 * 
	 * @param string
	 *            ha comprobar que no esta vacio o sea un long
	 * @return un Long
	 * @throws BusinessException
	 */
	public static Long checkLong(String string) throws BusinessException {
		checkNoVacio(string);
		Long numero = 0L;
		try {
			numero = Long.parseLong(string);
		} catch (NumberFormatException e) {
			throw new BusinessException("Eso no es numero.");
		}
		return numero;
	}

	/**
	 * Lanza una excepcion si el String pasado esta vacio o no es una fecha
	 * correcta
	 * 
	 * @param string
	 *            ha comprobar que no esta vacio o sea una fecha correcta
	 * @return Date
	 * @throws BusinessException
	 */
	public static Date checkFecha(String string) throws BusinessException {
		checkNoVacio(string);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha;
		try {
			formateador.setLenient(false);
			fecha = formateador.parse(string);
		} catch (ParseException e) {
			throw new BusinessException("Formato de la fecha incorrecto.");
		}
		return fecha;
	}

	/**
	 * Compruba que el porcentaje, pasada como int, sea menor al 100%
	 * 
	 * @param num
	 *            int con el porcentaje
	 * @return el int con el porcentaje
	 * @throws BusinessException
	 */
	public static int checkPorcentaje(int num) throws BusinessException {
		num = checkIntPositivo(num);
		if (num > 100) {
			throw new BusinessException(
					"El porcentaje no puede superar el 100%");
		}
		return num;
	}

	/**
	 * Compruba que el porcentaje, pasa como string, sea menor al 100%
	 * 
	 * @param string
	 *            que contiene el porcentaje
	 * @return int con el porcentaje
	 * @throws BusinessException
	 *             en caso de error
	 */
	public static int checkPorcentaje(String string) throws BusinessException {
		return checkPorcentaje(checkInt(string));
	}

	/**
	 * Comprueba que la asistencia y/o la calificacion son correctas
	 * 
	 * @param asistencia
	 *            en porcentaje
	 * @param calificacion
	 *            la calificacion en string
	 * @return Calificacion correspondiente
	 * @throws BusinessException
	 */
	public static Calificacion checkCalificacion(int asistencia,
			String calificacion) throws BusinessException {
		if (calificacion.equals("apto") && asistencia < 85) {
			throw new BusinessException(
					"No puede ser apto con menos del 85% de la asistencia.");
		}
		if (!calificacion.equals("apto") && !calificacion.equals("no apto")) {
			throw new BusinessException(
					"La calificacion debe ser 'apto' o 'no apto'.");
		}
		return calificacion.equals("apto") ? Calificacion.APTO
				: Calificacion.NO_APTO;
	}

	/**
	 * Comprueba que se cumplen los criterios de calificacion
	 * 
	 * @param asistencia
	 *            al curso
	 * @param calificacion
	 *            obtenida
	 * @throws BusinessException
	 *             si no se cumple el criterio
	 */
	public static void checkCalificacion(int asistencia,
			Calificacion calificacion) throws BusinessException {
		if (calificacion.equals(Calificacion.APTO) && asistencia < 85) {
			throw new BusinessException(
					"No puede ser apto con menos del 85% de la asistencia.");
		}
	}

	/**
	 * Comprueba que la fecha de fin es posterior a la fecha de inicio.
	 * 
	 * @param fechaInicio
	 *            date
	 * @param fechaFin
	 *            date
	 * @throws BusinessException
	 */
	public static void checkFechaPosterior(Date fechaInicio, Date fechaFin)
			throws BusinessException {
		if (DateUtil.isAfter(fechaInicio, fechaFin)) {
			throw new BusinessException(
					"La fecha de inicio debe ser anterior a la fecha de fin.");
		}
	}

}
