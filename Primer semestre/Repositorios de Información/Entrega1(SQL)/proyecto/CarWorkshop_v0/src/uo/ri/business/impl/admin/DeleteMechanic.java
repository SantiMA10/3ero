package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

public class DeleteMechanic {
	
	
	private long idMecanico;
	
	public DeleteMechanic(long idMecanico){
		this.idMecanico = idMecanico;
	}

	public void execute() throws BusinessException{
		
		try {
			MecanicosGateway mG = PersistenceFactory.getMecanicosGateway();
			Connection c = Jdbc.getConnection();
			mG.setConnection(c);
			
			mG.delete(idMecanico);
			
			c.close();
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		}
		
		
	}
	
}
