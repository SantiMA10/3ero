package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.MecanicoGateway;
import uo.ri.common.BusinessException;

public class MecanicosGatewayImpl implements MecanicoGateway{
	
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Map<String, Object> findById(long id) throws BusinessException {
		PreparedStatement pre = null;
		ResultSet rs = null;
		Map<String, Object> mecanico = new HashMap<String, Object>();
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_MECANICO_BUSCAR_ID"));
			pre.setLong(1, id);
			
			rs = pre.executeQuery();
			rs.next();
			
			mecanico.put("id", rs.getLong("id"));
			mecanico.put("nombre", rs.getString("nombre"));
			mecanico.put("apellidos", rs.getString("apellidos"));
			
		} catch(SQLException e){
		} finally{
			Jdbc.close(pre);
		}
		
		return mecanico;
	}
	
	@Override
	public List<Map<String, Object>> listarMecanicosExpertos(long idAveria) {
		List<Map<String, Object>> mecanicos = new ArrayList<Map<String,Object>>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_MECANICOS_LISTAR_EXPERTOS"));
			pre.setLong(1, idAveria);
			
			rs = pre.executeQuery();
			
			while(rs.next()){
				Map<String, Object> mecanico = new HashMap<String, Object>();
				mecanico.put("id", rs.getLong("id"));
				mecanico.put("apellidos", rs.getString("apellidos"));
				mecanico.put("nombre", rs.getString("nombre"));
				mecanicos.add(mecanico);
			}
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(rs, pre);
		}
		return mecanicos;
	}

	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String, Object>> mecanicos = new ArrayList<Map<String,Object>>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_MECANICO_LISTAR"));
			
			rs = pre.executeQuery();
			
			while(rs.next()){
				Map<String, Object> mecanico = new HashMap<String, Object>();
				mecanico.put("id", rs.getLong("id"));
				mecanico.put("apellidos", rs.getString("apellidos"));
				mecanico.put("nombre", rs.getString("nombre"));
				mecanicos.add(mecanico);
			}
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(rs, pre);
		}
		return mecanicos;
	}

}
