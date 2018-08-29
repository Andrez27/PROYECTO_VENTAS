package py.edu.facitec.proyecto_ventas.vista.prueba;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import py.edu.facitec.proyecto_ventas.vista.componentes.MiBoton;
import py.edu.facitec.proyecto_ventas.vista.componentes.MiToolBar;

public class VentanaPrueba extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrueba frame = new VentanaPrueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MiBoton mbtnCliente = new MiBoton();
		mbtnCliente.setIcon("cliente.png");
		mbtnCliente.setText("Cliente");
		mbtnCliente.setBounds(59, 47, 152, 71);
		contentPane.add(mbtnCliente);
		
		MiBoton mbtnProducto = new MiBoton();
		mbtnProducto.setIcon("producto.png");
		mbtnProducto.setText("Producto");
		mbtnProducto.setBounds(223, 47, 145, 71);
		contentPane.add(mbtnProducto);
		
		MiToolBar miToolBar = new MiToolBar();
		miToolBar.setBounds(59, 191, 558, 87);
		contentPane.add(miToolBar);
	}
}
