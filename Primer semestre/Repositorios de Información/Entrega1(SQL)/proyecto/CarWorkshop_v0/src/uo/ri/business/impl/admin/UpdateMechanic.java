package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;
import alb.util.jdbc.Jdbc;

public class UpdateMechanic {
	

	private String nombre;
	private String apellidos;
	private long id;
	
	public UpdateMechanic(String nombre, String apellidos, long id){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.id = id;
	}
	
	public void execute() throws BusinessException{
		
		try {
			MecanicosGateway mG = PersistenceFactory.getMecanicosGateway();
			Connection c = Jdbc.getConnection();
			mG.setConnection(c);
			
			mG.update(id, nombre, apellidos);
			
			c.close();
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		}
		
		
	}
	
}
