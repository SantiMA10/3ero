package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

public class ListMechanics {
	

	public List<Map<String, Object>> execute() throws BusinessException{
		
		List<Map<String, Object>> lista;
		
		try {
			MecanicosGateway mG = PersistenceFactory.getMecanicosGateway();
			Connection c = Jdbc.getConnection();
			mG.setConnection(c);
			
			lista = mG.findAll();
			
			c.close();
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		}
		
		return lista;
		
	}
	
}
