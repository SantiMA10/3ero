package uo.ri.amp.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.Calificacion;

public interface AdminService {

	public void altaCurso(Curso curso, Map<TipoVehiculo, Integer> porcentajes)
			throws BusinessException;

	public List<Curso> listarCursos() throws BusinessException;

	public Curso buscarCursoPorCodigo(String codigo) throws BusinessException;

	public void bajaCurso(Curso curso) throws BusinessException;

	public List<TipoVehiculo> listarTiposVehiculo() throws BusinessException;

	public void modificarCurso(Curso curso,
			Map<TipoVehiculo, Integer> porcentajes) throws BusinessException;

	public List<Mecanico> listarMecanicos() throws BusinessException;

	public void altaAsistencia(Long mecanico, String codigo,
			Calificacion calificacion, Date fechaInicio, Date fechaFin,
			int asistencia) throws BusinessException;

	public List<Asistencia> listarAsistenciaCurso(String codigo)
			throws BusinessException;

	public Asistencia buscarAsistenciaPorCursoYMecanico(String codigo, Long id)
			throws BusinessException;

	public void bajaAsistencia(String codigo, Long id) throws BusinessException;

	public void modificarAsistencia(Asistencia asistencia)
			throws BusinessException;

	public void generarCertificados() throws BusinessException;

	public Map<String, Object> listarFormacionMecanico(Mecanico mecanico)
			throws BusinessException;

	public Map<String, Map<Mecanico, Double>> listarFormacionTipoVehiculo()
			throws BusinessException;

}
