package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface MecanicoGateway {
	
	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	
	/**
	 * Metodo que devuelve los datos de un mecanico pasado su id
	 * @param id del mecanico
	 * @return un map con todos los datos del mecanico
	 * @throws BusinessException si no hay mecanico con esa id
	 */
	public Map<String, Object> findById(long id) throws BusinessException;
	
	/**
	 * Metodo que devulve todos los mecanicos expertos para una averia
	 * @param idAveria para la cual se quiere obtener los expertos
	 * @return lista con todos los mecanicos
	 */
	public List<Map<String, Object>> listarMecanicosExpertos(long idAveria);
	
	/**
	 * Metodo que devuelve todos los mecanicos de la base de datos
	 * @return lista de maps con los datos de los mecanicos
	 */
	public List<Map<String, Object>> listar();

}
