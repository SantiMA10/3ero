package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.*;

public class MecanicosGatewayImpl implements MecanicosGateway{
	
	private static String SQL_INSERT_MECHANIC = "insert into TMecanicos(nombre, apellidos) values (?, ?)";
	private static String SQL_DELETE_MECHANIC = "delete from TMecanicos where id = ?";
	private static String SQL_LIST_MECHANIC = "select id, nombre, apellidos from TMecanicos";
	private static String SQL_UPDATE_MECHANIC = "update TMecanicos set nombre = ?, apellidos = ? where id = ?";
	
	private Connection c;

	@Override
	public void setConnection(Connection connection) {
		this.c = connection;
	}

	@Override
	public Map<String, Object> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {		
			pst = c.prepareStatement(SQL_LIST_MECHANIC);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("id", rs.getLong(1));
				map.put("nombre", rs.getString(2));
				map.put("apellidos", rs.getString(3));
				
				lista.add(map);
		
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		
		return lista;
	}

	@Override
	public void update(Long id, String nombre, String apellidos) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {				
			pst = c.prepareStatement(SQL_UPDATE_MECHANIC);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);
					
			pst.executeUpdate();
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public void save(String nombre, String apellidos) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			pst = c.prepareStatement(SQL_INSERT_MECHANIC);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
				
			pst.executeUpdate();
					
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public void delete(Long id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			pst = c.prepareStatement(SQL_DELETE_MECHANIC);
			pst.setLong(1, id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		
	}

}
