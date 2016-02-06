package uo.ri.amp.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AdminService {
	
	public void altaCurso(Map<String, Object> curso) throws BusinessException;
	public List<Map<String, Object>> ListarCursos() throws BusinessException;
	public List<Map<String, Object>> ListarTiposVehiculo() throws BusinessException;
	public void actualizarCurso(Map<String, Object> curso)  throws BusinessException;
	public boolean borrarCurso(String codigo_curso) throws BusinessException;
	
	public void altaAsistencia(Map<String, Object> asistencia) throws BusinessException;
	public void modificarAsistencia(Map<String, Object> asistencia) throws BusinessException;
	public void borrarAsistencia(String codigo_curso, int id_mecanico) throws BusinessException;
	public List<Map<String, Object>> listarAsistenciaCursos(String codigo_curso) throws BusinessException;
	
	public Map<String, Object> listarFormacionMecanico(long id) throws BusinessException;
	public List<Map<String, Object>> listarHorasAsistenciaTipoVehiculo() throws BusinessException;
	public List<Map<String, Object>> listarMecanicos() throws BusinessException;

	public void generarCertificados() throws BusinessException;
}
