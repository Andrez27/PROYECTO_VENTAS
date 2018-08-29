package py.edu.facitec.proyecto_ventas.dao;

import java.util.List;

import py.edu.facitec.proyecto_ventas.modelo.Cliente;
import py.edu.facitec.proyecto_ventas.modelo.Producto;

public class ProductoDAO extends GenericDAO<Producto>{
	
	public ProductoDAO() {
		super(Producto.class);
	}
	
	public List<Producto> recuperarPorFiltro(String filtro) {
		session.beginTransaction();
		//Usamos HQL (Hibernate Query Language)
		String sentencia = "from Producto "
				+ "where upper(descripcion) like :descri "
				+ "or codigoBarra = :codBarra "
				+ "or id = :id "
				+ "order by id";
		query = session.createQuery(sentencia);
		query.setParameter("descri", "%"+filtro.toUpperCase()+"%");
		query.setParameter("codBarra", filtro);
		
		//En caso de que se tenga que convertir a int un filtro string
		int id = 0;
		try {
			id = Integer.parseInt(filtro);
		} catch (Exception e) {}
		query.setParameter("id", id);
		
		List<Producto> lista = query.getResultList();
		commit();
		
		return lista;
	}
	
}
