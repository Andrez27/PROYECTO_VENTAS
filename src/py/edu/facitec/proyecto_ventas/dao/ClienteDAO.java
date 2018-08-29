package py.edu.facitec.proyecto_ventas.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.proyecto_ventas.modelo.Cliente;

public class ClienteDAO extends GenericDAO<Cliente>{
	public ClienteDAO() {
		super(Cliente.class);
	}
	
	public List<Cliente> recuperarPorFiltro(String filtro) {
		//HQL
		session.beginTransaction();
		String sql = "from Cliente where upper(nombre) like :nom or upper(apellido) like :ape "
				+ "or documento = :ced order by id ";
		Query<Cliente> query = session.createQuery(sql);
		query.setParameter("nom", "%"+filtro.toUpperCase()+"%");
		query.setParameter("ape", "%"+filtro.toUpperCase()+"%");
		query.setParameter("ced", filtro);
		List<Cliente> lista = query.getResultList();
		commit();
		return lista;
	}
	
	public List<Cliente> recuperarPorRangos(String nDesde, String nHasta, String aDesde, String aHasta, Date frDesde, Date frHasta, int idDesde,int idHasta,int indiceOrden) {
		String[] opcionesOrden = {"id","nombre","apellido","fechaRegistro"};
		session.beginTransaction();
		String sql = "from Cliente where nombre between :nDesde and :nHasta "
				+ "and apellido between :aDesde and :aHasta "
				+ "and (fechaRegistro >= :frDesde or :frDesdeNula = true) "
				+ "and (fechaRegistro <= :frHasta or :frHastaNula = true) "
				+ "and id between :idDesde and :idHasta "
				+ "order by "+opcionesOrden[indiceOrden];
		
		Query<Cliente> query = session.createQuery(sql);
		query.setParameter("nDesde", nDesde);
		query.setParameter("nHasta", nHasta);
		query.setParameter("aDesde", aDesde);
		query.setParameter("aHasta", aHasta);
		query.setParameter("frDesde", frDesde);
		query.setParameter("frDesdeNula", frDesde == null);
		query.setParameter("frHasta", frHasta);
		query.setParameter("frHastaNula", frHasta == null);
		query.setParameter("idDesde", idDesde);
		query.setParameter("idHasta", idHasta);
		
		List<Cliente> lista = query.getResultList();
		commit();
		return lista;
	}
}
