package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.MecanicoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ListarMecanicosExpertos {
	
	private long id;
	
	public ListarMecanicosExpertos(long id){
		this.id = id;
	}
	
	public List<Map<String, Object>> execute() throws BusinessException{
		
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>(); 
		Connection c = null;
		
		try {
			MecanicoGateway mG = PersistenceFactory.getMecanicosGateway();
			
			c = Jdbc.getConnection();
			mG.setConnection(c);
			
			lista = mG.listarMecanicosExpertos(id);
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		}  finally{
			Jdbc.close(c);
		}
		
		return lista;
	}

}
