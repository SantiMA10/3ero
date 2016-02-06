package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface VehiculosGateway {
	
	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	
	/**
	 * Metodo que devuelve un vehiculo buscandolo por matricula
	 * @param matricula del vehiculo a buscar
	 * @return un map todos los datos del vehiculo
	 * @throws BusinessException si no hay vehiculos con esa matricula
	 */
	public Map<String, Object> findByMatricula(String matricula) throws BusinessException;
	
	/**
	 * Metodo que incrementa el num_averia del vehiculo cuyo id sea el pasado como parametro
	 * @param id long, del vehiculo
	 * @throws BusinessException si falla
	 */
	public void incrementarNumAverias(long id) throws BusinessException;
	
	/**
	 * Metodo que decrementa el num_averia del vehiculo cuyo id sea el pasado como parametro
	 * @param id long, del vehiculo
	 * @throws BusinessException si falla
	 */
	public void decrementarNumAverias(long id) throws BusinessException;


}
