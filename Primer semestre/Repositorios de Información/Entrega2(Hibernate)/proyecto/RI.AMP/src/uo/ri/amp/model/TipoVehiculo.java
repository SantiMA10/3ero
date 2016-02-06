package uo.ri.amp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TTiposvehiculo")
public class TipoVehiculo {

	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private Double precioHora;
	@Column(name = "num_horas_experto")
	private Integer numHorasExperto;

	@OneToMany(mappedBy = "tipo")
	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
	@OneToMany(mappedBy = "tipo")
	private Set<Certificado> certificados = new HashSet<Certificado>();
	@OneToMany(mappedBy = "tipo")
	private List<PorcentajeTipo> porcentajeTipo = new ArrayList<PorcentajeTipo>();

	TipoVehiculo() {
	}

	public TipoVehiculo(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public TipoVehiculo(String nombre, double precioHora) {
		this(nombre);
		this.precioHora = precioHora;
	}

	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		TipoVehiculo other = (TipoVehiculo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoVehiculo [nombre=" + nombre + ", precioHora=" + precioHora
				+ "]";
	}

	public Set<Vehiculo> getVehiculos() {
		return new HashSet<Vehiculo>(vehiculos);
	}

	Set<Vehiculo> _getVehiculos() {
		return vehiculos;
	}

	public Set<Certificado> getCertificados() {
		return new HashSet<Certificado>(certificados);
	}

	Set<Certificado> _getCertificados() {
		return certificados;
	}

	public List<PorcentajeTipo> getPorcentajeTipo() {
		return new ArrayList<PorcentajeTipo>(porcentajeTipo);
	}

	List<PorcentajeTipo> _getPorcentajeTipo() {
		return porcentajeTipo;
	}

	public Integer getNumHorasExperto() {
		return numHorasExperto;
	}

	public void setNumHorasExperto(Integer numHorasExperto) {
		this.numHorasExperto = numHorasExperto;
	}

}
