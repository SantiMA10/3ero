package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AsistenciaGateway {
	
	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	
	/**
	 * Metodo que inserta la asistencia en la base de datos
	 * @param asistencia un map con todos los datos
	 * @throws BusinessException si ya ha asistido a ese curso
	 */
	public void save(Map<String, Object> asistencia) throws BusinessException;
	
	/**
	 * Metodo que actualiza una asistencia 
	 * @param asistencia un map con todos los datos
	 * @throws BusinessException si no hay asistencia para modificarla
	 */
	public void update(Map<String, Object> asistencia) throws BusinessException;
	
	/**
	 * Metodo que devuelve true si hay asistencias a un curso, false en caso contrario
	 * @param codigo_curso del cual se quiere saber si hay asistencia
	 * @return true si hay asistencias a un curso, false en caso contrario
	 * @throws BusinessException
	 */
	public boolean isAsistencia(String codigo_curso) throws BusinessException;
	
	/**
	 * Metodo que borra la asistencia de un mecanico
	 * @param codigo_curso 
	 * @param id_mecanico
	 * @throws BusinessException si falla algo
	 */
	public void borrarAsistecia(String codigo_curso, int id_mecanico) throws BusinessException;
	
	/**
	 * Metodo que lista la asistencia a un curso
	 * @param codigo_curso
	 * @return lista con todas las asistencias a un curso
	 * @throws BusinessException
	 */
	public List<Map<String, Object>> listar(String codigo_curso) throws BusinessException;
	
	/**
	 * Metodo que lista las asistencias a cursos de un mecanico
	 * @param idMecanico del mecanico
	 * @return lista con todas las asistencias del mecanico
	 * @throws BusinessException
	 */
	public List<Map<String, Object>> listarPorMecanico(long idMecanico) throws BusinessException;
	
	/**
	 * Metodo que devuelve las horas totales de asistencia a cursos de un mecanio
	 * @param id del mecanico
	 * @return double con las horas totales de asistencia
	 */
	public double asistenciaTotal(long id);
	
	/**
	 * Metodo que devuelve la asistencia total de un mecanico para cursos de cada tipo de vehiculo
	 * @param id del mecanico
	 * @return lista con los tipos y las horas de asistencia para cada un de los tipos
	 */
	public List<Map<String, Object>> asistenciaTotalACursoPorTipo(long id);
	
	/**
	 * Metodo que devuelve la asistencia total a curso de un tipo de vehiculo
	 * @param id del tipo de vehiculo
	 * @return lista con las horas por cada tipo de vehiculo
	 */
	public List<Map<String, Object>> asistenciaPorTipo(long id);
	
}
