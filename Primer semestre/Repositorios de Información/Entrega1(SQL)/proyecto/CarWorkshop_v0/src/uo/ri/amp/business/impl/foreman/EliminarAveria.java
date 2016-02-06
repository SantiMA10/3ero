package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AveriaGateway;
import uo.ri.amp.persistence.VehiculosGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class EliminarAveria {
	
	private long id;
	
	public EliminarAveria(long id) {
		this.id = id;
	}
	
	public void execute() throws BusinessException{
		
		Connection c = null;
		
		try {
			AveriaGateway aG = PersistenceFactory.getAveriaGateway();
			VehiculosGateway vG = PersistenceFactory.getVehiculosGateway();
			
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			aG.setConnection(c);
			vG.setConnection(c);
			
			//Antes de eliminar la averia obtengo el id del vehiculo para poder restarle el numero de averias
			long idVehiculo = aG.findVehiculoIDbyAveriaId(id);
			aG.eliminarAveria(id);
			vG.decrementarNumAverias(idVehiculo);
			
			c.commit();
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally {
			Jdbc.close(c);
		}
		
	}

}
