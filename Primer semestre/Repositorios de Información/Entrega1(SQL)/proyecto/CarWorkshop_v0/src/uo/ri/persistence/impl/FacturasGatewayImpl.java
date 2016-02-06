package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway {
	
	private static final String SQL_INSERTAR_FACTURA = 
			"insert into TFacturas(numero, fecha, iva, importe, status) " +
			"	values(?, ?, ?, ?, ?)";
	
	private static final String SQL_ULTIMO_NUMERO_FACTURA = 
			"select max(numero) from TFacturas";
	
	private static final String SQL_BORRAR_FACTURA = 
			"delete from tfacturas where numerp = ?";
	
	private static final String SQL_BUSCAR_POR_NUMBER = 
			"select * from tfacturas where numero = ?";
	
	private static final String SQL_BUSCAR_POR_ID = 
			"select * from tfacturas where id = ?";
	
	private static final String SQL_ACTUALIZAR_FACTURA = 
			"update TFacturas set numero = ? fecha = ?, iva = ?, importe = ?, status = ? " +
			"where id = ?";
	
	private static final String SQL_LISTAR_FACTURAS = "select * from tfacturas";
	
	private Connection connection;

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long getLastInvoiceNumber() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_ULTIMO_NUMERO_FACTURA);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, el siguiente
			} else {  // todavía no hay ninguna
				return 1L;
			}
		} catch(Exception e){
			throw new RuntimeException();
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Map<String, Object> findById(long id) {
		Map<String, Object> resultado = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_BUSCAR_POR_ID);
			pst.setLong(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				resultado.put("id", rs.getString("id"));
				resultado.put("fecha", rs.getDate("fecha"));
				resultado.put("importe", rs.getDouble("importe"));
				resultado.put("iva", rs.getDouble("iva"));
				resultado.put("numero", rs.getLong("numero"));
				resultado.put("status", rs.getString("status"));
			}
			
			
		} catch(Exception e){
			throw new RuntimeException();
		} finally {
			Jdbc.close(rs, pst);
		}
		return resultado;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_LISTAR_FACTURAS);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				Map<String, Object> factura = new HashMap<String, Object>();
				
				factura.put("id", rs.getString("id"));
				factura.put("fecha", rs.getDate("fecha"));
				factura.put("importe", rs.getDouble("importe"));
				factura.put("iva", rs.getDouble("iva"));
				factura.put("numero", rs.getLong("numero"));
				factura.put("status", rs.getString("status"));
				
				resultado.add(factura);
			}
			
			
		} catch(Exception e){
			throw new RuntimeException();
		} finally {
			Jdbc.close(rs, pst);
		}
		
		return resultado;
	}

	@Override
	public long save(Map<String, Object> factura) {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(SQL_INSERTAR_FACTURA);
			pst.setLong(1, (long) factura.get("numeroFactura"));
			pst.setDate(2, new java.sql.Date(((Date) factura.get("fechaFactura")).getTime()));
			pst.setDouble(3, (double) factura.get("iva"));
			pst.setDouble(4, (double) factura.get("totalConIva"));
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();
			
			return (long)findByNumber((long) factura.get("numeroFactura")).get("idTabla"); // Id de la nueva factura generada
			
		} catch(Exception e){
			throw new RuntimeException();
		} finally {
			Jdbc.close(pst);
		}
		
	}

	@Override
	public void delete(long id) {
		PreparedStatement pst = null;
		
		try{
			pst = connection.prepareStatement(SQL_BORRAR_FACTURA);
			pst.setLong(1, id);
			
			pst.executeUpdate();
		} catch(Exception e){
			throw new RuntimeException();
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public Map<String, Object> findByNumber(long number) {
		
		Map<String, Object> resultado = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_BUSCAR_POR_NUMBER);
			pst.setLong(1, number);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				resultado.put("id", rs.getString("id"));
				resultado.put("fecha", rs.getDate("fecha"));
				resultado.put("importe", rs.getDouble("importe"));
				resultado.put("iva", rs.getDouble("iva"));
				resultado.put("numero", rs.getLong("numero"));
				resultado.put("status", rs.getString("status"));
			}
			
			
		} catch(Exception e){
			throw new RuntimeException();
		} finally {
			Jdbc.close(rs, pst);
		}
		return resultado;
	}

	@Override
	public void update(Map<String, Object> factura) {
		
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(SQL_ACTUALIZAR_FACTURA);
			pst.setLong(1, (long) factura.get("numeroFactura"));
			pst.setDate(2, new java.sql.Date(((Date) factura.get("fechaFactura")).getTime()));
			pst.setDouble(3, (double) factura.get("iva"));
			pst.setDouble(4, (double) factura.get("totalConIva"));
			pst.setString(5, (String) factura.get("status"));

			pst.executeUpdate();
						
		} catch(Exception e){
			throw new RuntimeException();
		} finally {
			Jdbc.close(pst);
		}
		
	}

}
