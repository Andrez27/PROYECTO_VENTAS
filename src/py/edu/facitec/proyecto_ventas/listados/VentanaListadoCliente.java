package py.edu.facitec.proyecto_ventas.listados;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import py.edu.facitec.proyecto_ventas.controladores.VentanaListadoClienteController;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaListadoCliente extends JDialog {
	private JTable table;
	private JTextField tNombreDesde;
	private JTextField tNombreHasta;
	private JTextField tApellidoDesde;
	private JTextField tApellidoHasta;
	private JFormattedTextField tFechaRegistroDesde;
	private JFormattedTextField tFechaRegistroHasta;
	private JButton btnImprimir;
	private JButton btnProcesar;
	private VentanaListadoClienteController controller;
	private JTextField tIdDesde;
	private JTextField tIdHasta;
	private JComboBox cbOrden;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoCliente dialog = new VentanaListadoCliente();
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
		controller = new VentanaListadoClienteController(this);
	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoCliente() {
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 764, 326);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(145, 8, 156, 14);
		getContentPane().add(lblNombre);
		
		tNombreDesde = new JTextField();
		tNombreDesde.setBounds(145, 26, 156, 20);
		getContentPane().add(tNombreDesde);
		tNombreDesde.setColumns(10);
		
		tNombreHasta = new JTextField();
		tNombreHasta.setColumns(10);
		tNombreHasta.setBounds(145, 50, 156, 20);
		getContentPane().add(tNombreHasta);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(311, 8, 46, 14);
		getContentPane().add(lblApellido);
		
		tApellidoDesde = new JTextField();
		tApellidoDesde.setColumns(10);
		tApellidoDesde.setBounds(311, 26, 151, 20);
		getContentPane().add(tApellidoDesde);
		
		tApellidoHasta = new JTextField();
		tApellidoHasta.setColumns(10);
		tApellidoHasta.setBounds(311, 50, 151, 20);
		getContentPane().add(tApellidoHasta);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(478, 8, 46, 14);
		getContentPane().add(lblFecha);
		
		tFechaRegistroDesde = new JFormattedTextField(FechaUtil.getMascara());
		tFechaRegistroDesde.setBounds(472, 26, 89, 20);
		getContentPane().add(tFechaRegistroDesde);
		
		tFechaRegistroHasta = new JFormattedTextField(FechaUtil.getMascara());
		tFechaRegistroHasta.setBounds(472, 50, 89, 20);
		getContentPane().add(tFechaRegistroHasta);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(685, 25, 89, 45);
		getContentPane().add(btnProcesar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(685, 418, 89, 32);
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
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nombre", "Apellido", "Fecha Registro"}));
		cbOrden.setBounds(571, 26, 104, 20);
		getContentPane().add(cbOrden);

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

	public JTable getTable() {
		return table;
	}

	public JTextField gettNombreDesde() {
		return tNombreDesde;
	}

	public JTextField gettNombreHasta() {
		return tNombreHasta;
	}

	public JTextField gettApellidoDesde() {
		return tApellidoDesde;
	}

	public JTextField gettApellidoHasta() {
		return tApellidoHasta;
	}

	public JFormattedTextField gettFechaRegistroDesde() {
		return tFechaRegistroDesde;
	}

	public JFormattedTextField gettFechaRegistroHasta() {
		return tFechaRegistroHasta;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}
}
