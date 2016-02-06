package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.CursosGateway;
import uo.ri.amp.persistence.PorcentajeTipoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ListarCursos {
	
	public List<Map<String, Object>> execute() throws BusinessException{
		Connection c = null;
		
		try {
			
			CursosGateway cG = PersistenceFactory.getCursosGateway();
			PorcentajeTipoGateway pG = PersistenceFactory.getPorcentajeTipoGateway();
			
			c = Jdbc.getConnection();
			cG.setConnection(c);
			pG.setConnection(c);
			
			//Obtengo la lista de cursos
			List<Map<String, Object>> cursos = cG.list();
			for(int i = 0; i < cursos.size(); i++){
				//Para cada curso, obtengo la lista de sus porcentajes
				Map<String, Object> porcentajesTipos = pG.list((String) cursos.get(i).get("codigo"));
				cursos.get(i).put("tipos", porcentajesTipos.get("tipos"));
				cursos.get(i).put("porcentajes", porcentajesTipos.get("porcentajes"));
			}
				
			Jdbc.close(c);
			
			return cursos;
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} 
		
	}

}
