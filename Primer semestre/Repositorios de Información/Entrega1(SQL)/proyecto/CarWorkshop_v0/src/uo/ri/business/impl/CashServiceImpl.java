package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.FacturarReparaciones;

public class CashServiceImpl implements CashService{

	@Override
	public Map<String, Object> facturarReparaciones(List<Long> idsAveria) {
		return new FacturarReparaciones(idsAveria).execute();
	}
	

}
