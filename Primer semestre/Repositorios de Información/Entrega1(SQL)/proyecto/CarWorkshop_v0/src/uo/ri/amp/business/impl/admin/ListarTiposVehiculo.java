package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.TipoVehiculoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ListarTiposVehiculo {
	
	public List<Map<String, Object>> execute() throws BusinessException{
		Connection c = null;
		
		try {
			
			TipoVehiculoGateway tG = PersistenceFactory.getTipoVehiculoGateway();
			
			c = Jdbc.getConnection();
			tG.setConnection(c);
			
			List<Map<String, Object>> tiposVehiculo = tG.listar();
			
			Jdbc.close(c);
			
			return tiposVehiculo;
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		}
		
	}

}
