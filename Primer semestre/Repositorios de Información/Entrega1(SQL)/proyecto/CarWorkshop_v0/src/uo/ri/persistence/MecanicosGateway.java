package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {
	
	public void setConnection(Connection connection);
	public Map<String, Object> findById(Long id);
	public List<Map<String, Object>>findAll();
	public void update(Long id, String nombre, String apellidos);
	public void save(String nombre, String apellidos);
	public void delete(Long id);

}
