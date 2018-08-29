package py.edu.facitec.proyecto_ventas.vista.abm;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.facitec.proyecto_ventas.controladores.VentanaClienteController;
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

public class VentanaCliente extends VentanaGenerica {
	private JTextField tNombre;
	private JTextField tApellido;
	private JTextField tDocumento;
	private VentanaClienteController controller;
	private JTextField tTelefono;
	private JTextField tEmail;
	private JFormattedTextField tFechaRegistro;
	private JCheckBox cActivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente dialog = new VentanaCliente();
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
	public VentanaCliente() {
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
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(12, 12, 90, 15);
		getPanelFormulario().add(lblNombre);
		
		tNombre = new JTextField();
		tNombre.setBounds(113, 10, 250, 19);
		getPanelFormulario().add(tNombre);
		tNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(12, 39, 90, 15);
		getPanelFormulario().add(lblApellido);
		
		tApellido = new JTextField();
		tApellido.setBounds(113, 37, 250, 19);
		getPanelFormulario().add(tApellido);
		tApellido.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDocumento.setBounds(13, 66, 89, 15);
		getPanelFormulario().add(lblDocumento);
		
		tDocumento = new JTextField();
		tDocumento.setBounds(113, 64, 101, 19);
		getPanelFormulario().add(tDocumento);
		tDocumento.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(33, 94, 69, 15);
		getPanelFormulario().add(lblTelefono);
		
		tTelefono = new JTextField();
		tTelefono.setBounds(113, 91, 101, 19);
		getPanelFormulario().add(tTelefono);
		tTelefono.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(33, 121, 69, 15);
		getPanelFormulario().add(lblEmail);
		
		tEmail = new JTextField();
		tEmail.setBounds(113, 119, 250, 19);
		getPanelFormulario().add(tEmail);
		tEmail.setColumns(10);
		
		JLabel lblFRegistro = new JLabel("F. Registro");
		lblFRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFRegistro.setBounds(12, 148, 90, 15);
		getPanelFormulario().add(lblFRegistro);
		
		tFechaRegistro = new JFormattedTextField(FechaUtil.getMascara());
		tFechaRegistro.setBounds(113, 146, 101, 19);
		getPanelFormulario().add(tFechaRegistro);
		
		cActivo = new JCheckBox("Activo");
		cActivo.setBounds(113, 173, 129, 23);
		getPanelFormulario().add(cActivo);

		
	}
	
	//Aqui indicamos cual es el controlador de la ventana
	public void setupController(){
		controller = new VentanaClienteController(this);
	}

	public JTextField gettNombre() {
		return tNombre;
	}

	public JTextField gettApellido() {
		return tApellido;
	}

	public JTextField gettDocumento() {
		return tDocumento;
	}

	public JTextField gettTelefono() {
		return tTelefono;
	}

	public JTextField gettEmail() {
		return tEmail;
	}

	public JFormattedTextField gettFechaRegistro() {
		return tFechaRegistro;
	}

	public JCheckBox getcActivo() {
		return cActivo;
	}
	
	
}
