package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.AveriasGateway;

public class AveriasGatewayImpl implements AveriasGateway{
	
	private static final String SQL_LISTAR_AVERIAS = "select * from taverias";
	private static final String SQL_BUSCAR_AVERIAS_ID = "select * from taverias where id=?";
	//private static final String SQL_INSERTAR_AVERIA = "";
	private static final String SQL_ACTUALIZAR_AVERIA = "update from taverias set descripcion=?, fecha=?, importe=?, status=?, factura_id=?, mecanico_id=?, vehiculo_id=? where id=?";
	//private static final String SQL_BORRAR_AVERIA = "delete form taveria where id = ?";
	
	private Connection connection;

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Map<String, Object> findById(long id) {
		Map<String, Object> averia = new HashMap<String, Object>();
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			pre = connection.prepareCall(SQL_BUSCAR_AVERIAS_ID);
			pre.setLong(1, id);
			
			rs = pre.executeQuery();
			
			while(rs.next()){
				averia.put("id", rs.getLong("id"));
				averia.put("descripcion", rs.getString("descripcion"));
				averia.put("fecha", rs.getDate("fecha"));
				averia.put("importe", rs.getDouble("importe"));
				averia.put("status", rs.getString("status"));
				averia.put("factura_id", rs.getLong("factura_id"));
				averia.put("mecanico_id", rs.getLong("mecanico_id"));
				averia.put("vehiculo_id", rs.getLong("vehiculo_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return averia;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<Map<String, Object>> averias = new ArrayList<Map<String,Object>>();
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			pre = connection.prepareCall(SQL_LISTAR_AVERIAS);
			
			rs = pre.executeQuery();
			
			while(rs.next()){
				Map<String, Object> averia = new HashMap<String, Object>();

				averia.put("id", rs.getLong("id"));
				averia.put("descripcion", rs.getString("descripcion"));
				averia.put("fecha", rs.getDate("fecha"));
				averia.put("importe", rs.getDouble("importe"));
				averia.put("status", rs.getString("status"));
				averia.put("factura_id", rs.getLong("factura_id"));
				averia.put("mecanico_id", rs.getLong("mecanico_id"));
				averia.put("vehiculo_id", rs.getLong("vehiculo_id"));
				
				averias.add(averia);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return averias;
	}

	@Override
	public void save(Map<String, Object> averia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Map<String, Object> averia) {
		PreparedStatement pre = null;
		
		try {
			pre = connection.prepareCall(SQL_ACTUALIZAR_AVERIA);
			pre.setString(1, (String) averia.get("descripcion"));
			pre.setDate(2, (Date) averia.get("fecha"));
			pre.setDouble(3, (double) averia.get("importe"));
			pre.setString(4, (String) averia.get("status"));
			pre.setLong(5, (long) averia.get("factura_id"));
			pre.setLong(6, (long) averia.get("mecanico_id"));
			pre.setLong(7, (long) averia.get("vehiculo_id"));
			pre.setLong(8, (long) averia.get("id"));
			
			pre.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Jdbc.close(pre);
		}
		
		
	}

}
