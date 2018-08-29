package py.edu.facitec.proyecto_ventas.informes;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import py.edu.facitec.proyecto_ventas.controladores.VentanaInformeVentaController;
import py.edu.facitec.proyecto_ventas.controladores.VentanaListadoClienteController;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaInformeVenta extends JDialog {
	private JTable table;
	private JTextField tNombreClienteDesde;
	private JTextField tNombreClienteHasta;
	private JTextField tApellidoClienteDesde;
	private JTextField tApellidoClienteHasta;
	private JFormattedTextField tFechaDesde;
	private JFormattedTextField tFechaHasta;
	private JButton btnImprimir;
	private JButton btnProcesar;
	private VentanaInformeVentaController controller;
	private JTextField tIdDesde;
	private JTextField tIdHasta;
	private JComboBox cbOrden;
	private JButton btnDetallado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInformeVenta dialog = new VentanaInformeVenta();
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
		controller = new VentanaInformeVentaController(this);
	}

	/**
	 * Create the dialog.
	 */
	public VentanaInformeVenta() {
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 764, 326);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNombre = new JLabel("Nombre Cliente");
		lblNombre.setBounds(145, 8, 156, 14);
		getContentPane().add(lblNombre);
		
		tNombreClienteDesde = new JTextField();
		tNombreClienteDesde.setBounds(145, 26, 156, 20);
		getContentPane().add(tNombreClienteDesde);
		tNombreClienteDesde.setColumns(10);
		
		tNombreClienteHasta = new JTextField();
		tNombreClienteHasta.setColumns(10);
		tNombreClienteHasta.setBounds(145, 50, 156, 20);
		getContentPane().add(tNombreClienteHasta);
		
		JLabel lblApellido = new JLabel("Apellido Cliente");
		lblApellido.setBounds(311, 8, 89, 14);
		getContentPane().add(lblApellido);
		
		tApellidoClienteDesde = new JTextField();
		tApellidoClienteDesde.setColumns(10);
		tApellidoClienteDesde.setBounds(311, 26, 151, 20);
		getContentPane().add(tApellidoClienteDesde);
		
		tApellidoClienteHasta = new JTextField();
		tApellidoClienteHasta.setColumns(10);
		tApellidoClienteHasta.setBounds(311, 50, 151, 20);
		getContentPane().add(tApellidoClienteHasta);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(478, 8, 46, 14);
		getContentPane().add(lblFecha);
		
		tFechaDesde = new JFormattedTextField(FechaUtil.getMascara());
		tFechaDesde.setBounds(472, 26, 89, 20);
		getContentPane().add(tFechaDesde);
		
		tFechaHasta = new JFormattedTextField(FechaUtil.getMascara());
		tFechaHasta.setBounds(472, 50, 89, 20);
		getContentPane().add(tFechaHasta);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(685, 25, 89, 45);
		getContentPane().add(btnProcesar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(537, 418, 89, 32);
		getContentPane().add(btnImprimir);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 4, 125, 14);
		getContentPane().add(lblCodigo);
		
		tIdDesde = new JTextField();
		tIdDesde.setColumns(10);
		tIdDesde.setBounds(10, 29, 125, 20);
		getContentPane().add(tIdDesde);
		
		tIdHasta = new JTextField();
		tIdHasta.setColumns(10);
		tIdHasta.setBounds(10, 53, 125, 20);
		getContentPane().add(tIdHasta);
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por");
		lblOrdenarPor.setBounds(576, 8, 76, 14);
		getContentPane().add(lblOrdenarPor);
		
		cbOrden = new JComboBox();
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nombre Cliente", "Apellido Cliente", "Fecha"}));
		cbOrden.setBounds(571, 26, 104, 20);
		getContentPane().add(cbOrden);
		
		btnDetallado = new JButton("Detallado");
		btnDetallado.setActionCommand("ImprimirDetallado");
		btnDetallado.setBounds(665, 418, 89, 32);
		getContentPane().add(btnDetallado);

	}

	public JButton getBtnDetallado() {
		return btnDetallado;
	}

	public JTable getTable() {
		return table;
	}

	public JTextField gettNombreClienteDesde() {
		return tNombreClienteDesde;
	}

	public JTextField gettNombreClienteHasta() {
		return tNombreClienteHasta;
	}

	public JTextField gettApellidoClienteDesde() {
		return tApellidoClienteDesde;
	}

	public JTextField gettApellidoClienteHasta() {
		return tApellidoClienteHasta;
	}

	public JFormattedTextField gettFechaDesde() {
		return tFechaDesde;
	}

	public JFormattedTextField gettFechaHasta() {
		return tFechaHasta;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public JTextField gettIdDesde() {
		return tIdDesde;
	}

	public JTextField gettIdHasta() {
		return tIdHasta;
	}

	public JComboBox getCbOrden() {
		return cbOrden;
	}
}
