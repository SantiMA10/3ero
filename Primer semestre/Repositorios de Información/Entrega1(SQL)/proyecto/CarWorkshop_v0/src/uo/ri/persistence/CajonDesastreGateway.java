package uo.ri.persistence;

import java.sql.Connection;

public interface CajonDesastreGateway {
	
	public void setConnection(Connection connection);
	public double consultaImporteRepuestos(Long idAveria);
	public double consultaImporteManoObra(Long idAveria);

}
