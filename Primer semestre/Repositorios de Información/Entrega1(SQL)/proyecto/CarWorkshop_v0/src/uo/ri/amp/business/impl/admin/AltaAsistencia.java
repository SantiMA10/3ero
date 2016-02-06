package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.common.BusinessException;

public class AltaAsistencia {
	
	private Map<String, Object> asistencia;
	
	public AltaAsistencia(Map<String, Object> asistencia) {
		this.asistencia = asistencia;
	}
	
	public void execute() throws BusinessException{
		
		Connection c = null;
		
		try {
			
			AsistenciaGateway aG = PersistenceFactory.getAsistenciaGateway();
			
			c = Jdbc.getConnection();
			aG.setConnection(c);
			
			aG.save(asistencia);
			
			c.close();
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
	}

}
