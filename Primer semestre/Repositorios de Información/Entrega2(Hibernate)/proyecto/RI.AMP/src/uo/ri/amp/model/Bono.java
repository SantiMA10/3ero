package uo.ri.amp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TBono")
public class Bono extends MedioPago {

	protected double disponible = 0.0;
	protected String codigo;

	Bono() {
	}

	public Bono(String codigo) {
		super();
		this.codigo = codigo;
	}

	public Bono(String codigo, double d) {
		this(codigo);
		this.disponible = d;
	}

	public double getDisponible() {
		return disponible;
	}

	public void setDisponible(double disponible) {
		this.disponible = disponible;
	}

	public String getCodigo() {
		return codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Bono other = (Bono) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bono [disponible=" + disponible + ", codigo=" + codigo + "]";
	}

}
