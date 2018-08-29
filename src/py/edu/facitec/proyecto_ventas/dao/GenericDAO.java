package py.edu.facitec.proyecto_ventas.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import py.edu.facitec.proyecto_ventas.util.ConnectionHelper;

public class GenericDAO <T>{
	protected Class<T> clase;
	protected Session session;
	protected Query<T> query;
	
	public GenericDAO(Class<T> clase) {
		this.clase = clase;
		ConnectionHelper.getSessionFactory().openSession();
		session = ConnectionHelper.getSessionFactory().getCurrentSession();
	}
	
	public void insertar(T entity) throws Exception{
		session.beginTransaction();
		session.save(entity);
	}
	
	public void modificar(T entity) throws Exception{
		session.beginTransaction();
		session.update(entity);
	}
	
	public void eliminar(T entity) throws Exception{
		session.beginTransaction();
		session.delete(entity);
	}
	
	public T recuperaPorId(int id){
		session.beginTransaction();
		T entity = session.get(clase, id);
		commit();
		return entity;
	}
	
	public int recuperaUltimoId(){
		session.beginTransaction();
		Query<Integer> query = session.createQuery("select max(id) from "+clase.getName());
		int maxId = 0;
		try {
			maxId = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		commit();
		return maxId;
	}
	
	public List<T> recuperarTodo(){
		session.beginTransaction();
		query = session.createQuery("from "+clase.getName()+" order by id");
		List<T> result = query.getResultList();
		commit();
		return result;
	}
	
	public void commit() { 
		session.flush();//insercion por lotes 
		session.getTransaction().commit();
	}
	
	public void rollback() {
		//cancela la ejecucion
		session.getTransaction().rollback();
	}
}
