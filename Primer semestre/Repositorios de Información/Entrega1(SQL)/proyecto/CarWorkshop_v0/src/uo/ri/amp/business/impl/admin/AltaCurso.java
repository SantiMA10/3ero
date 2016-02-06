package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.CursosGateway;
import uo.ri.amp.persistence.PorcentajeTipoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class AltaCurso {
	
	private Map<String, Object> curso;
	
	public AltaCurso(Map<String, Object> curso) {
		this.curso = curso;
	}
	
	public void execute() throws BusinessException{
		
		Connection c = null;
		
		try {
			
			CursosGateway cG = PersistenceFactory.getCursosGateway();
			PorcentajeTipoGateway pG = PersistenceFactory.getPorcentajeTipoGateway();
			
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			cG.setConnection(c);
			pG.setConnection(c);
			
			cG.save(curso);
			pG.save(curso);
			
			c.commit();
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
	}

}
