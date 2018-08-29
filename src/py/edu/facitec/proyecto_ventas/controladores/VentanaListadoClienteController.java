package py.edu.facitec.proyecto_ventas.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import py.edu.facitec.proyecto_ventas.dao.ClienteDAO;
import py.edu.facitec.proyecto_ventas.listados.VentanaListadoCliente;
import py.edu.facitec.proyecto_ventas.modelo.Cliente;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;
import py.edu.facitec.proyecto_ventas.util.ReporteUtil;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaCliente;

public class VentanaListadoClienteController implements ActionListener{
	
	private VentanaListadoCliente vListadoCliente;
	private ClienteDAO dao;
	private List<Cliente> lista;
	private ModeloTablaCliente mtCliente;
	
	
	public VentanaListadoClienteController(VentanaListadoCliente vListadoCliente) {
		this.vListadoCliente = vListadoCliente;
		
		mtCliente = new ModeloTablaCliente();
		this.vListadoCliente.getTable().setModel(mtCliente);
	
		setUpEvents();
	}


	private void setUpEvents() {
		vListadoCliente.getBtnProcesar().addActionListener(this);
		vListadoCliente.getBtnImprimir().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Imprimir":
			imprimir();
			break;
		case "Procesar":
			procesar();
			break;
		default:
			break;
		}
	}

	
	private void procesar() {
		int idDesde = 0;
		int idHasta = 999999999;
		try {
			idDesde = Integer.parseInt(vListadoCliente.gettIdDesde().getText());
		} catch (Exception e) {}	
		try {
			idHasta = Integer.parseInt(vListadoCliente.gettIdHasta().getText());
		} catch (Exception e) {}		
		String nombreDesde = vListadoCliente.gettNombreDesde().getText();
		String nombreHasta = vListadoCliente.gettNombreHasta().getText() + "zzz";
		String apellidoDesde = vListadoCliente.gettApellidoDesde().getText();
		String apellidoHasta = vListadoCliente.gettApellidoHasta().getText() + "zzz";
		Date fechaRegistroDesde = FechaUtil.convertirStringADateUtil(vListadoCliente.gettFechaRegistroDesde().getText());
		Date fechaRegistroHasta = FechaUtil.convertirStringADateUtil(vListadoCliente.gettFechaRegistroHasta().getText());
		dao = new ClienteDAO();
		lista = dao.recuperarPorRangos(nombreDesde,nombreHasta,apellidoDesde,apellidoHasta,fechaRegistroDesde,fechaRegistroHasta,
				idDesde,idHasta,vListadoCliente.getCbOrden().getSelectedIndex());	
		mtCliente.setLista(lista);
		mtCliente.fireTableDataChanged();
	}

	private void imprimir() {	
		if (lista == null) {
			return;
		}
		String filtros = "Id:["+vListadoCliente.gettIdDesde().getText()+"]["+vListadoCliente.gettIdHasta().getText()+"] "
				+ "Nombre:["+vListadoCliente.gettNombreDesde().getText()+"]["+vListadoCliente.gettNombreHasta().getText()+"] "
				+ "Apellido:["+vListadoCliente.gettApellidoDesde().getText()+"]["+vListadoCliente.gettApellidoHasta().getText()+"] "
				+ "Fecha Registro:["+vListadoCliente.gettFechaRegistroDesde().getText()+"]["+vListadoCliente.gettFechaRegistroHasta().getText()+"] "
				+ "Orden:["+vListadoCliente.getCbOrden().getSelectedItem().toString()+"]";
		Map<String, Object> map = new HashMap<>();
		map.put("filtros", filtros);
		ReporteUtil.imprimir(lista, map, "clientes");
	}

}
