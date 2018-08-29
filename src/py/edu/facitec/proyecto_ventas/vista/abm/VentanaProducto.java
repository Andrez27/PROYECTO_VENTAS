package py.edu.facitec.proyecto_ventas.vista.abm;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.facitec.proyecto_ventas.controladores.VentanaClienteController;
import py.edu.facitec.proyecto_ventas.controladores.VentanaProductoController;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;
import py.edu.facitec.proyecto_ventas.vista.componentes.VentanaGenerica;
import py.edu.facitec.proyecto_ventas.vista.modelo_tabla.ModeloTablaCliente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaProducto extends VentanaGenerica {
	private JTextField tDescripcion;
	private JTextField tCodigoBarra;
	private JTextField tPrecioCompra;
	private VentanaProductoController controller;
	private JTextField tPrecioVenta;
	private JLabel lblCBarra;
	private JLabel lblPCompra;
	private JLabel lblPVenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProducto dialog = new VentanaProducto();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setupController();//inicializar controlador
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public VentanaProducto() {
		getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//se pasa a cargar formulario la posicion seleccionada
				controller.cargarFormulario(getTable().getSelectedRow());
			}
		});
		
		/*getMiToolBar().getNuevo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});*/
		
		
		getTituloFormulario().setText("Datos del Cliente");
		
		tDescripcion = new JTextField();
		tDescripcion.setBounds(113, 10, 250, 19);
		getPanelFormulario().add(tDescripcion);
		tDescripcion.setColumns(10);
		
		tCodigoBarra = new JTextField();
		tCodigoBarra.setBounds(113, 37, 250, 19);
		getPanelFormulario().add(tCodigoBarra);
		tCodigoBarra.setColumns(10);
		
		tPrecioCompra = new JTextField();
		tPrecioCompra.setBounds(113, 64, 101, 19);
		getPanelFormulario().add(tPrecioCompra);
		tPrecioCompra.setColumns(10);
		
		tPrecioVenta = new JTextField();
		tPrecioVenta.setBounds(113, 91, 101, 19);
		getPanelFormulario().add(tPrecioVenta);
		tPrecioVenta.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(12, 12, 90, 15);
		getPanelFormulario().add(lblDescripcion);
		
		lblCBarra = new JLabel("C. Barra");
		lblCBarra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCBarra.setBounds(12, 39, 90, 15);
		getPanelFormulario().add(lblCBarra);
		
		lblPCompra = new JLabel("P. Compra");
		lblPCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPCompra.setBounds(12, 66, 90, 15);
		getPanelFormulario().add(lblPCompra);
		
		lblPVenta = new JLabel("P. Venta");
		lblPVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPVenta.setBounds(12, 93, 90, 15);
		getPanelFormulario().add(lblPVenta);

		
	}
	
	//Aqui indicamos cual es el controlador de la ventana
	public void setupController(){
		controller = new VentanaProductoController(this);
	}

	public JTextField gettDescripcion() {
		return tDescripcion;
	}

	public JTextField gettCodigoBarra() {
		return tCodigoBarra;
	}

	public JTextField gettPrecioCompra() {
		return tPrecioCompra;
	}

	public JTextField gettPrecioVenta() {
		return tPrecioVenta;
	}

	
}
