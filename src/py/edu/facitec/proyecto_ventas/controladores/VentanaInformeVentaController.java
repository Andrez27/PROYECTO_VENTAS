package py.edu.facitec.proyecto_ventas.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import py.edu.facitec.proyecto_ventas.dao.VentaDao;
import py.edu.facitec.proyecto_ventas.informes.VentanaInformeVenta;
import py.edu.facitec.proyecto_ventas.modelo.Venta;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;
import py.edu.facitec.proyecto_ventas.util.ReporteUtil;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaVenta;

public class VentanaInformeVentaController implements ActionListener{
	
	private VentanaInformeVenta vListadoVenta;
	private VentaDao dao;
	private List<Venta> lista;
	private ModeloTablaVenta mtVenta;
	
	
	public VentanaInformeVentaController(VentanaInformeVenta vListadoVenta) {
		this.vListadoVenta = vListadoVenta;
		
		mtVenta = new ModeloTablaVenta();
		this.vListadoVenta.getTable().setModel(mtVenta);
	
		setUpEvents();
	}


	private void setUpEvents() {
		vListadoVenta.getBtnProcesar().addActionListener(this);
		vListadoVenta.getBtnImprimir().addActionListener(this);
		vListadoVenta.getBtnDetallado().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Imprimir":
			imprimir();
			break;
		case "ImprimirDetallado":
			imprimirDetallado();
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
			idDesde = Integer.parseInt(vListadoVenta.gettIdDesde().getText());
		} catch (Exception e) {}	
		try {
			idHasta = Integer.parseInt(vListadoVenta.gettIdHasta().getText());
		} catch (Exception e) {}		
		String nombreClienteDesde = vListadoVenta.gettNombreClienteDesde().getText();
		String nombreClienteHasta = vListadoVenta.gettNombreClienteHasta().getText() + "zzz";
		String apellidoClienteDesde = vListadoVenta.gettApellidoClienteDesde().getText();
		String apellidoClienteHasta = vListadoVenta.gettApellidoClienteHasta().getText() + "zzz";
		Date fechaDesde = FechaUtil.convertirStringADateUtil(vListadoVenta.gettFechaDesde().getText());
		Date fechaHasta = FechaUtil.convertirStringADateUtil(vListadoVenta.gettFechaHasta().getText());
		dao = new VentaDao();
		lista = dao.recuperarPorRangos(nombreClienteDesde,nombreClienteHasta,apellidoClienteDesde,apellidoClienteHasta,fechaDesde,fechaHasta,
				idDesde,idHasta,vListadoVenta.getCbOrden().getSelectedIndex());	
		mtVenta.setLista(lista);
		mtVenta.fireTableDataChanged();
	}

	private void imprimir() {	
		if (lista == null) {
			return;
		}
		String filtros = "Id:["+vListadoVenta.gettIdDesde().getText()+"]["+vListadoVenta.gettIdHasta().getText()+"] "
				+ "Nombre Cliente:["+vListadoVenta.gettNombreClienteDesde().getText()+"]["+vListadoVenta.gettNombreClienteHasta().getText()+"] "
				+ "Apellido Cliente:["+vListadoVenta.gettApellidoClienteDesde().getText()+"]["+vListadoVenta.gettApellidoClienteHasta().getText()+"] "
				+ "Fecha:["+vListadoVenta.gettFechaDesde().getText()+"]["+vListadoVenta.gettFechaHasta().getText()+"] "
				+ "Orden:["+vListadoVenta.getCbOrden().getSelectedItem().toString()+"]";
		Map<String, Object> map = new HashMap<>();
		map.put("filtros", filtros);
		ReporteUtil.imprimir(lista, map, "ventas");
	}
	
	private void imprimirDetallado() {
		if (lista == null) {
			return;
		}
		String filtros = "Id:["+vListadoVenta.gettIdDesde().getText()+"]["+vListadoVenta.gettIdHasta().getText()+"] "
				+ "Nombre Cliente:["+vListadoVenta.gettNombreClienteDesde().getText()+"]["+vListadoVenta.gettNombreClienteHasta().getText()+"] "
				+ "Apellido Cliente:["+vListadoVenta.gettApellidoClienteDesde().getText()+"]["+vListadoVenta.gettApellidoClienteHasta().getText()+"] "
				+ "Fecha:["+vListadoVenta.gettFechaDesde().getText()+"]["+vListadoVenta.gettFechaHasta().getText()+"] "
				+ "Orden:["+vListadoVenta.getCbOrden().getSelectedItem().toString()+"]";
		Map<String, Object> map = new HashMap<>();
		map.put("filtros", filtros);
		ReporteUtil.imprimir(lista, map, "ventas_detalladas");
	}


}
