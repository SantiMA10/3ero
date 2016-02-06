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
import uo.ri.amp.persistence.TipoVehiculoGateway;

public class TipoVehiculoGatewayImpl implements TipoVehiculoGateway{
	
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String, Object>> resultado = new ArrayList<Map<String,Object>>();
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_TIPOVEHICULO_LISTAR"));
			rs = pre.executeQuery();
			
			while(rs.next()){
				Map<String,Object> tipo = new HashMap<String, Object>();
				tipo.put("id", rs.getLong("id"));
				tipo.put("nombre", rs.getString("nombre"));
				tipo.put("preciohora", rs.getDouble("preciohora"));
				tipo.put("numhorasexperto", rs.getInt("numhorasexperto"));
				resultado.add(tipo);
			}
			
			
		} catch(SQLException e){
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return resultado;
		
	}

}
