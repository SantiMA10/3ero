package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.CajonDesastreGateway;
import uo.ri.persistence.FacturasGateway;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;

public class FacturarReparaciones {
	
//	private static final String SQL_IMPORTE_REPUESTOS = 
//			"select sum(s.cantidad * r.precio) " +
//			"	from  TSustituciones s, TRepuestos r " +
//			"	where s.repuesto_id = r.id " +
//			"		and s.intervencion_averia_id = ?";
//		
//	private static final String SQL_IMPORTE_MANO_OBRA =
//			"select sum(i.minutos * tv.precioHora / 60) " +
//			"	from TAverias a, TIntervenciones i, TVehiculos v, TTiposVehiculo tv" +
//			"	where i.averia_id = a.id " +
//			"		and a.vehiculo_id = v.id" +
//			"		and v.tipo_id = tv.id" +
//			"		and a.id = ?" +
//			"		and a.status = 'TERMINADA'";

//	private static final String SQL_UPDATE_IMPORTE_AVERIA = 
//			"update TAverias set importe = ? where id = ?";

//	private static final String SQL_ULTIMO_NUMERO_FACTURA = 
//			"select max(numero) from TFacturas";

//	private static final String SQL_INSERTAR_FACTURA = 
//			"insert into TFacturas(numero, fecha, iva, importe, status) " +
//			"	values(?, ?, ?, ?, ?)";

//	private static final String SQL_VINCULAR_AVERIA_FACTURA = 
//			"update TAverias set factura_id = ? where id = ?";
//
//	private static final String SQL_ACTUALIZAR_ESTADO_AVERIA = 
//			"update TAverias set status = ? where id = ?";
//
//	private static final String SQL_VERIFICAR_ESTADO_AVERIA = 
//			"select status from TAverias where id = ?";

//  No utilizado en otro sitio
//	private static final String SQL_RECUPERAR_CLAVE_GENERADA = 
//			"select id from TFacturas where numero = ?";
		
	private Connection connection;	
	private List<Long> idsAveria;
	private FacturasGateway fG;
	private AveriasGateway aG;
	private CajonDesastreGateway cG;

	public FacturarReparaciones(List<Long> idsAveria) {
			this.idsAveria = idsAveria;
	}
	
	public Map<String, Object> execute(){
		
		Map<String, Object> datos = new HashMap<String, Object>();
		
		try {
			
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);
			
			fG = PersistenceFactory.getFacturasGateway();
			fG.setConnection(connection);
			aG = PersistenceFactory.getAveriasGateway();
			aG.setConnection(connection);
			cG = PersistenceFactory.getCajonDesastreGateway();
			cG.setConnection(connection);
			
			verificarAveriasTerminadas(idsAveria);

			long numeroFactura = generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			
			double totalFactura = calcularImportesAverias(idsAveria);
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva/100);
			importe = Round.twoCents(importe);
			
			long idFactura = crearFactura(numeroFactura, fechaFactura, iva, importe);
			vincularAveriasConFactura(idFactura, idsAveria);
			cambiarEstadoAverias(idsAveria, "FACTURADA");
			
			datos.put("numeroFactura", numeroFactura);
			datos.put("fechaFactura", fechaFactura);
			datos.put("totalFactura", totalFactura);
			datos.put("iva", iva);
			datos.put("importe", importe);
 
			connection.commit();
		}
		catch (SQLException e) {
			try { connection.rollback(); } catch (SQLException ex) {};
			throw new RuntimeException(e);
		}
		catch (BusinessException e) {
			try { connection.rollback(); } catch (SQLException ex) {};
			try {
				throw e;
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			Jdbc.close(connection);
		}
		
		return datos;
		
	}
	
	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
	}
	
	protected double calcularImportesAverias(List<Long> idsAveria)
			throws BusinessException, SQLException {
		
		double totalFactura = 0.0;
		for(Long idAveria : idsAveria) {
			double importeManoObra = consultaImporteManoObra(idAveria);
			double importeRepuestos = consultaImporteRepuestos(idAveria);
			double totalAveria = importeManoObra + importeRepuestos;
			
			actualizarImporteAveria(idAveria, totalAveria);
			
			totalFactura += totalAveria; 
		}
		return totalFactura;
	}
	
	private void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException {
		List<Map<String, Object>> averias = aG.findAll();
		
		for (Long idAveria : idsAveria) {
			boolean encontrada = false;
			
			for(Map<String, Object> averia : averias){
				if(idAveria == averia.get("id")){
					encontrada = true;
					if(!averia.get("status").equals("TERMINADA")){
						throw new BusinessException("No está terminada la avería " + idAveria);
					}
				}
			}
			
			if (!encontrada) {
				throw new BusinessException("No existe la averia " + idAveria);
			}
		}

	}
	
	private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException {
		
		List<Map<String, Object>> averias = aG.findAll();
		
		for (Long idAveria : idsAveria) {
			
			for(Map<String, Object> averia : averias){
				if(idAveria == averia.get("id")){
					averia.put("status", status);
					aG.update(averia);
				}
			}
			
		}
		
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException {
		
		List<Map<String, Object>> averias = aG.findAll();
		
		for (Long idAveria : idsAveria) {
			
			for(Map<String, Object> averia : averias){
				if(idAveria == averia.get("id")){
					averia.put("factura_id", idFactura);
					aG.update(averia);
				}
			}
			
		}
		
	}

	private long crearFactura(long numeroFactura, Date fechaFactura,
			double iva, double totalConIva) throws SQLException {
		
		HashMap<String, Object> factura = new HashMap<String, Object>();
		factura.put("numeroFactura", numeroFactura);
		factura.put("fechaFactura", fechaFactura);
		factura.put("iva", iva);
		factura.put("totalConIva", totalConIva);
		
		return fG.save(factura);
		
	}

//	private long getGeneratedKey(long numeroFactura) throws SQLException {
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//
//		try {
//			pst = connection.prepareStatement(SQL_RECUPERAR_CLAVE_GENERADA);
//			pst.setLong(1, numeroFactura);
//			rs = pst.executeQuery();
//			rs.next();
//
//			return rs.getLong(1);
//			
//		} finally {
//			Jdbc.close(rs, pst);
//		}
//	}

	private Long generarNuevoNumeroFactura() throws SQLException {
		return fG.getLastInvoiceNumber();
	}
	
	private void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException {
		
		Map<String, Object> averia = aG.findById(idAveria);
		averia.put("importe", totalAveria);
		
		aG.update(averia);
	}

	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		return cG.consultaImporteRepuestos(idAveria);
	}

	private double consultaImporteManoObra(Long idAveria) throws BusinessException, SQLException {
		return cG.consultaImporteManoObra(idAveria);
	}
	
}
