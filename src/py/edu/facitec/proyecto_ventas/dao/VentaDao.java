package py.edu.facitec.proyecto_ventas.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.proyecto_ventas.modelo.Cliente;
import py.edu.facitec.proyecto_ventas.modelo.Venta;

public class VentaDao extends GenericDAO<Venta>{
	public VentaDao() {
		super(Venta.class);
	}

	public List<Venta> recuperarPorRangos(String ncDesde,String ncHasta, String acDesde,String acHasta,
											Date fDesde,Date fHasta, int idDesde, int idHasta, int indiceOrden) {
		String[] opcionesOrden = {"id","cliente.nombre","cliente.apellido","fecha"};
		session.beginTransaction();
		String sql = "from Venta where cliente.nombre between :nDesde and :nHasta "
				+ "and cliente.apellido between :aDesde and :aHasta "
				+ "and (fecha >= :fDesde or :fDesdeNula = true) "
				+ "and (fecha <= :fHasta or :fHastaNula = true) "
				+ "and id between :idDesde and :idHasta "
				+ "order by "+opcionesOrden[indiceOrden];
		
		Query<Venta> query = session.createQuery(sql);
		query.setParameter("nDesde", ncDesde);
		query.setParameter("nHasta", ncHasta);
		query.setParameter("aDesde", acDesde);
		query.setParameter("aHasta", acHasta);
		query.setParameter("fDesde", fDesde);
		query.setParameter("fDesdeNula", fDesde == null);
		query.setParameter("fHasta", fHasta);
		query.setParameter("fHastaNula", fHasta == null);
		query.setParameter("idDesde", idDesde);
		query.setParameter("idHasta", idHasta);
		
		List<Venta> lista = query.getResultList();
		commit();
		return lista;
	}
}
