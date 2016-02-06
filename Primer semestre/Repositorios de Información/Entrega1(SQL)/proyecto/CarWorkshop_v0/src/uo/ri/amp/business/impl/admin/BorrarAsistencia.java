package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.common.BusinessException;

public class BorrarAsistencia {
	
	private String codigo_curso;
	private int id_mecanico;
	
	public BorrarAsistencia(String codigo_curso, int id_mecanico) {
		this.codigo_curso = codigo_curso;
		this.id_mecanico = id_mecanico;
	}
	
	public void execute() throws BusinessException{
		
		Connection c = null;
				
		try {
			
			AsistenciaGateway aG = PersistenceFactory.getAsistenciaGateway();
			
			c = Jdbc.getConnection();
			aG.setConnection(c);
			
			aG.borrarAsistecia(codigo_curso, id_mecanico);
						
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
	}

}
