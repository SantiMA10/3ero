package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface CashService {
	
	public Map<String, Object> facturarReparaciones(List<Long> idsAveria);

}
