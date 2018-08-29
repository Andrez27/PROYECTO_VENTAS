package py.edu.facitec.proyecto_ventas.controladores;

import java.util.List;

import py.edu.facitec.proyecto_ventas.dao.ProductoDAO;
import py.edu.facitec.proyecto_ventas.interfaces.BuscadorProductoInterace;
import py.edu.facitec.proyecto_ventas.modelo.Producto;
import py.edu.facitec.proyecto_ventas.vista.buscadores.BuscadorProducto;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaProducto;

public class BuscadorProductoController {
	
	private BuscadorProducto buscadorProducto;
	private ModeloTablaProducto mtProducto;
	private ProductoDAO dao;
	private List<Producto> lista;
	private BuscadorProductoInterace interfaz;
	
	public void setInterfaz(BuscadorProductoInterace interfaz) {
		this.interfaz = interfaz;
	}
	
	public BuscadorProductoController(BuscadorProducto buscadorProducto) {
		this.buscadorProducto = buscadorProducto;
		
		mtProducto = new ModeloTablaProducto();
		this.buscadorProducto.getTable().setModel(mtProducto);
	}
	
	public void recuperarPorFiltros() {
		dao = new ProductoDAO();
		lista = dao.recuperarPorFiltro(buscadorProducto.getjBuscador().getText());
		mtProducto.setLista(lista);
		mtProducto.fireTableDataChanged();
	}
	
	public void seleccionarRegistro(int index) {
		if (index < 0) {
			return;
		}
		interfaz.setProducto(lista.get(index));
		buscadorProducto.dispose();
	}

}
