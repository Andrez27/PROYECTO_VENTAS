package py.edu.facitec.proyecto_ventas.controladores;

import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.proyecto_ventas.dao.ProductoDAO;
import py.edu.facitec.proyecto_ventas.interfaces.AccionesABM;
import py.edu.facitec.proyecto_ventas.modelo.Producto;
import py.edu.facitec.proyecto_ventas.vista.abm.VentanaProducto;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaProducto;

public class VentanaProductoController implements AccionesABM{

	private VentanaProducto ventanaProducto;
	private Producto producto;
	private ProductoDAO dao;
	private List<Producto> lista;
	private ModeloTablaProducto mtProducto;
	private String accion;

	public VentanaProductoController(VentanaProducto ventanaProducto) {
		this.ventanaProducto = ventanaProducto;

		mtProducto = new ModeloTablaProducto();
		this.ventanaProducto.getTable().setModel(mtProducto);

		//Settear la implementacion de la iterfaz
		this.ventanaProducto.getMiToolBar().setAcciones(this);

		estadoInicial(true);	

		recuperarTodo();

		/*this.ventanaCliente.getMiToolBar().getNuevo().addActionListener(this);
		this.ventanaCliente.getMiToolBar().getModificar().addActionListener(this);
		this.ventanaCliente.getMiToolBar().getEliminar().addActionListener(this);
		this.ventanaCliente.getMiToolBar().getGuardar().addActionListener(this);
		this.ventanaCliente.getMiToolBar().getCancelar().addActionListener(this);
		this.ventanaCliente.getMiToolBar().getSalir().addActionListener(this);*/
	}

	//cargar los datos de un registro seleccionado al formulario
	public void cargarFormulario(int posicion) {
		if(posicion < 0){
			return;//si la posicion es incorrecta cancela la ejecucion
		}
		producto = lista.get(posicion);
		ventanaProducto.gettDescripcion().setText(producto.getDescripcion());
		ventanaProducto.gettCodigoBarra().setText(producto.getCodigoBarra());
		ventanaProducto.gettPrecioCompra().setText(producto.getPrecioCompra()+"");
		ventanaProducto.gettPrecioVenta().setText(producto.getPrecioVenta()+"");
	}

	private void vaciarCampos() {
		ventanaProducto.gettDescripcion().setText("");
		ventanaProducto.gettCodigoBarra().setText("");
		ventanaProducto.gettPrecioCompra().setText("");
		ventanaProducto.gettPrecioVenta().setText("");
	}

	private void recuperarTodo() {
		dao = new ProductoDAO();
		lista = dao.recuperarTodo();//recuperamos todos los clientes de la bd
		mtProducto.setLista(lista);//cargamos los clientes a la tabla
		mtProducto.fireTableDataChanged();//se actualiza la tabla
	}

	//inicial = true || no inicial = false
	private void estadoInicial(boolean b) {
		ventanaProducto.getMiToolBar().getNuevo().setEnabled(b);
		ventanaProducto.getMiToolBar().getModificar().setEnabled(b);
		ventanaProducto.getMiToolBar().getEliminar().setEnabled(b);
		ventanaProducto.getMiToolBar().getGuardar().setEnabled(!b);
		ventanaProducto.getMiToolBar().getCancelar().setEnabled(!b);

		ventanaProducto.gettDescripcion().setEnabled(!b);
		ventanaProducto.gettCodigoBarra().setEnabled(!b);
		ventanaProducto.gettPrecioCompra().setEnabled(!b);
		ventanaProducto.gettPrecioVenta().setEnabled(!b);
		ventanaProducto.getTable().setEnabled(b);
		if(!b){
			//limia la selecion de la tabla cuando se acciona nuevo o midificar
			ventanaProducto.getTable().clearSelection();
		}
	}

	@Override
	public void nuevo() {
		estadoInicial(false);
		vaciarCampos();
		accion = "NUEVO";
	}

	@Override
	public void modificar() {
		estadoInicial(false);
		accion = "MODIFICAR";
	}

	@Override
	public void eliminar() {
		int posicion = ventanaProducto.getTable().getSelectedRow();
		if(posicion<0){
			JOptionPane.showMessageDialog(null, 
					"Debes seleccionar un registro!",
					"ATENCIÓN",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, 
				"Estas seguro que deseas eliminar el producto "
						+producto.getDescripcion()+"?",
						"ATENCIÓN",
						JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.YES_OPTION){
			dao = new ProductoDAO();
			try {
				dao.eliminar(producto);
				dao.commit();
				vaciarCampos();
				recuperarTodo();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, 
						"No se pudo eliminar porque el registro esta en uso!",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				dao.rollback();
			}
		}
	}

	@Override
	public void guardar() {
		if(accion.equals("NUEVO")){
			producto = new Producto();
		}
		producto.setDescripcion(ventanaProducto.gettDescripcion().getText());
		producto.setCodigoBarra(ventanaProducto.gettCodigoBarra().getText());
		producto.setPrecioCompra(
				Double.parseDouble(ventanaProducto.gettPrecioCompra().getText()));
		producto.setPrecioVenta(
				Double.parseDouble(ventanaProducto.gettPrecioVenta().getText()));	


		dao = new ProductoDAO();
		try {
			if(accion.equals("NUEVO")){
				dao.insertar(producto);
			}else{
				dao.modificar(producto);
			}
			dao.commit();
			recuperarTodo();
			estadoInicial(true);
			vaciarCampos();
		} catch (Exception e) {
			dao.rollback();
			JOptionPane.showMessageDialog(null, 
					"Se produjo un error al guardar el producto","Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void cancelar() {
		estadoInicial(true);
	}

	@Override
	public void salir() {
		ventanaProducto.dispose();
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Nuevo":

			break;
		case "Modificar":

			break;
		case "Eliminar":

			break;
		case "Guardar":

			break;
		case "Cancelar":

			break;
		case "Salir":

			break;
		default:
			break;
		}
	}*/




}
