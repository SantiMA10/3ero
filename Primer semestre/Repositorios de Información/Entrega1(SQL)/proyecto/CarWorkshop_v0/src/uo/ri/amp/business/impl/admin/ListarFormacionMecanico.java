package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.amp.persistence.CursosGateway;
import uo.ri.amp.persistence.TipoVehiculoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ListarFormacionMecanico {
	
	private long id;

	public ListarFormacionMecanico(long id) {
		this.id = id;
	}

	public Map<String, Object> execute() throws BusinessException {
		Map<String, Object> listado = new HashMap<String, Object>();
		Connection c = null;
		
		try {
			
			CursosGateway cG = PersistenceFactory.getCursosGateway();
			AsistenciaGateway aG = PersistenceFactory.getAsistenciaGateway();
			TipoVehiculoGateway tG = PersistenceFactory.getTipoVehiculoGateway();
			
			c = Jdbc.getConnection();
			cG.setConnection(c);
			aG.setConnection(c);
			tG.setConnection(c);
			
			double horasCursos = cG.horasTotalCursos(id);
			double horasAsistidas = aG.asistenciaTotal(id);
			
			listado.put("horascurso", horasCursos);
			listado.put("horasasistidas", horasAsistidas);
			listado.put("horasportipo", aG.asistenciaTotalACursoPorTipo(id));
									
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
		return listado;
	}

}
