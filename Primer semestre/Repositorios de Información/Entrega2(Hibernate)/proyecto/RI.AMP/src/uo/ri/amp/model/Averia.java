package uo.ri.amp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.amp.model.types.AveriaStatus;

@Entity
@Table(name = "TAverias")
public class Averia {

	@Id
	@GeneratedValue
	private Long id;
	private String descripcion;
	private Date fecha;
	private Double importe = 0.0;
	@Enumerated(EnumType.STRING)
	private AveriaStatus status = AveriaStatus.ABIERTA;

	@ManyToOne
	private Vehiculo vehiculo;
	@OneToMany(mappedBy = "averia")
	private Set<Intervencion> intervenciones = new HashSet<Intervencion>();
	@ManyToOne
	private Mecanico mecanico;
	@ManyToOne
	private Factura factura;

	Averia() {
	}

	public Averia(Date fecha, Vehiculo vehiculo) {
		super();
		this.fecha = fecha;
		Association.Averiar.link(vehiculo, this);
		this.vehiculo = vehiculo;
	}

	public Averia(Vehiculo vehiculo, String descripcion) {
		this(new Date(), vehiculo);
		this.descripcion = descripcion;
	}

	public Averia(Vehiculo vehiculo, String descripcion, Date fecha) {
		this(fecha, vehiculo);
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getImporte() {
		calcularImporte();
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public void setStatus(AveriaStatus status) {
		this.status = status;
	}

	public Date getFecha() {
		return fecha;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((vehiculo == null) ? 0 : vehiculo.hashCode());
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
		Averia other = (Averia) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Averia [descripcion=" + descripcion + ", fecha=" + fecha
				+ ", importe=" + importe + ", status=" + status + ", vehiculo="
				+ vehiculo + "]";
	}

	Set<Intervencion> _getIntervenciones() {
		return this.intervenciones;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Factura getFactura() {
		return factura;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<Intervencion>(intervenciones);
	}

	public void calcularImporte() {
		importe = 0.0;
		for (Intervencion intervencion : intervenciones) {
			this.importe += intervencion.getImporte();
		}
	}

	public Long getId() {
		return id;
	}

}
