package uo.ri.amp.business;

import java.util.List;

import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.Vehiculo;
import uo.ri.amp.model.exception.BusinessException;

public interface ForemanService {

	public void altaAveria(Averia averia) throws BusinessException;

	public Averia buscarAveriaPorID(Long id) throws BusinessException;

	public void modifcarAveria(Averia averia) throws BusinessException;

	public void bajaAveria(Long id) throws BusinessException;

	public List<Averia> historialAverias(String matricula)
			throws BusinessException;

	public void asignarAveria(Averia averia, Mecanico mecanico)
			throws BusinessException;

	public Vehiculo buscarVehiculoPorMatricula(String matricula)
			throws BusinessException;

	public List<Mecanico> listarMecanicosExpertosEnTipo(Long id)
			throws BusinessException;

}
