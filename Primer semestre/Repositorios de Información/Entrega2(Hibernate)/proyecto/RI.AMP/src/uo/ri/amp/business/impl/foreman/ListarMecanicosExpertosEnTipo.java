package uo.ri.amp.business.impl.foreman;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Certificado;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.persistence.MecanicoFinder;

public class ListarMecanicosExpertosEnTipo implements Comand {

	private Long idTipo;

	public ListarMecanicosExpertosEnTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public Object execute() throws BusinessException {
		List<Mecanico> mecanicos = MecanicoFinder.findAll();
		List<Mecanico> expertos = new ArrayList<Mecanico>();
		for (Mecanico mecanico : mecanicos) {
			Set<Certificado> certificados = mecanico.getCertificados();
			for (Certificado certificado : certificados) {
				if (certificado.getTipo().getId().equals(idTipo)) {
					expertos.add(mecanico);
				}
			}
		}
		return expertos;
	}

}
