package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.MecanicoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ListarMecanicos {

	public List<Map<String, Object>> execute() throws BusinessException{
		List<Map<String, Object>> mecanicos = new ArrayList<Map<String,Object>>();
		Connection c = null;
		try{
			c = Jdbc.getConnection();
			MecanicoGateway mG = PersistenceFactory.getMecanicosGateway();
			mG.setConnection(c);
			
			mecanicos = mG.listar();
			
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally {
			Jdbc.close(c);
		}
		return mecanicos;
	}
	
}
