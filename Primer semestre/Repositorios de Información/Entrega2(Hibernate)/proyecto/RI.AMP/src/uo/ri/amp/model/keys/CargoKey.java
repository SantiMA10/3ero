package uo.ri.amp.model.keys;

import java.io.Serializable;

public class CargoKey implements Serializable {
	private static final long serialVersionUID = 1L;

	Long factura;
	Long medioPago;

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
		CargoKey other = (CargoKey) obj;
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

}
