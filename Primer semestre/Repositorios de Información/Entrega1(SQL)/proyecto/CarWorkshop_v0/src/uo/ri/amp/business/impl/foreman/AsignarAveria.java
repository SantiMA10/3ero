package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AveriaGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class AsignarAveria {
	
	private long idAveria, idMecanico;
	
	public AsignarAveria(long idAveria, long idMecanico){
		this.idAveria = idAveria;
		this.idMecanico = idMecanico;
	}
	
	public void execute() throws BusinessException{
		
		Connection c = null;
		
		try {
			AveriaGateway aG = PersistenceFactory.getAveriaGateway();
			
			c = Jdbc.getConnection();
			aG.setConnection(c);
			aG.asignarAveria(idAveria, idMecanico);
						
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
	}

}
