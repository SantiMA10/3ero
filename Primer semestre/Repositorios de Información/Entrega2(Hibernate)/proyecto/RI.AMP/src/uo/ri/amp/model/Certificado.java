package uo.ri.amp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.amp.model.keys.CertificadoKey;

@Entity
@IdClass(CertificadoKey.class)
@Table(name = "TCertificado")
public class Certificado {

	@Id
	@ManyToOne
	private Mecanico mecanico;
	@Id
	@ManyToOne
	private TipoVehiculo tipo;

	private Date fecha;

	public Certificado(Mecanico mecanico, TipoVehiculo tipo, Date fecha) {
		super();
		Association.Certificar.link(tipo, mecanico, this);
		this.fecha = fecha;
	}

	Certificado() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	void _setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}
}
