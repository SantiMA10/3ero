package uo.ri.amp.conf;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.business.ForemanService;
import uo.ri.amp.business.impl.AdminServiceImpl;
import uo.ri.amp.business.impl.ForemanServiceImpl;

public class ServiceFactory {
	
	public static AdminService getAdminService(){
		return new AdminServiceImpl();
	}
	
	public static ForemanService getForemanService(){
		return new ForemanServiceImpl();
	}

}
