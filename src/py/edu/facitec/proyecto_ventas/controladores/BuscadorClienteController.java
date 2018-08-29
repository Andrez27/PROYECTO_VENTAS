package py.edu.facitec.proyecto_ventas.controladores;

import java.util.List;

import py.edu.facitec.proyecto_ventas.dao.ClienteDAO;
import py.edu.facitec.proyecto_ventas.interfaces.BuscadorClienteInterace;
import py.edu.facitec.proyecto_ventas.modelo.Cliente;
import py.edu.facitec.proyecto_ventas.vista.buscadores.BuscadorCliente;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaCliente;

public class BuscadorClienteController {
	
	private BuscadorCliente buscadorCliente;
	private ModeloTablaCliente mtCliente;
	private ClienteDAO dao;
	private List<Cliente> lista;
	private BuscadorClienteInterace interfaz;
	
	public void setInterfaz(BuscadorClienteInterace interfaz) {
		this.interfaz = interfaz;
	}
	
	public BuscadorClienteController(BuscadorCliente buscadorCliente) {
		this.buscadorCliente = buscadorCliente;
		
		mtCliente = new ModeloTablaCliente();
		this.buscadorCliente.getTable().setModel(mtCliente);
		
		dao = new ClienteDAO();
		System.out.println(dao.recuperaUltimoId());
	}
	
	public void recuperarPorFiltros() {
		dao = new ClienteDAO();
		lista = dao.recuperarPorFiltro(buscadorCliente.getjBuscador().getText());
		mtCliente.setLista(lista);
		mtCliente.fireTableDataChanged();
	}
	
	public void seleccionarRegistro(int index) {
		if (index < 0) {
			return;
		}
		interfaz.setCliente(lista.get(index));
		buscadorCliente.dispose();
	}

}
