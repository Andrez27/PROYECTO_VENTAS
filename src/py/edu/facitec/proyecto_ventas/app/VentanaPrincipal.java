package py.edu.facitec.proyecto_ventas.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;

import py.edu.facitec.proyecto_ventas.dao.ConfiguracionDao;
import py.edu.facitec.proyecto_ventas.modelo.Configuracion;
import py.edu.facitec.proyecto_ventas.util.ConnectionHelper;
import py.edu.facitec.proyecto_ventas.vista.abm.VentanaCliente;
import py.edu.facitec.proyecto_ventas.vista.componentes.MiBoton;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import py.edu.facitec.proyecto_ventas.vista.componentes.PanelFondo;
import py.edu.facitec.proyecto_ventas.vista.configuracion.VentanaConfiguracion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;

import java.awt.Font;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblTelefono;
	private JLabel lblEmpresa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionHelper.setUp();//abrimos la conexion 
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);//centrar ventana
		setExtendedState(MAXIMIZED_BOTH);//maximizar la ventana
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaCliente();
			}
		});
		mnArchivo.add(mntmClientes);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mnArchivo.add(mntmProveedores);
		
		JMenuItem mntmProductos = new JMenuItem("Productos");
		mnArchivo.add(mntmProductos);
		
		JMenu mnMovimientos = new JMenu("Movimientos");
		menuBar.add(mnMovimientos);
		
		JMenuItem mntmCompras = new JMenuItem("Compras");
		mnMovimientos.add(mntmCompras);
		
		JMenuItem mntmVentas = new JMenuItem("Ventas");
		mnMovimientos.add(mntmVentas);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenu mnListados = new JMenu("Listados");
		mnReportes.add(mnListados);
		
		JMenuItem mntmListadoDeClientes = new JMenuItem("Listado de Clientes");
		mnListados.add(mntmListadoDeClientes);
		
		JMenuItem mntmListadoDeProductos = new JMenuItem("Listado de Productos");
		mnListados.add(mntmListadoDeProductos);
		
		JMenuItem mntmListadoDeProveedores = new JMenuItem("Listado de Proveedores");
		mnListados.add(mntmListadoDeProveedores);
		
		JMenu mnInformes = new JMenu("Informes");
		mnReportes.add(mnInformes);
		
		JMenuItem mntmInformeDeCompras = new JMenuItem("Informe de compras");
		mnInformes.add(mntmInformeDeCompras);
		
		JMenuItem mntmInformeDeVentas = new JMenuItem("Informe de ventas");
		mnInformes.add(mntmInformeDeVentas);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmConfiguraciones = new JMenuItem("Configuraciones");
		mntmConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaConfiguracion();
			}
		});
		mnOpciones.add(mntmConfiguraciones);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		MiBoton mbtnClientes = new MiBoton();
		mbtnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaCliente();
			}
		});
		mbtnClientes.setIcon("cliente.png");
		mbtnClientes.setText("Clientes");
		toolBar.add(mbtnClientes);
		
		MiBoton mbtnProductos = new MiBoton();
		mbtnProductos.setIcon("producto.png");
		mbtnProductos.setText("Productos");
		toolBar.add(mbtnProductos);
		
		MiBoton mbtnVentas = new MiBoton();
		mbtnVentas.setIcon("venta.png");
		mbtnVentas.setText("Ventas");
		toolBar.add(mbtnVentas);
		
		MiBoton mbtnSalir = new MiBoton();
		mbtnSalir.setIcon("Cancel-32.png");
		mbtnSalir.setText("Salir");
		toolBar.add(mbtnSalir);
		
		PanelFondo panelFondo = new PanelFondo();
		panelFondo.setFondo("fondo2.jpg");
		contentPane.add(panelFondo, BorderLayout.CENTER);
		panelFondo.setLayout(null);
		
		lblEmpresa = new JLabel("New label");
		lblEmpresa.setForeground(Color.WHITE);
		lblEmpresa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmpresa.setBounds(10, 11, 452, 27);
		panelFondo.add(lblEmpresa);
		
		lblTelefono = new JLabel("New label");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefono.setBounds(10, 52, 452, 27);
		panelFondo.add(lblTelefono);
		
		recuperarConfiguracion();
	}//fin del constructor
	
	private void abrirVentanaCliente() {
		VentanaCliente v = new VentanaCliente();
		v.setupController();
		v.setVisible(true);
	}
	
	private void abrirVentanaConfiguracion() {
		VentanaConfiguracion v = new VentanaConfiguracion();
		v.setUpController();
		v.setVisible(true);
		v.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				recuperarConfiguracion();
			}
		});
	}
	
	private void recuperarConfiguracion() {
		ConfiguracionDao dao = new ConfiguracionDao();
		Configuracion configuracion = dao.recuperaPorId(1);
		if (configuracion == null) {
			lblEmpresa.setText("");
			lblTelefono.setText("");
		}else{
			lblEmpresa.setText(configuracion.getNombreEmpresa());
			lblTelefono.setText(configuracion.getTelefono());
		}
	}
	
}
