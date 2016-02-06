package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.*;

public interface CertificadosGateway {
	
	/**
	 * Metodo que asigna la conexion a la bd al gate wat
	 * @param c Connection
	 */
	public void setConnection(Connection c);
	
	/**
	 * Metodo que devuelve una lista con el tipo de vehiculo y las horas de cada mecanico en ese tipo
	 * @return lista con el tipo de vehiculo y las horas de cada mecanico en ese tipo
	 */
	public List<Map<String, Object>> listaHorasPorMecanicoYTipo();
	
	/**
	 * Metodo que inserta el certificado en la base de datos
	 * @param certificado map con todos los datos del certificado
	 */
	public void save(Map<String, Object> certificado);

}
