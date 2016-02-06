package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.amp.model.keys.IntervencionKey;

@Entity
@IdClass(IntervencionKey.class)
@Table(name = "TIntervenciones")
public class Intervencion {

	@Id
	@ManyToOne
	private Averia averia;
	@Id
	@ManyToOne
	private Mecanico mecanico;

	private int minutos;

	@OneToMany(mappedBy = "intervencion")
	private Set<Sustitucion> sustituciones = new HashSet<Sustitucion>();

	public Intervencion(Mecanico mecanico, Averia averia) {
		super();
		Association.Intervenir.link(averia, mecanico, this);
	}

	Intervencion() {
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public Averia getAveria() {
		return averia;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averia == null) ? 0 : averia.hashCode());
		result = prime * result
				+ ((mecanico == null) ? 0 : mecanico.hashCode());
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
		Intervencion other = (Intervencion) obj;
		if (averia == null) {
			if (other.averia != null)
				return false;
		} else if (!averia.equals(other.averia))
			return false;
		if (mecanico == null) {
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals(other.mecanico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Intervencion [averia=" + averia + ", mecanico=" + mecanico
				+ ", minutos=" + minutos + "]";
	}

	void _setAveria(Averia averia) {
		this.averia = averia;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;

	}

	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}

	public Set<Sustitucion> getSustituciones() {
		return new HashSet<Sustitucion>(sustituciones);
	}

	public double getImporte() {
		double importe = 0;
		for (Sustitucion sustitucion : sustituciones) {
			importe += sustitucion.getImporte();
		}
		importe += getMinutos()
				* getAveria().getVehiculo().getTipo().getPrecioHora() / 60;
		return importe;
	}

}
