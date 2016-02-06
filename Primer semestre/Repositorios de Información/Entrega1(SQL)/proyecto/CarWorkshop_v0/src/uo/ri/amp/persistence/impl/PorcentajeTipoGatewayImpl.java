package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.PorcentajeTipoGateway;
import uo.ri.common.BusinessException;

public class PorcentajeTipoGatewayImpl implements PorcentajeTipoGateway{

	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(Map<String, Object> curso) throws BusinessException {

		PreparedStatement pre = null;

		try {
			List<Integer> tipos = (List<Integer>) curso.get("tipos");
			List<Integer> porcentajes = (List<Integer>) curso.get("porcentajes");

			for(int i = 0; i < tipos.size(); i++){
				pre = c.prepareStatement(Conf.get("SQL_PORCENTAJETIPO_INSERTAR"));
				pre.setInt(1, (int)tipos.get(i));
				pre.setString(2, (String) curso.get("codigo"));
				pre.setInt(3, (int)porcentajes.get(i));

				pre.executeUpdate();
			}


		} catch (SQLException e) {
			if(e.getErrorCode() == -424 || e.getErrorCode() == -177){
				throw new BusinessException("No hay ningun tipo de coche con ese id");
			}
			if(e.getErrorCode() == -104){
				throw new BusinessException("No puedes insertar dos porcentajes para el mismo tipo de vehiculo");
			}
		} finally{
			Jdbc.close(pre);
		}

	}

	@Override
	public Map<String, Object> list(String codigo) throws BusinessException {

		PreparedStatement pre = null;
		ResultSet rs = null;

		Map<String, Object> cursos = new HashMap<String,Object>();

		try {
			pre = c.prepareStatement(Conf.get("SQL_PORCENTAJETIPO_LISTAR"));
			pre.setString(1, codigo);

			rs = pre.executeQuery();

			List<Integer> tipos = new ArrayList<Integer>();
			List<Integer> porcentajes = new ArrayList<Integer>();

			while(rs.next()){
				tipos.add(rs.getInt("id_tipo"));
				porcentajes.add(rs.getInt("porcentaje"));
			}

			cursos.put("tipos", tipos);
			cursos.put("porcentajes", porcentajes);

		} catch (SQLException e) {

		} finally{
			Jdbc.close(rs, pre);
		}

		return cursos;
	}

	@Override
	public void delete(String codigo) throws BusinessException {

		PreparedStatement pre = null;

		try {

			pre = c.prepareStatement(Conf.get("SQL_PORCENTAJETIPO_BORRAR"));
			pre.setString(1, codigo);

			pre.executeUpdate();


		} catch (SQLException e) {
		} finally{
			Jdbc.close(pre);
		}

	}

}
