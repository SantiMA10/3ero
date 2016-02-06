package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.conf.SantiUtil;
import uo.ri.amp.persistence.AveriaGateway;
import uo.ri.amp.persistence.VehiculosGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ListarHistorialAverias {
	
	private String matricula;
	
	public ListarHistorialAverias(String matricula){
		this.matricula = matricula;
	}

	public List<Map<String, Object>> execute() throws BusinessException{
		
		List<Map<String, Object>> lista = new ArrayList<Map<String,Object>>(); 
		Connection c = null;
		
		try {
			AveriaGateway aG = PersistenceFactory.getAveriaGateway();
			VehiculosGateway vG = PersistenceFactory.getVehiculosGateway();
			
			c = Jdbc.getConnection();
			aG.setConnection(c);
			vG.setConnection(c);
			
			try{
				//Con la matricula pasada obtengo el primer id que devuelve la consulta y con ese id listo las averis
				lista = aG.listarHistorialAverias((long) vG.findByMatricula(matricula).get("id"));
				for(Map<String, Object> dato: lista){
					//Para cada averia formateo la fecha
					dato.put("fecha", SantiUtil.formatearFecha((String) dato.get("fecha")));
				}
			} catch(NullPointerException e){
				throw new BusinessException("No hay averias para esa matricula.");
			}
						
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
		return lista;
	}
	
}
