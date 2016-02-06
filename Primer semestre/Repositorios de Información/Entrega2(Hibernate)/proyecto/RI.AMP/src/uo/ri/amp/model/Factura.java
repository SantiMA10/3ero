package uo.ri.amp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.amp.model.exception.BusinessException;
import uo.ri.amp.model.types.AveriaStatus;
import uo.ri.amp.model.types.FacturaStatus;
import alb.util.date.DateUtil;
import alb.util.math.Round;

@Entity
@Table(name = "TFacturas")
public class Factura {

	@Id
	@GeneratedValue
	private Long id;
	private Long numero;
	private Date fecha;
	private double importe;
	private double iva;
	@Enumerated(EnumType.STRING)
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;

	@OneToMany(mappedBy = "factura")
	private List<Averia> averias = new ArrayList<Averia>();
	@OneToMany(mappedBy = "factura")
	private Set<Cargo> cargos = new HashSet<Cargo>();

	Factura() {
	}

	public Factura(Long numero) {
		super();
		this.numero = numero;
		this.fecha = new Date();
	}

	public Factura(long numero, Date fecha) {
		this(numero);
		this.fecha = fecha;
	}

	public Factura(long numero, List<Averia> averias) throws BusinessException {
		this(numero);
		for (int i = 0; i < averias.size(); i++) {
			addAveria(averias.get(i));
		}
	}

	public Factura(long numero, Date fecha, List<Averia> averias)
			throws BusinessException {
		this(numero, fecha);
		for (int i = 0; i < averias.size(); i++) {
			addAveria(averias.get(i));
		}
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		calcularImporte();
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public void calcularImporte() {
		importe = 0;
		for (int i = 0; i < averias.size(); i++) {
			importe += averias.get(i).getImporte();
		}
		importe = Round.twoCents(importe * ((getIva() / 100) + 1));
	}

	public double getIva() {
		return DateUtil.fromString("1/7/2012").before(fecha) ? 21.0 : 18.0;
	}

	public FacturaStatus getStatus() {
		return status;
	}

	public void setStatus(FacturaStatus status) {
		this.status = status;
	}

	public Long getNumero() {
		return numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Factura other = (Factura) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", importe="
				+ importe + ", iva=" + iva + ", status=" + status + "]";
	}

	public void addAveria(Averia averia) throws BusinessException {
		if (averia.getStatus() != AveriaStatus.TERMINADA)
			throw new BusinessException("La averia añadida no esta terminada");
		averia.setStatus(AveriaStatus.FACTURADA);
		averia.calcularImporte();
		averias.add(averia);
		Association.Facturar.link(averia, this);
	}

	public Set<Averia> getAverias() {
		return new HashSet<Averia>(averias);
	}

	public void removeAveria(Averia averia) {
		averias.remove(averia);
		Association.Facturar.unlink(averia, this);
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
