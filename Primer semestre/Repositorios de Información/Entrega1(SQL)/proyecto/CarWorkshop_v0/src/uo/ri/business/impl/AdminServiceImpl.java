package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.ListMechanics;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService{

	@Override
	public void addMechanic(String nombre, String apellidos) throws BusinessException {
		new AddMechanic(nombre, apellidos).execute();
	}

	@Override
	public void deleteMechanic(long idMecanico) throws BusinessException{
		new DeleteMechanic(idMecanico).execute();
	}

	@Override
	public List<Map<String, Object>> listMechanics() throws BusinessException{
		return new ListMechanics().execute();
	}

	@Override
	public void updateMechanic(String nombre, String apellidos, long idMecanico) throws BusinessException{
		new UpdateMechanic(nombre, apellidos, idMecanico);
	}

}
