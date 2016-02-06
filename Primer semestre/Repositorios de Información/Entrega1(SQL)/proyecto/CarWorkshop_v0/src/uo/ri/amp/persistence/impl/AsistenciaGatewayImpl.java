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
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.common.BusinessException;

public class AsistenciaGatewayImpl implements AsistenciaGateway{
	
	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public void save(Map<String, Object> asistencia) throws BusinessException {
		PreparedStatement pre = null;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_INSERTAR"));
			pre.setString(1, (String) asistencia.get("codigo_curso"));
			pre.setInt(2, (int)asistencia.get("id_mecanico"));
			pre.setString(3, formateador.format((Date)asistencia.get("fechainicio")));
			pre.setString(4, formateador.format((Date)asistencia.get("fechafin")));
			pre.setInt(5, (int)asistencia.get("asistencia"));
			pre.setString(6, (String)asistencia.get("calificacion"));
			
			pre.executeUpdate();
			
		} catch(SQLException e){
			if(e.getErrorCode() == -177)
				throw new BusinessException("No existe nigun curso y/o mecanico con ese codigo/id.");
			if(e.getErrorCode() == -104)
				throw new BusinessException("El mecanico ya ha participado en el curso.");
		} finally{
			Jdbc.close(pre);
		}
		
	}

	@Override
	public void update(Map<String, Object> asistencia) throws BusinessException {
		PreparedStatement pre = null;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_ACTUALIZAR"));

			pre.setString(1, formateador.format((Date)asistencia.get("fechainicio")));
			pre.setString(2, formateador.format((Date)asistencia.get("fechafin")));
			pre.setInt(3, (int)asistencia.get("asistencia"));
			pre.setString(4, (String)asistencia.get("calificacion"));
			pre.setString(5, (String) asistencia.get("codigo_curso"));
			pre.setInt(6, (int)asistencia.get("id_mecanico"));
			
			if(pre.executeUpdate() == 0)
				throw new BusinessException("No existe nigun curso y/o mecanico con ese codigo/id.");
			
		} catch(SQLException e){
				
		} finally{
			Jdbc.close(pre);
		}
		
	}

	@Override
	public boolean isAsistencia(String codigo_curso) throws BusinessException {
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_DE_CURSO"));
			pre.setString(1, codigo_curso);
			
			rs = pre.executeQuery();
			return rs.next();

			
		} catch(SQLException e){			
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return false;
		
	}

	@Override
	public void borrarAsistecia(String codigo_curso, int id_mecanico) throws BusinessException {
		PreparedStatement pre = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_BORRAR"));
			pre.setString(1, codigo_curso);
			pre.setInt(2, id_mecanico);
			
			if(pre.executeUpdate() == 0)
				throw new BusinessException("No hay asistencias con ese codigo curso y/o id de mecanico.");
			
		} catch(SQLException e){
			
		} finally{
			Jdbc.close(pre);
		}
		
	}

	@Override
	public List<Map<String, Object>> listar(String codigo_curso) throws BusinessException {
		List<Map<String, Object>> listado = new ArrayList<Map<String,Object>>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_DE_CURSO"));
			pre.setString(1, codigo_curso);
			
			rs = pre.executeQuery();
			while(rs.next()){
				Map<String, Object> asistencia = new HashMap<String, Object>();
				asistencia.put("id_mecanico", rs.getString("id_mecanico"));
				asistencia.put("fechafin", rs.getString("fechafin"));
				asistencia.put("asistencia", rs.getInt("asistencia"));
				asistencia.put("calificacion", rs.getString("calificacion"));
				listado.add(asistencia);
			}

			
		} catch(SQLException e){
						
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return listado;
	}

	@Override
	public List<Map<String, Object>> listarPorMecanico(long idMecanico) throws BusinessException {
		List<Map<String, Object>> listado = new ArrayList<Map<String,Object>>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_DE_CURSO_MECANICO"));
			pre.setLong(1, idMecanico);
			
			rs = pre.executeQuery();
			while(rs.next()){
				Map<String, Object> asistencia = new HashMap<String, Object>();
				asistencia.put("codigo_curso", rs.getString("codigo_curso"));
				asistencia.put("fechafin", rs.getString("fechafin"));
				asistencia.put("asistencia", rs.getInt("asistencia"));
				asistencia.put("calificacion", rs.getString("calificacion"));
				listado.add(asistencia);
			}

			
		} catch(SQLException e){
						
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return listado;
	}
	
	public double asistenciaTotal(long id){
		double asistencia = 0.0;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_TOTAL_MECANICO"));
			pre.setLong(1, id);
			
			rs = pre.executeQuery();
			while(rs.next()){
				asistencia = rs.getDouble(2);
			}

			
		} catch(SQLException e){
						
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return asistencia;
	}
	
	public List<Map<String, Object>> asistenciaTotalACursoPorTipo(long id){
		List<Map<String, Object>> asistencias = new ArrayList<Map<String, Object>>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_TOTAL_MECANICO_TIPOS"));
			pre.setLong(1, id);
			
			rs = pre.executeQuery();
			while(rs.next()){
				Map<String, Object> asistencia = new HashMap<String, Object>();
				asistencia.put("id", rs.getLong("id_tipo"));
				asistencia.put("numhoras", rs.getDouble(4));
				asistencia.put("nombre", rs.getString("nombre"));
				asistencias.add(asistencia);
			}

			
		} catch(SQLException e){
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return asistencias;
	}

	@Override
	public List<Map<String, Object>> asistenciaPorTipo(long id) {
		List<Map<String, Object>> asistencias = new ArrayList<Map<String, Object>>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_ASISTENCIA_TIPOS"));
			pre.setLong(1, id);
			
			rs = pre.executeQuery();
			while(rs.next()){
				Map<String, Object> asistencia = new HashMap<String, Object>();
				asistencia.put("nombre", rs.getString("nombre"));
				asistencia.put("numhoras", rs.getDouble(4));
				asistencias.add(asistencia);
			}

			
		} catch(SQLException e){
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return asistencias;
	}

}
