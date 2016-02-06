package uo.ri.amp.business.impl.admin;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import uo.ri.amp.business.impl.Comand;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.Calificacion;
import uo.ri.amp.persistence.AsistenciasFinder;
import uo.ri.amp.persistence.CursosFinder;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.amp.persistence.util.Jpa;
import uo.ri.amp.util.SantiUtil;

public class AltaAsistencia implements Comand {

	private Long mecanicoID;
	private String codigo;
	private Calificacion calificacion;
	private Date fechaInicio, fechaFin;
	private int asistencia;

	public AltaAsistencia(Long mecanico, String codigo,
			Calificacion calificacion, Date fechaInicio, Date fechaFin,
			int asistencia) {
		this.mecanicoID = mecanico;
		this.codigo = codigo;
		this.calificacion = calificacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.asistencia = asistencia;
	}

	@Override
	public Object execute() throws BusinessException {

		EntityManager eM = Jpa.getManager();

		Curso curso = CursosFinder.buscarPorCodigo(codigo);
		Mecanico mecanico = MecanicoFinder.findById(mecanicoID);
		
		boolean insertada = false;
		try{
			
			AsistenciasFinder.buscarPorCursoYMecanico(codigo, mecanicoID);
			
		} catch(NoResultException e){
			insertada = true;
			SantiUtil.checkFechaPosterior(fechaInicio, fechaFin);
			SantiUtil.checkPorcentaje(asistencia);
			SantiUtil.checkCalificacion(asistencia, calificacion);

			Asistencia asistenciaObjeto = new Asistencia(curso, mecanico,
					fechaInicio, fechaFin, asistencia, calificacion);
			eM.persist(asistenciaObjeto);
		}

		if(!insertada){
			throw new BusinessException("Ese mecanico ya ha asistido a este curso.");
		}

		return null;
	}

}
