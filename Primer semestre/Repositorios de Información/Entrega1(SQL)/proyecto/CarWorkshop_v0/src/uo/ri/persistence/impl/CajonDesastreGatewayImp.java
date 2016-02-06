package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.persistence.CajonDesastreGateway;

public class CajonDesastreGatewayImp implements CajonDesastreGateway{
	
	private static final String SQL_IMPORTE_REPUESTOS = 
			"select sum(s.cantidad * r.precio) " +
			"	from  TSustituciones s, TRepuestos r " +
			"	where s.repuesto_id = r.id " +
			"		and s.intervencion_averia_id = ?";
		
	private static final String SQL_IMPORTE_MANO_OBRA =
			"select sum(i.minutos * tv.precioHora / 60) " +
			"	from TAverias a, TIntervenciones i, TVehiculos v, TTiposVehiculo tv" +
			"	where i.averia_id = a.id " +
			"		and a.vehiculo_id = v.id" +
			"		and v.tipo_id = tv.id" +
			"		and a.id = ?" +
			"		and a.status = 'TERMINADA'";
	
	Connection connection;

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public double consultaImporteRepuestos(Long idAveria) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connection.prepareStatement(SQL_IMPORTE_REPUESTOS);
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}
			
			return rs.getDouble(1);
		} catch (Exception e) {
		} finally {
			Jdbc.close(rs, pst);
		}
		return idAveria; 
	}

	@Override
	public double consultaImporteManoObra(Long idAveria) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connection.prepareStatement(SQL_IMPORTE_MANO_OBRA);
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}
			
			return rs.getDouble(1);
			
		} catch (Exception e) {
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return idAveria;
		
	}

}
