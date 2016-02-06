package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AveriaGateway;
import uo.ri.amp.persistence.VehiculosGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ModificarAveria {
	
	private long id;
	private String descripcion, matricula;
	private Date fecha;
	
	public ModificarAveria(long id, String matricula, String descripcion, Date fecha){
		this.id = id;
		this.matricula = matricula;
		this.descripcion = descripcion;
		this.fecha = fecha;
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
			
			//Obtengo el id del coche que estaba en la averia
			long oldIdVehiculo = aG.findVehiculoIDbyAveriaId(id);
			//Le decremento el numero de averias
			vG.decrementarNumAverias(oldIdVehiculo);
			//Obtengo el id del coche buscandola por la matricula
			long newIdVehiculo = (long) vG.findByMatricula(matricula).get("id");
			aG.modificarAveria(id, newIdVehiculo, descripcion, fecha);
			//Le incremento el numero de averias al nuevo vehiculo
			vG.incrementarNumAverias(newIdVehiculo);
			
			c.commit();
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
	}

}
