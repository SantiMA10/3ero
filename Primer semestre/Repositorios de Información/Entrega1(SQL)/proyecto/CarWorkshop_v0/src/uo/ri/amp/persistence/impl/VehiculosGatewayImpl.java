package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.VehiculosGateway;
import uo.ri.common.BusinessException;

public class VehiculosGatewayImpl implements VehiculosGateway{
	
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Map<String, Object> findByMatricula(String matricula) throws BusinessException {
		Map<String, Object> vehiculo = new HashMap<String, Object>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_VEHICULOS_OBTENER"));
			pre.setString(1, matricula);
			rs = pre.executeQuery();
			
			if(rs.next()){
				vehiculo.put("id", rs.getLong("id"));
				vehiculo.put("marca", rs.getString("marca"));
				vehiculo.put("matricula", rs.getString("matricula"));
				vehiculo.put("modelo", rs.getString("modelo"));
				vehiculo.put("num_averias", rs.getInt("num_averias"));
			}
			
			if(vehiculo.isEmpty()){
				throw new BusinessException("No hay ningun vehiculo con esa matricula.");
			}
			
		} catch(SQLException e){
			
		} finally {
			Jdbc.close(rs, pre);
		}
		
		return vehiculo;
	}

	@Override
	public void incrementarNumAverias(long id) throws BusinessException {
		PreparedStatement pre = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_VEHICULOS_INCREMENTAR_NUMAVERIAS"));
			pre.setLong(1, id);
			
			pre.executeUpdate();
		} catch(SQLException e){
		} finally {
			Jdbc.close(pre);
		}
		
	}

	@Override
	public void decrementarNumAverias(long id) throws BusinessException {
		PreparedStatement pre = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_VEHICULOS_DECREMENTAR_NUMAVERIAS"));
			pre.setLong(1, id);
			
			pre.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			Jdbc.close(pre);
		}
		
	}

}
