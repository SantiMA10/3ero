package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface TipoVehiculoGateway {
	
	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	/**
	 * Metodo que lista los tipos de vehiculo
	 * @return Una lista de maps con todos los datos de cada tipo de vehiculo
	 */
	public List<Map<String, Object>> listar();

}
