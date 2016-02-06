package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.CertificadosGateway;

public class CertificadosGatewayImpl implements CertificadosGateway{
	
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public List<Map<String, Object>> listaHorasPorMecanicoYTipo() {
		List<Map<String, Object>> listado = new ArrayList<Map<String,Object>>();
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_HORAS_TIPO_MECANICO"));
			rs = pre.executeQuery();
			
			while(rs.next()){
				Map<String, Object> elemento = new HashMap<String, Object>();
				elemento.put("horas", rs.getDouble(1));
				elemento.put("id_tipo", rs.getLong(2));
				elemento.put("id_mecanico", rs.getLong(3));
				listado.add(elemento);
			}
			
		} catch(SQLException e){
		} finally{
			Jdbc.close(rs, pre);
		}
		return listado;
	}

	@Override
	public void save(Map<String, Object> certificado) {
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

		PreparedStatement pre = null;		
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_INSERTAR_CERTIFICADO"));
			pre.setLong(1, (long) certificado.get("id_tipo"));
			pre.setLong(2, (long) certificado.get("id_mecanico"));
			pre.setString(3, formateador.format((Date)certificado.get("fecha")));
			
			pre.executeUpdate();
			
		} catch(SQLException e){
						
		} finally{
			Jdbc.close(pre);
		}
		
		
	}

}
