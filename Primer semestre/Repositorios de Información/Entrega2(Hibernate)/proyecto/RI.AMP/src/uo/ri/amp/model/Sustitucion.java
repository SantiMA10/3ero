package uo.ri.amp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.amp.model.keys.SustitucionKey;

@Entity
@IdClass(SustitucionKey.class)
@Table(name = "TSustituciones")
public class Sustitucion {

	@Id
	@ManyToOne
	private Repuesto repuesto;
	@Id
	@ManyToOne
	private Intervencion intervencion;

	private int cantidad;

	public Sustitucion(Repuesto repuesto, Intervencion intervencion) {
		super();
		Association.Sustituir.link(repuesto, intervencion, this);
	}

	Sustitucion() {
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public Intervencion getIntervencion() {
		return intervencion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((intervencion == null) ? 0 : intervencion.hashCode());
		result = prime * result
				+ ((repuesto == null) ? 0 : repuesto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sustitucion other = (Sustitucion) obj;
		if (intervencion == null) {
			if (other.intervencion != null)
				return false;
		} else if (!intervencion.equals(other.intervencion))
			return false;
		if (repuesto == null) {
			if (other.repuesto != null)
				return false;
		} else if (!repuesto.equals(other.repuesto))
			return false;
		return true;
	}

	void _setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	void _setIntervencion(Intervencion intervencion) {
		this.intervencion = intervencion;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getImporte() {
		double importe = 0.0;
		importe += getCantidad() * getRepuesto().getPrecio();
		return importe;
	}

}
