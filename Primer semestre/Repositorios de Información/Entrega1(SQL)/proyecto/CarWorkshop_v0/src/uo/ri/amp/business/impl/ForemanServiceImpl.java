package uo.ri.amp.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.ForemanService;
import uo.ri.amp.business.impl.foreman.AsignarAveria;
import uo.ri.amp.business.impl.foreman.EliminarAveria;
import uo.ri.amp.business.impl.foreman.ListarHistorialAverias;
import uo.ri.amp.business.impl.foreman.ListarMecanicosExpertos;
import uo.ri.amp.business.impl.foreman.ModificarAveria;
import uo.ri.amp.business.impl.foreman.RegistrarAveria;
import uo.ri.common.BusinessException;

public class ForemanServiceImpl implements ForemanService{

	@Override
	public void registrarAveria(String matricula, String descripcion, Date fecha) throws BusinessException {
		new RegistrarAveria(matricula, descripcion, fecha).execute();
	}

	@Override
	public void modificarAveria(long id, String matricula, String descripcion, Date fecha) throws BusinessException {
		new ModificarAveria(id, matricula, descripcion, fecha).execute();
	}

	@Override
	public void eliminarAveria(long id) throws BusinessException {
		new EliminarAveria(id).execute();
	}

	@Override
	public List<Map<String, Object>> listarMecanicosExpertos(long id) throws BusinessException {
		return new ListarMecanicosExpertos(id).execute();
	}

	@Override
	public void asignarAveria(long idAveria, long idMecanico) throws BusinessException{
		new AsignarAveria(idAveria, idMecanico).execute();
	}

	@Override
	public List<Map<String, Object>> listarHistorialAverias(String matricula) throws BusinessException {
		return new ListarHistorialAverias(matricula).execute();
	}

}
