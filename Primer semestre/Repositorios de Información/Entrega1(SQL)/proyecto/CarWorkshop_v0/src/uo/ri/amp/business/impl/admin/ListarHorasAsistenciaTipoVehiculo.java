package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.AsistenciaGateway;
import uo.ri.amp.persistence.TipoVehiculoGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class ListarHorasAsistenciaTipoVehiculo {

	public List<Map<String, Object>> execute() throws BusinessException {
		List<Map<String, Object>> listado = new ArrayList<Map<String,Object>>();
		Connection c = null;
		
		try {
			
			AsistenciaGateway aG = PersistenceFactory.getAsistenciaGateway();
			TipoVehiculoGateway tG = PersistenceFactory.getTipoVehiculoGateway();
			
			c = Jdbc.getConnection();
			aG.setConnection(c);
			tG.setConnection(c);
			
			List<Map<String, Object>> tipos = tG.listar();
			for(int i = 0; i < tipos.size(); i++){
				Map<String, Object> tipo = new HashMap<String, Object>();
				tipo.put("nombre", tipos.get(i).get("nombre"));
				tipo.put("mecanicos", aG.asistenciaPorTipo((long) tipos.get(i).get("id")));
				listado.add(tipo);
			}
									
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally {
			Jdbc.close(c);
		}
		
		return listado;
	}

}
