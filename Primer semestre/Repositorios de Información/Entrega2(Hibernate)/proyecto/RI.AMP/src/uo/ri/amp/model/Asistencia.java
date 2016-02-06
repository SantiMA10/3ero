package uo.ri.amp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.amp.model.keys.AsistenciaKey;
import uo.ri.amp.model.types.Calificacion;

@Entity
@IdClass(AsistenciaKey.class)
@Table(name = "TAsistencia")
public class Asistencia {

	@Id
	@ManyToOne
	private Curso curso;
	@Id
	@ManyToOne
	private Mecanico mecanico;

	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	@Column(name = "fecha_fin")
	private Date fechaFin;
	private Integer asistencia;
	@Enumerated(EnumType.STRING)
	private Calificacion calificacion;

	public Asistencia(Curso curso, Mecanico mecanico, Date fechaInicio,
			Date fechaFin, Integer asistencia, Calificacion calificacion) {
		super();
		Association.Asistir.link(curso, mecanico, this);
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.asistencia = asistencia;
		this.calificacion = calificacion;
	}

	Asistencia() {
	};

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Integer asistencia) {
		this.asistencia = asistencia;
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	public Curso getCurso() {
		return curso;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
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
		Asistencia other = (Asistencia) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
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
		return "Asistencia [curso=" + curso + ", mecanico=" + mecanico
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", asistencia=" + asistencia + ", calificacion="
				+ calificacion + "]";
	}

	void _setCurso(Curso curso) {
		this.curso = curso;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

}
