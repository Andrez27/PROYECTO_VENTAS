package py.edu.facitec.proyecto_ventas.vista.transacciones;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import py.edu.facitec.proyecto_ventas.controladores.TransaccionVentaController;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransaccionVenta extends JDialog {
	private JTextField tCodigo;
	private JTextField tCliente;
	private JTable table;
	private JFormattedTextField tFecha;
	private JButton btnBuscarCliente;
	private JComboBox cCondicion;
	private JTextField tProducto;
	private JButton btnBuscarProducto;
	private JTextField tCantidad;
	private JButton btnGuardar;
	private JButton btnQuitar;
	private JButton btnAgregar;
	private TransaccionVentaController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransaccionVenta dialog = new TransaccionVenta();
					dialog.setUpController();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setUpController() {
		controller = new TransaccionVentaController(this);
	}

	/**
	 * Create the dialog.
	 */
	public TransaccionVenta() {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNro = new JLabel("Nro");
		lblNro.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNro);
		
		tCodigo = new JTextField();
		tCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.recuperarVentaPorId();
				}
			}
		});
		tCodigo.setBounds(10, 36, 86, 20);
		getContentPane().add(tCodigo);
		tCodigo.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(138, 11, 46, 14);
		getContentPane().add(lblCliente);
		
		tCliente = new JTextField();
		tCliente.setEnabled(false);
		tCliente.setBounds(136, 36, 286, 20);
		getContentPane().add(tCliente);
		tCliente.setColumns(10);
		
		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setActionCommand("BuscarCliente");
		btnBuscarCliente.setBounds(432, 35, 89, 23);
		getContentPane().add(btnBuscarCliente);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(534, 11, 46, 14);
		getContentPane().add(lblFecha);
		
		tFecha = new JFormattedTextField(FechaUtil.getMascara());
		tFecha.setBounds(531, 36, 110, 20);
		getContentPane().add(tFecha);
		
		JLabel lblTipoPago = new JLabel("Tipo pago");
		lblTipoPago.setBounds(673, 11, 86, 14);
		getContentPane().add(lblTipoPago);
		
		cCondicion = new JComboBox();
		cCondicion.setModel(new DefaultComboBoxModel(new String[] {"Contado", "Cr\u00E9dito"}));
		cCondicion.setBounds(673, 36, 101, 20);
		getContentPane().add(cCondicion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 84, 764, 381);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 682, 277);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 11, 46, 14);
		panel.add(lblProducto);
		
		tProducto = new JTextField();
		tProducto.setEnabled(false);
		tProducto.setBounds(10, 37, 306, 20);
		panel.add(tProducto);
		tProducto.setColumns(10);
		
		btnBuscarProducto = new JButton("Buscar");
		btnBuscarProducto.setActionCommand("BuscarProducto");
		btnBuscarProducto.setBounds(322, 36, 89, 23);
		panel.add(btnBuscarProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(437, 11, 46, 14);
		panel.add(lblCantidad);
		
		tCantidad = new JTextField();
		tCantidad.setBounds(437, 37, 86, 20);
		panel.add(tCantidad);
		tCantidad.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(533, 34, 89, 23);
		panel.add(btnAgregar);
		
		btnQuitar = new JButton("X");
		btnQuitar.setActionCommand("Quitar");
		btnQuitar.setBounds(708, 74, 46, 23);
		panel.add(btnQuitar);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(10, 67, 46, 14);
		getContentPane().add(lblDetalle);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(685, 491, 89, 23);
		getContentPane().add(btnGuardar);

	}

	public JTextField gettProducto() {
		return tProducto;
	}

	public void settProducto(JTextField tProducto) {
		this.tProducto = tProducto;
	}

	public JTextField gettCodigo() {
		return tCodigo;
	}

	public void settCodigo(JTextField tCodigo) {
		this.tCodigo = tCodigo;
	}

	public JTextField gettCliente() {
		return tCliente;
	}

	public void settCliente(JTextField tCliente) {
		this.tCliente = tCliente;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JFormattedTextField gettFecha() {
		return tFecha;
	}

	public void settFecha(JFormattedTextField tFecha) {
		this.tFecha = tFecha;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JComboBox getcCondicion() {
		return cCondicion;
	}

	public void setcCondicion(JComboBox cCondicion) {
		this.cCondicion = cCondicion;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

	public JTextField gettCantidad() {
		return tCantidad;
	}

	public void settCantidad(JTextField tCantidad) {
		this.tCantidad = tCantidad;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}
	
	
}
