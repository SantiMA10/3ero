package uo.ri.amp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.amp.model.keys.CargoKey;

@Entity
@IdClass(CargoKey.class)
@Table(name = "TCargos")
public class Cargo {

	@Id
	@ManyToOne
	private Factura factura;
	@Id
	@ManyToOne
	private MedioPago medioPago;
	private double importe = 0.0;

	Cargo() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result
				+ ((medioPago == null) ? 0 : medioPago.hashCode());
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
		Cargo other = (Cargo) obj;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (medioPago == null) {
			if (other.medioPago != null)
				return false;
		} else if (!medioPago.equals(other.medioPago))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cargo [factura=" + factura + ", medioPago=" + medioPago
				+ ", importe=" + importe + "]";
	}

	public Cargo(Factura factura, MedioPago medioPago, double importe) {
		this.factura = factura;
		this.medioPago = medioPago;
		this.importe = importe;

		Association.Cargar.link(factura, medioPago, this);
	}

	public Factura getFactura() {
		return factura;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public double getImporte() {
		return importe;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	void _setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

}
