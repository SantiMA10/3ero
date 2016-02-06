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
import uo.ri.amp.persistence.*;
import uo.ri.common.BusinessException;

public class CursosGatewayImpl implements CursosGateway{
	
	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
		
	}

	@Override
	public void save(Map<String, Object> curso) throws BusinessException {
		
		PreparedStatement pre = null;
		
		try {
			pre = c.prepareStatement(Conf.get("SQL_CURSO_INSERTAR"));
			pre.setString(1, (String) curso.get("codigo"));
			pre.setString(2, (String) curso.get("nombre"));
			pre.setString(3, (String) curso.get("descripcion"));
			pre.setInt(4, (int) curso.get("numeroHoras"));

			if(pre.executeUpdate() != 1){
				
				throw new BusinessException("Curso no insertado");
				
			}
			
		} catch (SQLException e) {
			int codigoError = e.getErrorCode();
			
			if(codigoError == -104){
				throw new BusinessException("Ya existe un curso con ese codigo.");
			}
		} finally{
			Jdbc.close(pre);
		}
		
	}

	@Override
	public List<Map<String, Object>> list() throws BusinessException {
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		List<Map<String, Object>> cursos = new ArrayList<Map<String,Object>>();
		
		try {
			pre = c.prepareStatement(Conf.get("SQL_CURSO_LISTAR"));
			rs = pre.executeQuery();
			
			while(rs.next()){
				Map<String, Object> curso = new HashMap<String, Object>();
				curso.put("codigo", rs.getString("codigo"));
				curso.put("nombre", rs.getString("nombre"));
				curso.put("descripcion", rs.getString("descripcion"));
				curso.put("numeroHoras", rs.getInt("numHoras"));
				
				cursos.add(curso);
			}
			
		} catch (SQLException e) {
			
		} finally{
			Jdbc.close(rs, pre);
		}
		
		return cursos;
	}

	@Override
	public void update(Map<String, Object> curso) throws BusinessException {
		PreparedStatement pre = null;
		
		try {
			pre = c.prepareStatement(Conf.get("SQL_CURSO_ACTUALIZAR"));
			pre.setString(1, (String) curso.get("nombre"));
			pre.setString(2, (String) curso.get("descripcion"));
			pre.setInt(3, (int) curso.get("numeroHoras"));
			pre.setString(4, (String) curso.get("codigo"));


			if(pre.executeUpdate() != 1){
				
				throw new BusinessException("Curso no insertado");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Jdbc.close(pre);
		}
		
	}

	@Override
	public void delete(String codigo_curso) throws BusinessException {
		PreparedStatement pre = null;
		 
		try {

			pre = c.prepareStatement(Conf.get("SQL_CURSO_BORRAR"));
			pre.setString(1, codigo_curso);
			
			pre.executeUpdate();
			
			
		} catch (SQLException e) {
		} finally{
			Jdbc.close(pre);
		}
		
	}

	@Override
	public double horasTotalCursos(long id) {
		double asistencia = 0.0;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try{
			pre = c.prepareStatement(Conf.get("SQL_CURSO_TOTAL_MECANICO"));
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

}
