package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ModificarAsistencia {
	
	private Map<String, Object> asistencia;
	
	public ModificarAsistencia(Map<String, Object> asistencia) {
		this.asistencia = asistencia;
	}
	
	public void execute() throws BusinessException{
		
		Connection c = null;
		
		try {
			
			AsistenciaGateway aG = PersistenceFactory.getAsistenciaGateway();
			
			c = Jdbc.getConnection();
			aG.setConnection(c);
			
			aG.update(asistencia);
						
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
	}


}
