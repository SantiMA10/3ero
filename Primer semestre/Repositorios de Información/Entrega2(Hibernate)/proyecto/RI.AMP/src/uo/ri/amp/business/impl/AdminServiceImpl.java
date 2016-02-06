package uo.ri.amp.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.business.impl.admin.AltaAsistencia;
import uo.ri.amp.business.impl.admin.AltaCurso;
import uo.ri.amp.business.impl.admin.BajaAsistencia;
import uo.ri.amp.business.impl.admin.BajaCurso;
import uo.ri.amp.business.impl.admin.BuscarAsistenciaPorCursoYMecanico;
import uo.ri.amp.business.impl.admin.BuscarCursoPorCodigo;
import uo.ri.amp.business.impl.admin.GenerarCertificados;
import uo.ri.amp.business.impl.admin.ListarAsistenciaCurso;
import uo.ri.amp.business.impl.admin.ListarCursos;
import uo.ri.amp.business.impl.admin.ListarFormacionMecanico;
import uo.ri.amp.business.impl.admin.ListarFormacionTipoVehiculo;
import uo.ri.amp.business.impl.admin.ListarMecanicos;
import uo.ri.amp.business.impl.admin.ListarTiposVehiculo;
import uo.ri.amp.business.impl.admin.ModificarAsistencia;
import uo.ri.amp.business.impl.admin.ModificarCurso;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.Calificacion;

public class AdminServiceImpl implements AdminService {

	ComandExecutor comandExecutor = new ComandExecutor();

	@Override
	public void altaCurso(Curso curso, Map<TipoVehiculo, Integer> porcentajes)
			throws BusinessException {
		comandExecutor.execute(new AltaCurso(curso, porcentajes));
	}

	@Override
	public List<TipoVehiculo> listarTiposVehiculo() throws BusinessException {
		@SuppressWarnings("unchecked")
		List<TipoVehiculo> tipos = (List<TipoVehiculo>) comandExecutor
				.execute(new ListarTiposVehiculo());
		return tipos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarCursos() throws BusinessException {
		return (List<Curso>) comandExecutor.execute(new ListarCursos());
	}

	@Override
	public Curso buscarCursoPorCodigo(String codigo) throws BusinessException {
		return (Curso) comandExecutor.execute(new BuscarCursoPorCodigo(codigo));
	}

	@Override
	public void modificarCurso(Curso curso,
			Map<TipoVehiculo, Integer> porcentajes) throws BusinessException {
		comandExecutor.execute(new ModificarCurso(curso, porcentajes));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> listarMecanicos() throws BusinessException {
		return (List<Mecanico>) comandExecutor.execute(new ListarMecanicos());
	}

	@Override
	public void altaAsistencia(Long mecanico, String codigo,
			Calificacion calificacion, Date fechaInicio, Date fechaFin,
			int asistencia) throws BusinessException {
		comandExecutor.execute(new AltaAsistencia(mecanico, codigo,
				calificacion, fechaInicio, fechaFin, asistencia));

	}

	@Override
	public void bajaCurso(Curso curso) throws BusinessException {
		comandExecutor.execute(new BajaCurso(curso));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Asistencia> listarAsistenciaCurso(String codigo)
			throws BusinessException {
		return (List<Asistencia>) comandExecutor
				.execute(new ListarAsistenciaCurso(codigo));
	}

	@Override
	public Asistencia buscarAsistenciaPorCursoYMecanico(String codigo, Long id)
			throws BusinessException {
		return (Asistencia) comandExecutor
				.execute(new BuscarAsistenciaPorCursoYMecanico(codigo, id));
	}

	@Override
	public void bajaAsistencia(String codigo, Long id) throws BusinessException {
		comandExecutor.execute(new BajaAsistencia(codigo, id));
	}

	@Override
	public void modificarAsistencia(Asistencia asistencia)
			throws BusinessException {
		comandExecutor.execute(new ModificarAsistencia(asistencia));
	}

	@Override
	public void generarCertificados() throws BusinessException {
		comandExecutor.execute(new GenerarCertificados());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listarFormacionMecanico(Mecanico mecanico)
			throws BusinessException {
		return ((Map<String, Object>) comandExecutor
				.execute(new ListarFormacionMecanico(mecanico)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Map<Mecanico, Double>> listarFormacionTipoVehiculo()
			throws BusinessException {
		return (Map<String, Map<Mecanico, Double>>) comandExecutor
				.execute(new ListarFormacionTipoVehiculo());
	}

}
