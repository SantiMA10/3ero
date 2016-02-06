package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CursosGateway {

	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	
	/**
	 * Metodo que inserta un curso en la base de datos
	 * @param curso todos los datos del curso
	 * @throws BusinessException error de PK repetida
	 */
	public void save(Map<String, Object> curso) throws BusinessException;
	
	/**
	 * Metodo que devuelve todos los cursos de la base de datos
	 * @return lista con todos los curso
	 * @throws BusinessException si algo falla
	 */
	public List<Map<String, Object>> list() throws BusinessException;
	
	/**
	 * Metodo que actualiza los datos de un curso
	 * @param curso map con todos los datos de un curso
	 * @throws BusinessException
	 */
	public void update(Map<String, Object> curso) throws BusinessException;
	
	/**
	 * Metodo que borra un curso
	 * @param codigo_curso a borrar
	 * @throws BusinessException si falla por alguna FK
	 */
	public void delete(String codigo_curso) throws BusinessException;
	
	/**
	 * Metodo que devuelve las horas totales de un curso para un mecanico
	 * @param id del mecanico
	 * @return el numero de horas
	 */
	public double horasTotalCursos(long id);

}
