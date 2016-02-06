package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AveriaGateway {
	
	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	
	/**
	 * Metodo que inserta una averia en la base de datos
	 * @param idVehiculo id del vehiculo que sufre la averias
	 * @param descripcion de la averia
	 * @param fecha de la averia
	 */
	public void registrarAveria(long idVehiculo, String descripcion, Date fecha);
	
	/**
	 * Metodo que modifica una averia
	 * @param id de la averia
	 * @param idVehiculo del vehiculo
	 * @param descripcion de la averia
	 * @param fecha de la averia
	 */
	public void modificarAveria(long id, long idVehiculo, String descripcion, Date fecha);
	
	/**
	 * Metodo que elimina una averia
	 * @param id de la averia
	 */
	public void eliminarAveria(long id);
	
	/**
	 * Metodo que devuelve todas las averias de un vehiculo
	 * @param idVehiculo id del vehiculo del cual quieres obtener las averias
	 * @return lista con todos los datos de las averias del vehiculo
	 */
	public List<Map<String, Object>> listarHistorialAverias(long idVehiculo);
	
	/**
	 * Metodo que asigna una averia un mecanico
	 * @param idAveria de la averia 
	 * @param idMecanico del mecanico
	 * @throws BusinessException si algo falla
	 */
	public void asignarAveria(long idAveria, long idMecanico) throws BusinessException;
	
	/**
	 * Metodo que devuelve el id del vehiculo que sufre una averia
	 * @param idAveria para la cual se quiere obtener el id del vehiculo
	 * @return id del vehiculo
	 * @throws BusinessException si no existe averia
	 */
	public long findVehiculoIDbyAveriaId(long idAveria) throws BusinessException;

}
