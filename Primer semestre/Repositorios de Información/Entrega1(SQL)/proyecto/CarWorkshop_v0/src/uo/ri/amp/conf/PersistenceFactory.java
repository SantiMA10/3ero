package uo.ri.amp.conf;

import uo.ri.amp.persistence.*;
import uo.ri.amp.persistence.impl.*;

public class PersistenceFactory {
	
	public static CursosGateway getCursosGateway(){
		return new CursosGatewayImpl();
	}
	
	public static PorcentajeTipoGateway getPorcentajeTipoGateway(){
		return new PorcentajeTipoGatewayImpl();
	}
	
	public static TipoVehiculoGateway getTipoVehiculoGateway(){
		return new TipoVehiculoGatewayImpl();
	}

	public static AsistenciaGateway getAsistenciaGateway(){
		return new AsistenciaGatewayImpl();
	}
	
	public static MecanicoGateway getMecanicosGateway(){
		return new MecanicosGatewayImpl();
	}

	public static CertificadosGateway getCertificadosGateway() {
		return new CertificadosGatewayImpl();
	}
	
	public static AveriaGateway getAveriaGateway() {
		return new AveriaGatewayImpl();
	}
	
	public static VehiculosGateway getVehiculosGateway() {
		return new VehiculosGatewayImpl();
	}
}
