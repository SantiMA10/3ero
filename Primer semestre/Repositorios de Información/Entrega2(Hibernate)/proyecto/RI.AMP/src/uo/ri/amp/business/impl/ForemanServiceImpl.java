package uo.ri.amp.business.impl;

import java.util.List;

import uo.ri.amp.business.ForemanService;
import uo.ri.amp.business.impl.foreman.AltaAveria;
import uo.ri.amp.business.impl.foreman.AsignarAveria;
import uo.ri.amp.business.impl.foreman.BajaAveria;
import uo.ri.amp.business.impl.foreman.BuscarAveriaPorID;
import uo.ri.amp.business.impl.foreman.BuscarVehiculoPorMatricula;
import uo.ri.amp.business.impl.foreman.HistorialAverias;
import uo.ri.amp.business.impl.foreman.ListarMecanicosExpertosEnTipo;
import uo.ri.amp.business.impl.foreman.ModificarAveria;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.Vehiculo;
import uo.ri.amp.model.exception.BusinessException;

public class ForemanServiceImpl implements ForemanService {

	ComandExecutor executor = new ComandExecutor();

	@Override
	public Vehiculo buscarVehiculoPorMatricula(String matricula)
			throws BusinessException {
		return (Vehiculo) executor.execute(new BuscarVehiculoPorMatricula(
				matricula));
	}

	@Override
	public void altaAveria(Averia averia) throws BusinessException {
		executor.execute(new AltaAveria(averia));
	}

	@Override
	public Averia buscarAveriaPorID(Long id) throws BusinessException {
		return (Averia) executor.execute(new BuscarAveriaPorID(id));
	}

	@Override
	public void modifcarAveria(Averia averia) throws BusinessException {
		executor.execute(new ModificarAveria(averia));
	}

	@Override
	public void bajaAveria(Long id) throws BusinessException {
		executor.execute(new BajaAveria(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Averia> historialAverias(String matricula)
			throws BusinessException {
		return (List<Averia>) executor.execute(new HistorialAverias(matricula));
	}

	@Override
	public void asignarAveria(Averia averia, Mecanico mecanico)
			throws BusinessException {
		executor.execute(new AsignarAveria(averia, mecanico));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> listarMecanicosExpertosEnTipo(Long id)
			throws BusinessException {
		return (List<Mecanico>) executor
				.execute(new ListarMecanicosExpertosEnTipo(id));
	}

}
