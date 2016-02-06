package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.CertificadosGateway;
import uo.ri.amp.persistence.TipoVehiculoGateway;
import uo.ri.common.BusinessException;

public class GenerarCertificados {
	
	public void execute() throws BusinessException{
		Connection c = null;
		try {
			
			TipoVehiculoGateway tG = PersistenceFactory.getTipoVehiculoGateway();
			CertificadosGateway cG = PersistenceFactory.getCertificadosGateway();
			
			c = Jdbc.getConnection();
			tG.setConnection(c);
			cG.setConnection(c);
			
			//Lista con tipos de curso y las horas para ser experto
			List<Map<String, Object>> tipos = tG.listar();
			//Lista con id_tipo, el codigo del mecanico y las horas que el mecanico asistido a cursos para ese tipo, siempre que haya resultado apto
			List<Map<String, Object>> horasMecanicoTipo = cG.listaHorasPorMecanicoYTipo();
			for(int i = 0; i < horasMecanicoTipo.size(); i++){
				for(int j = 0; j < tipos.size(); j++){
					//Comprobacion si las horas que ha asistido el mecanico son suficientes para ser experto
					if((long)horasMecanicoTipo.get(i).get("id_tipo") == (long)tipos.get(j).get("id") && 
							(double)horasMecanicoTipo.get(i).get("horas") >= (int)tipos.get(j).get("numhorasexperto")*1.0){
						Map<String, Object> certificado = new HashMap<String, Object>();
						certificado.put("id_tipo", (long)tipos.get(j).get("id"));
						certificado.put("id_mecanico", (long)horasMecanicoTipo.get(i).get("id_mecanico"));
						certificado.put("fecha", new Date());
						//inserto el certificado con fecha de hoy
						cG.save(certificado);
					}
				}
			}
						
		} catch (SQLException e) {
			throw new BusinessException("No se puede conectar con la BD, ¿esta arrancada?");
		} finally {
			Jdbc.close(c);
		}
	}

}
