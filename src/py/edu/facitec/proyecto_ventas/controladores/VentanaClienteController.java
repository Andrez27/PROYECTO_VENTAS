package py.edu.facitec.proyecto_ventas.controladores;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.proyecto_ventas.dao.ClienteDAO;
import py.edu.facitec.proyecto_ventas.interfaces.AccionesABM;
import py.edu.facitec.proyecto_ventas.modelo.Cliente;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;
import py.edu.facitec.proyecto_ventas.vista.abm.VentanaCliente;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaCliente;

public class VentanaClienteController implements AccionesABM{

	private VentanaCliente ventanaCliente;
	private Cliente cliente;
	private ClienteDAO dao;
	private List<Cliente> lista;
	private ModeloTablaCliente mtCliente;
	private String accion;

	public VentanaClienteController(VentanaCliente ventanaCliente) {
		this.ventanaCliente = ventanaCliente;

		mtCliente = new ModeloTablaCliente();
		this.ventanaCliente.getTable().setModel(mtCliente);

		//Settear la implementacion de la iterfaz
		this.ventanaCliente.getMiToolBar().setAcciones(this);

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
		cliente = lista.get(posicion);
		ventanaCliente.gettNombre().setText(cliente.getNombre());
		ventanaCliente.gettApellido().setText(cliente.getApellido());
		ventanaCliente.gettDocumento().setText(cliente.getDocumento());
		ventanaCliente.gettTelefono().setText(cliente.getTelefono());
		ventanaCliente.gettEmail().setText(cliente.getEmail());
		//Se usa con jformattedtextfield (campos con formato como fecha)
		ventanaCliente.gettFechaRegistro().setValue(
				FechaUtil.convertirDateUtilAString(cliente.getFechaRegistro()));
		ventanaCliente.getcActivo().setSelected(cliente.getActivo());
	}

	private void vaciarCampos() {
		ventanaCliente.gettNombre().setText("");
		ventanaCliente.gettApellido().setText("");
		ventanaCliente.gettDocumento().setText("");
		ventanaCliente.gettTelefono().setText("");
		ventanaCliente.gettEmail().setText("");
		//Se usa con jformattedtextfield (campos con formato como fecha)
		ventanaCliente.gettFechaRegistro().setValue(null);
		ventanaCliente.getcActivo().setSelected(false);
	}

	private void recuperarTodo() {
		dao = new ClienteDAO();
		lista = dao.recuperarTodo();//recuperamos todos los clientes de la bd
		mtCliente.setLista(lista);//cargamos los clientes a la tabla
		mtCliente.fireTableDataChanged();//se actualiza la tabla
	}

	//inicial = true || no inicial = false
	private void estadoInicial(boolean b) {
		ventanaCliente.getMiToolBar().getNuevo().setEnabled(b);
		ventanaCliente.getMiToolBar().getModificar().setEnabled(b);
		ventanaCliente.getMiToolBar().getEliminar().setEnabled(b);
		ventanaCliente.getMiToolBar().getGuardar().setEnabled(!b);
		ventanaCliente.getMiToolBar().getCancelar().setEnabled(!b);

		ventanaCliente.gettNombre().setEnabled(!b);
		ventanaCliente.gettApellido().setEnabled(!b);
		ventanaCliente.gettDocumento().setEnabled(!b);
		ventanaCliente.gettEmail().setEnabled(!b);
		ventanaCliente.gettTelefono().setEnabled(!b);
		ventanaCliente.gettFechaRegistro().setEnabled(!b);
		ventanaCliente.getcActivo().setEnabled(!b);

		ventanaCliente.getTable().setEnabled(b);
		if(!b){
			//limia la selecion de la tabla cuando se acciona nuevo o midificar
			ventanaCliente.getTable().clearSelection();
		}
	}

	@Override
	public void nuevo() {
		estadoInicial(false);
		vaciarCampos();
		ventanaCliente.gettFechaRegistro().setValue(
				FechaUtil.convertirDateUtilAString(new Date()));
		accion = "NUEVO";
	}

	@Override
	public void modificar() {
		estadoInicial(false);
		accion = "MODIFICAR";
	}

	@Override
	public void eliminar() {
		int posicion = ventanaCliente.getTable().getSelectedRow();
		if(posicion < 0){
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, 
				"Estas seguro que deseas eliminar el clinte "
						+cliente.getNombre()+" "+cliente.getApellido(),
						"ATENCION",
						JOptionPane.YES_NO_OPTION);

		if(respuesta == JOptionPane.YES_OPTION){
			dao= new ClienteDAO();
			try {
				dao.eliminar(cliente);
				dao.commit();
				vaciarCampos();
				recuperarTodo();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void guardar() {
		if(accion.equals("NUEVO")){
			cliente = new Cliente();
		}

		cliente.setNombre(ventanaCliente.gettNombre().getText());
		cliente.setApellido(ventanaCliente.gettApellido().getText());
		cliente.setDocumento(ventanaCliente.gettDocumento().getText());
		cliente.setTelefono(ventanaCliente.gettTelefono().getText());
		cliente.setEmail(ventanaCliente.gettEmail().getText());
		cliente.setFechaRegistro(FechaUtil.convertirStringADateUtil(
				ventanaCliente.gettFechaRegistro().getText()));
		cliente.setActivo(ventanaCliente.getcActivo().isSelected());

		dao = new ClienteDAO();
		try {
			if(accion.equals("NUEVO")){
				dao.insertar(cliente);
			}else{
				dao.modificar(cliente);
			}			
			dao.commit();
			estadoInicial(true);
			recuperarTodo();
			vaciarCampos();
		} catch (Exception e) {
			dao.rollback();
			JOptionPane.showMessageDialog(null, 
					"Se produjo un error al guardar el cliente",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void cancelar() {
		estadoInicial(true);
	}

	@Override
	public void salir() {
		ventanaCliente.dispose();
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
