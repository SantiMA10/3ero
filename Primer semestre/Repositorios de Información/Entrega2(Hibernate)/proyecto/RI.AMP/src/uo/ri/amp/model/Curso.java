package uo.ri.amp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCurso")
public class Curso {

	@Id
	@GeneratedValue
	private Long id;
	private String codigo;
	private String nombre;
	private String descripcion;
	@Column(name = "num_horas")
	private int numHoras;

	@OneToMany(mappedBy = "curso")
	private Set<Asistencia> asistencias = new HashSet<Asistencia>();
	@OneToMany(mappedBy = "curso")
	private Set<PorcentajeTipo> porcentajeTipo = new HashSet<PorcentajeTipo>();

	public Curso(String codigo, String nombre, String descripcion, int numHoras) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numHoras = numHoras;
	}

	Curso() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNumHoras() {
		return numHoras;
	}

	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}

	public Long getId() {
		return id;
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
		Curso other = (Curso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", numHoras=" + numHoras
				+ "]";
	}

	protected Set<Asistencia> _getAsistencias() {
		return asistencias;
	}

	public Set<Asistencia> getAsistencia() {
		return new HashSet<Asistencia>(asistencias);
	}

	protected Set<PorcentajeTipo> _getPorcentajeTipo() {
		return porcentajeTipo;
	}

	public Set<PorcentajeTipo> getPorcentajeTipo() {
		return new HashSet<PorcentajeTipo>(porcentajeTipo);
	}

}
