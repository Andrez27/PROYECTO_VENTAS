package py.edu.facitec.proyecto_ventas.vista.configuracion;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import py.edu.facitec.proyecto_ventas.controladores.VentanaConfiguracionController;
import py.edu.facitec.proyecto_ventas.vista.componentes.MiBoton;

import javax.swing.JButton;

public class VentanaConfiguracion extends JDialog {
	private JTextField tEmpresa;
	private JTextField tTelefono;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private VentanaConfiguracionController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfiguracion dialog = new VentanaConfiguracion();
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
		controller = new VentanaConfiguracionController(this);
	}

	/**
	 * Create the dialog.
	 */
	public VentanaConfiguracion() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Empresa");
		lblNombreDeLa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreDeLa.setBounds(10, 11, 180, 24);
		getContentPane().add(lblNombreDeLa);
		
		tEmpresa = new JTextField();
		tEmpresa.setBounds(10, 46, 414, 20);
		getContentPane().add(tEmpresa);
		tEmpresa.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelfono.setBounds(10, 81, 180, 24);
		getContentPane().add(lblTelfono);
		
		tTelefono = new JTextField();
		tTelefono.setBounds(10, 116, 166, 20);
		getContentPane().add(tTelefono);
		tTelefono.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(69, 178, 89, 48);
		getContentPane().add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(246, 178, 89, 48);
		getContentPane().add(btnCancelar);

	}

	public JTextField gettEmpresa() {
		return tEmpresa;
	}

	public void settEmpresa(JTextField tEmpresa) {
		this.tEmpresa = tEmpresa;
	}

	public JTextField gettTelefono() {
		return tTelefono;
	}

	public void settTelefono(JTextField tTelefono) {
		this.tTelefono = tTelefono;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
}
