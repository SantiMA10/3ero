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
import uo.ri.amp.persistence.AveriaGateway;
import uo.ri.common.BusinessException;

public class AveriaGatewayImpl implements AveriaGateway{
	
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public void registrarAveria(long idVehiculo, String descripcion, Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement pre = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_AVERIA_INSERTAR"));
			pre.setLong(1, getSiguienteID());
			pre.setString(2, descripcion);
			pre.setString(3, formateador.format(fecha));
			pre.setString(4, "ABIERTA");
			pre.setLong(5, idVehiculo);

			pre.executeUpdate();
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(pre);
		}
		
	}

	@Override
	public void modificarAveria(long id, long idVehiculo, String descripcion, Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement pre = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_AVERIA_ACTUALIZAR"));
			pre.setString(1, descripcion);
			pre.setString(2, formateador.format(fecha));
			pre.setLong(3, idVehiculo);
			pre.setLong(4, id);

			pre.executeUpdate();
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(pre);
		}
		
	}

	@Override
	public void eliminarAveria(long id) {
		PreparedStatement pre = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_AVERIA_ELIMINAR"));
			pre.setLong(1, id);

			pre.executeUpdate();
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(pre);
		}
		
	}

	@Override
	public List<Map<String, Object>> listarHistorialAverias(long idVehiculo) {
		List<Map<String, Object>> historial = new ArrayList<Map<String,Object>>();
		PreparedStatement pre = null;
		ResultSet rs = null;
				
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_AVERIA_LISTAR"));
			pre.setLong(1, idVehiculo);
			rs = pre.executeQuery();
			
			while(rs.next()){
				Map<String, Object> averia = new HashMap<String, Object>();
				averia.put("id", rs.getLong("id"));
				averia.put("descripcion", rs.getString("descripcion"));
				averia.put("fecha", rs.getString("fecha"));
				averia.put("importe", rs.getDouble("importe"));
				averia.put("status", rs.getString("status"));
				averia.put("factura_id", rs.getString("factura_id"));
				averia.put("mecanico_id", rs.getString("mecanico_id"));
				historial.add(averia);
			}
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(rs, pre);
		}
		
		return historial;
	}

	@Override
	public void asignarAveria(long idAveria, long idMecanico) throws BusinessException {
		PreparedStatement pre = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_AVERIA_ASIGNAR"));
			pre.setLong(1, idMecanico);
			pre.setString(2, "ASIGNADA");
			pre.setLong(3, idAveria);

			
			pre.executeUpdate();
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(pre);
		}
		
	}
	
	private long getSiguienteID(){
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_AVERIA_MAX_ID"));
			rs = pre.executeQuery();
			if(rs.next()){
				return rs.getLong(1) + 1;
			}
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(rs, pre);
		}
		
		return 0L;
	}

	@Override
	public long findVehiculoIDbyAveriaId(long idAveria) throws BusinessException {
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			
			pre = c.prepareStatement(Conf.get("SQL_AVERIA_IDVEHICULO"));
			pre.setLong(1, idAveria);
			
			rs = pre.executeQuery();
			if(rs.next()){
				return rs.getLong("vehiculo_id");
			}
			
		} catch(SQLException e){
		} finally {
			Jdbc.close(rs, pre);
		}
		
		return 0L;
	}

}
