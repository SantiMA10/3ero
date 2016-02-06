package uo.ri.amp.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.business.impl.admin.*;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService{

	@Override
	public void altaCurso(Map<String, Object> curso) throws BusinessException {
		new AltaCurso(curso).execute();
	}
	
	@Override
	public List<Map<String, Object>> ListarCursos() throws BusinessException {
		return new ListarCursos().execute();
	}

	@Override
	public List<Map<String, Object>> ListarTiposVehiculo() throws BusinessException {
		return new ListarTiposVehiculo().execute();
	}

	@Override
	public void actualizarCurso(Map<String, Object> curso) throws BusinessException {
		new ActualizarCurso(curso).execute();
		
	}

	@Override
	public void altaAsistencia(Map<String, Object> asistencia) throws BusinessException {
		new AltaAsistencia(asistencia).execute();
	}

	@Override
	public void modificarAsistencia(Map<String, Object> asistencia) throws BusinessException {
		new ModificarAsistencia(asistencia).execute();
	}

	@Override
	public boolean borrarCurso(String codigo_curso) throws BusinessException {
		return new BajaCurso(codigo_curso).execute();
	}

	@Override
	public void borrarAsistencia(String codigo_curso, int id_mecanico) throws BusinessException {
		new BorrarAsistencia(codigo_curso, id_mecanico).execute();
	}

	@Override
	public List<Map<String, Object>> listarAsistenciaCursos(String codigo_curso) throws BusinessException {
		return new ListaAsistenciaCursos(codigo_curso).execute();
	}

	@Override
	public void generarCertificados() throws BusinessException {
		new GenerarCertificados().execute();
	}

	@Override
	public Map<String, Object> listarFormacionMecanico(long id) throws BusinessException {
		return new ListarFormacionMecanico(id).execute();
	}

	@Override
	public List<Map<String, Object>> listarHorasAsistenciaTipoVehiculo() throws BusinessException {
		return new ListarHorasAsistenciaTipoVehiculo().execute();
	}

	@Override
	public List<Map<String, Object>> listarMecanicos() throws BusinessException {
		return new ListarMecanicos().execute();
	}
	
	

}
