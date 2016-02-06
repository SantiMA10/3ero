package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AveriaGateway;
import uo.ri.amp.persistence.VehiculosGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class RegistrarAveria {
	
	private String matricula, descripcion;
	private Date fecha;
	
	public RegistrarAveria(String matricula, String descripcion, Date fecha){
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
			
			//Obtengo el id del vehiculo a partir de la matriculas
			long id = (long) vG.findByMatricula(matricula).get("id");
			aG.registrarAveria(id, descripcion, fecha);
			//Incremento el numero de averias de ese vehiculo
			vG.incrementarNumAverias(id);
			
			c.commit();
			
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally{
			Jdbc.close(c);
		}
		
	}

}
