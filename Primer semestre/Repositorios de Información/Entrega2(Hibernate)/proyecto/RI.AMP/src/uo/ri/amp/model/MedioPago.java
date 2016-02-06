package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "TMediosPago")
@DiscriminatorColumn(name = "DTYPE")
public abstract class MedioPago {

	@Id
	@GeneratedValue
	protected Long id;
	@ManyToOne
	private Cliente cliente;
	protected double acumulado = 0.0;

	@OneToMany(mappedBy = "medioPago")
	private Set<Cargo> cargos = new HashSet<Cargo>();

	public MedioPago(Cliente cliente) {
		this.cliente = cliente;
		Association.Pagar.link(cliente, this);
	}

	MedioPago() {
	}

	public double getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(double acumulado) {
		this.acumulado = acumulado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		MedioPago other = (MedioPago) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedioPago [cliente=" + cliente + ", acumulado=" + acumulado
				+ "]";
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<Cargo>(cargos);
	}

	Set<Cargo> _getCargos() {
		return (cargos);
	}

	public Long getId() {
		return id;
	}

}
