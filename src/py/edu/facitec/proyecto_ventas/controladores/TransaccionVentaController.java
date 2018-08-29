package py.edu.facitec.proyecto_ventas.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.proyecto_ventas.dao.VentaDao;
import py.edu.facitec.proyecto_ventas.interfaces.BuscadorClienteInterace;
import py.edu.facitec.proyecto_ventas.interfaces.BuscadorProductoInterace;
import py.edu.facitec.proyecto_ventas.modelo.Cliente;
import py.edu.facitec.proyecto_ventas.modelo.Producto;
import py.edu.facitec.proyecto_ventas.modelo.Venta;
import py.edu.facitec.proyecto_ventas.modelo.VentaDetalle;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;
import py.edu.facitec.proyecto_ventas.vista.buscadores.BuscadorCliente;
import py.edu.facitec.proyecto_ventas.vista.buscadores.BuscadorProducto;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaVentaItem;
import py.edu.facitec.proyecto_ventas.vista.transacciones.TransaccionVenta;

public class TransaccionVentaController implements ActionListener,BuscadorClienteInterace,BuscadorProductoInterace{
	
	private TransaccionVenta transaccionVenta;
	private ModeloTablaVentaItem mtVentaItem;
	private String accion;
	private Producto producto;
	private Cliente cliente;
	private List<VentaDetalle> items;
	private Venta venta;
	private VentaDao dao;
	
	
	public TransaccionVentaController(TransaccionVenta transaccionVenta) {
		this.transaccionVenta = transaccionVenta;
		this.mtVentaItem = new ModeloTablaVentaItem();
		this.transaccionVenta.getTable().setModel(mtVentaItem);
		
		this.transaccionVenta.getBtnBuscarCliente().addActionListener(this);
		this.transaccionVenta.getBtnBuscarProducto().addActionListener(this);
		this.transaccionVenta.getBtnAgregar().addActionListener(this);
		this.transaccionVenta.getBtnQuitar().addActionListener(this);
		this.transaccionVenta.getBtnGuardar().addActionListener(this);
		
		nuevo();
	}
	
	private void nuevo() {
		transaccionVenta.gettFecha().setValue(FechaUtil.convertirDateUtilAString(new Date()));
		accion = "NUEVO";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "BuscarCliente":
			abrirBuscadorCliente();
			break;

		case "BuscarProducto":
			abrirBuscadorProducto();
			break;
		case "Agregar":
			agregarItem();
			break;
		case "Quitar":
			quitarItem();
			break;
		case "Guardar":
			guardar();
			break;
		}
	}
	
	private void guardar() {
		if (accion.equals("NUEVO")) {
			venta = new Venta();
		}
		
		if(!validarCampos()){
			return;
		}
		
		venta.setFecha(FechaUtil.convertirStringADateUtil(transaccionVenta.gettFecha().getText()));
		venta.setTipoPago(transaccionVenta.getcCondicion().getSelectedItem().toString());
		
		venta.setCliente(cliente);
		
		venta.setItems(items);
		
		for (int i = 0; i < items.size(); i++) {
			items.get(i).setVenta(venta);
		}
		
		dao = new VentaDao();
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(venta);
			}else {
				dao.modificar(venta);
			}
			dao.commit();
			vaciarFormulario();
		} catch (Exception e) {
			e.printStackTrace();
			dao.rollback();
		}
		
	}

	private void vaciarFormulario() {
		transaccionVenta.gettCodigo().setText("");
		transaccionVenta.gettCliente().setText("");
		transaccionVenta.getcCondicion().setSelectedIndex(0);
		transaccionVenta.gettFecha().setValue(FechaUtil.convertirDateUtilAString(new Date()));
		
		items = new ArrayList<>();
		mtVentaItem.setLista(items);
		mtVentaItem.fireTableDataChanged();
		
		accion = "NUEVO";
	}

	private boolean validarCampos() {
		if (transaccionVenta.gettCliente().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Seleccione un cliente");
			return false;
		}
		if (transaccionVenta.gettFecha().getValue() == null) {
			JOptionPane.showMessageDialog(null, "Fecha Obligatoria");
			return false;
		}
		if (items == null || items.size() ==0) {
			JOptionPane.showMessageDialog(null, "Cargue por lo menos un item");
			return false;
		}
		return true;
	}

	private void quitarItem() {
		if (transaccionVenta.getTable().getSelectedRow() < 0) {
			return;
		}
		items.remove(transaccionVenta.getTable().getSelectedRow());
		mtVentaItem.setLista(items);
		mtVentaItem.fireTableDataChanged();
	}

	private void agregarItem() {
		if (items == null) {
			items = new ArrayList<VentaDetalle>();
		}
		
		VentaDetalle detalle = new VentaDetalle();
		detalle.setProducto(producto);
		detalle.setPrecio(producto.getPrecioVenta());
		detalle.setCantidad(Integer.parseInt(transaccionVenta.gettCantidad().getText()));
		
		items.add(detalle);
		mtVentaItem.setLista(items);
		mtVentaItem.fireTableDataChanged();
		
		vaciarFormularioDetalle();
	}
	
	private void vaciarFormularioDetalle() {
		transaccionVenta.gettProducto().setText("");
		transaccionVenta.gettCantidad().setText("");
	}
	
	public void recuperarVentaPorId() {
		dao = new VentaDao();
		venta = dao.recuperaPorId(Integer.parseInt(transaccionVenta.gettCodigo().getText()));
		
		cargarFormulario();
	}

	private void cargarFormulario() {
		cliente = venta.getCliente();
		
		transaccionVenta.gettCliente().setText(venta.getCliente().getNombre()+" "+venta.getCliente().getApellido());
		transaccionVenta.gettFecha().setValue(FechaUtil.convertirDateUtilAString(venta.getFecha()));
		transaccionVenta.getcCondicion().setSelectedItem(venta.getTipoPago());
		
		items = venta.getItems();
		mtVentaItem.setLista(items);
		mtVentaItem.fireTableDataChanged();
		
		accion = "MODIFICAR";
	}

	private void abrirBuscadorCliente() {
		BuscadorCliente buscador = new BuscadorCliente();
		buscador.setupController();
		buscador.getController().setInterfaz(this);
		buscador.setVisible(true);
	}
	
	private void abrirBuscadorProducto() {
		BuscadorProducto buscador = new BuscadorProducto();
		buscador.setupController();
		buscador.getController().setInterfaz(this);
		buscador.setVisible(true);
	}

	@Override
	public void setProducto(Producto producto) {
		this.producto = producto;
		transaccionVenta.gettProducto().setText(producto.getDescripcion());
	}

	@Override
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		transaccionVenta.gettCliente().setText(cliente.getNombre()+" "+cliente.getApellido());
	}
}
