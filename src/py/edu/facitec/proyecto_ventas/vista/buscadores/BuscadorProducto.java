package py.edu.facitec.proyecto_ventas.vista.buscadores;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import py.edu.facitec.proyecto_ventas.controladores.BuscadorProductoController;

public class BuscadorProducto extends JDialog {
	private JTextField jBuscador;
	private JTable table;
	private BuscadorProductoController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscadorProducto dialog = new BuscadorProducto();
					dialog.setupController();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setupController() {
		controller = new BuscadorProductoController(this);
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorProducto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(this);
		
		jBuscador = new JTextField();
		jBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				controller.recuperarPorFiltros();
			}
		});
		jBuscador.setBounds(10, 11, 414, 20);
		getContentPane().add(jBuscador);
		jBuscador.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 414, 195);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					controller.seleccionarRegistro(table.getSelectedRow());
				}
			}
		});
		scrollPane.setViewportView(table);

	}

	public JTextField getjBuscador() {
		return jBuscador;
	}

	public JTable getTable() {
		return table;
	}
	
	public BuscadorProductoController getController() {
		return controller;
	}
	
}
