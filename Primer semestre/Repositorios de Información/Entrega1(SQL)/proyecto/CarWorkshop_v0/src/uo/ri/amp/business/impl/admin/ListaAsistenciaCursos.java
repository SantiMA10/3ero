package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.amp.persistence.MecanicoGateway;
import uo.ri.common.BusinessException;

public class ListaAsistenciaCursos {
	
	private String codigo_curso;
	
	public ListaAsistenciaCursos(String codigo_curso) {
		this.codigo_curso = codigo_curso;
	}
	
	public List<Map<String, Object>> execute() throws BusinessException{
		
		Connection c = null;
		
		try {
			
			AsistenciaGateway aG = PersistenceFactory.getAsistenciaGateway();
			MecanicoGateway mG = PersistenceFactory.getMecanicosGateway();
			
			c = Jdbc.getConnection();
			aG.setConnection(c);
			mG.setConnection(c);
			
			//Obtengo la lista de asistencia a un curso
			List<Map<String, Object>> listado = aG.listar(codigo_curso);
			for(int i = 0; i < listado.size(); i++){
				//Cambio el id del mecanico por su nombre
				String nombre = (String) mG.findById(Long.parseLong((String) listado.get(i).get("id_mecanico"))).get("nombre");
				listado.get(i).put("nombre", nombre);
				//Formato de forma correcta la fecha
				listado.get(i).put("fechafin", SantiUtil.formatearFecha((String)listado.get(i).get("fechafin")));
			}
			
			Jdbc.close(c);
			
			return listado;
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} 
		
	}

}
