package uo.ri.amp.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ForemanService {
	
	public void registrarAveria(String matricula, String descripcion, Date fecha) throws BusinessException;
	public void modificarAveria(long id, String matricula, String descripcion, Date fecha) throws BusinessException;
	public void eliminarAveria(long id) throws BusinessException;
	public List<Map<String, Object>> listarHistorialAverias(String matricula) throws BusinessException;
	public List<Map<String, Object>> listarMecanicosExpertos(long id) throws BusinessException;
	public void asignarAveria(long idAveria, long idMecanico) throws BusinessException;

}
