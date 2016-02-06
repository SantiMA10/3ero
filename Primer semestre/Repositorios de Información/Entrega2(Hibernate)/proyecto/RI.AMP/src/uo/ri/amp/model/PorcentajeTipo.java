package uo.ri.amp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.amp.model.keys.PorcentajeTipoKey;

@Entity
@IdClass(PorcentajeTipoKey.class)
@Table(name = "TPorcentajeTipo")
public class PorcentajeTipo {

	@Id
	@ManyToOne
	private TipoVehiculo tipo;
	@Id
	@ManyToOne
	private Curso curso;
	private Integer porcentaje;

	public PorcentajeTipo(TipoVehiculo tipo, Curso curso, Integer porcentaje) {
		super();
		this.porcentaje = porcentaje;
		Association.Impartir.link(tipo, curso, this);
	}

	@Override
	public String toString() {
		return "tipo=" + tipo.getNombre() + ",\tporcentaje=" + porcentaje + "%";
	}

	PorcentajeTipo() {
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public Curso getCurso() {
		return curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		PorcentajeTipo other = (PorcentajeTipo) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	void _setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	void _setCurso(Curso curso) {
		this.curso = curso;
	}

}
