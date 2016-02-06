package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface PorcentajeTipoGateway {
	
	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	
	/**
	 * Metodo que inserta los porcentajes de un curso en la base de datos
	 * @param curso todos los datos del curso
	 * @throws BusinessException si falla alguna FK
	 */
	public void save(Map<String, Object> curso) throws BusinessException;
	
	/**
	 * Metodo que devuelve todos los porcentajes para un curso
	 * @param codigo del curso
	 * @return todos los porcentajes para un curso
	 * @throws BusinessException si algo falla
	 */
	public Map<String, Object> list(String codigo) throws BusinessException;
	
	/**
	 * Metodo que borra todos los porcentajes para un curso
	 * @param codigo del curso
	 * @throws BusinessException si algo falla
	 */
	public void delete(String codigo) throws BusinessException;

}
