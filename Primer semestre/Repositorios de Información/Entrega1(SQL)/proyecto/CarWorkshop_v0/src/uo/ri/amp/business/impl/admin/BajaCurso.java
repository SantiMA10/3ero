package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.amp.persistence.CursosGateway;
import uo.ri.amp.persistence.PorcentajeTipoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class BajaCurso {
	
	private String codigo_curso;
	
	public BajaCurso(String codigo_curso) {
		this.codigo_curso = codigo_curso;
	}
	
	public boolean execute() throws BusinessException{
		
		boolean borrado = false;
		Connection c = null;
		
		try {
			
			CursosGateway cG = PersistenceFactory.getCursosGateway();
			PorcentajeTipoGateway pG = PersistenceFactory.getPorcentajeTipoGateway();
			AsistenciaGateway aG = PersistenceFactory.getAsistenciaGateway();
			
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			cG.setConnection(c);
			pG.setConnection(c);
			aG.setConnection(c);
			
			//Comprubo que no hay asistencia para el curso que intenta borrar el administrador
			if(!aG.isAsistencia(codigo_curso)){
				pG.delete(codigo_curso);
				cG.delete(codigo_curso);
				borrado = true;
			}
			else{
				throw new BusinessException("No puedes borrar un curso en el que hay registrada asistencia.");
			}
			
			c.commit();
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
		return borrado;
		
	}

}
