package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface AveriasGateway {
	
	public void setConnection(Connection connection);
	public Map<String, Object> findById(long id);
	public List<Map<String, Object>> findAll();
	public void save(Map<String, Object> averia);
	public void delete(long id);
	public void update(Map<String, Object> averia);

}
