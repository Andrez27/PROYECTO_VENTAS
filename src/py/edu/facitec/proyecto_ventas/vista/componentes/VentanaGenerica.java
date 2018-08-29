package py.edu.facitec.proyecto_ventas.vista.componentes;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class VentanaGenerica extends JDialog {
	private JTable table;
	private JLabel tituloFormulario;
	private JPanel panelFormulario;
	private MiToolBar miToolBar;

	/**
	 * Create the dialog.
	 */
	public VentanaGenerica() {
		setBounds(100, 100, 800, 400);
		setLocationRelativeTo(this);
		getContentPane().setLayout(null);
		
		miToolBar = new MiToolBar();
		miToolBar.setBounds(12, 12, 540, 66);
		getContentPane().add(miToolBar);
		
		panelFormulario = new JPanel();
		panelFormulario.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFormulario.setBounds(12, 100, 375, 258);
		getContentPane().add(panelFormulario);
		panelFormulario.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(402, 100, 384, 258);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		tituloFormulario = new JLabel("Titulo formulario");
		tituloFormulario.setBounds(12, 79, 375, 15);
		getContentPane().add(tituloFormulario);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JPanel getPanelFormulario() {
		return panelFormulario;
	}
	
	public JLabel getTituloFormulario() {
		return tituloFormulario;
	}
	
	public MiToolBar getMiToolBar() {
		return miToolBar;
	}
}
