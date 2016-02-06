package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AdminService {
	
	public void addMechanic(String nombre, String apellidos) throws BusinessException;
	public void deleteMechanic(long idMecanico) throws BusinessException;
	public List<Map<String, Object>> listMechanics() throws BusinessException;
	public void updateMechanic(String nombre, String apellidos, long idMecanico) throws BusinessException;
	

}
