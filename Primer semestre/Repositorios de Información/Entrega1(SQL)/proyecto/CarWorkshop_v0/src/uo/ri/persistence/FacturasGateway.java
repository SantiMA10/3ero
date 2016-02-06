package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface FacturasGateway {

	public void setConnection(Connection connection);
	public long getLastInvoiceNumber();
	public Map<String, Object> findById(long id);
	public List<Map<String, Object>> findAll();
	public long save(Map<String, Object> factura);
	public void delete(long id);
	public void update(Map<String, Object> factura);
	public Map<String, Object> findByNumber(long number);
	
}
