package py.edu.facitec.proyecto_ventas.vista.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import py.edu.facitec.proyecto_ventas.interfaces.AccionesABM;

public class MiToolBar extends JToolBar implements ActionListener{
	private MiBoton modificar;
	private MiBoton eliminar;
	private MiBoton guardar;
	private MiBoton cancelar;
	private MiBoton salir;
	private MiBoton nuevo;
	private AccionesABM acciones;
	
	public void setAcciones(AccionesABM acciones) {
		this.acciones = acciones;
	}
	
	public MiToolBar() {
		setBorder(null);
		setOpaque(false);
		setFloatable(false);
		
		nuevo = new MiBoton();
		nuevo.setText("Nuevo");
		add(nuevo);
		
		modificar = new MiBoton();
		modificar.setText("Modificar");
		add(modificar);
		
		eliminar = new MiBoton();
		eliminar.setText("Eliminar");
		add(eliminar);
		
		guardar = new MiBoton();
		guardar.setText("Guardar");
		add(guardar);
		
		cancelar = new MiBoton();
		cancelar.setText("Cancelar");
		add(cancelar);
		
		salir = new MiBoton();
		salir.setText("Salir");
		add(salir);
		
		nuevo.addActionListener(this);
		modificar.addActionListener(this);
		eliminar.addActionListener(this);
		guardar.addActionListener(this);
		cancelar.addActionListener(this);
		salir.addActionListener(this);
	}
	public MiBoton getModificar() {
		return modificar;
	}
	public MiBoton getEliminar() {
		return eliminar;
	}
	public MiBoton getGuardar() {
		return guardar;
	}
	public MiBoton getCancelar() {
		return cancelar;
	}
	public MiBoton getSalir() {
		return salir;
	}
	public MiBoton getNuevo() {
		return nuevo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Nuevo":
			acciones.nuevo();
			break;
		case "Modificar":
			acciones.modificar();
			break;
		case "Eliminar":
			acciones.eliminar();
			break;
		case "Guardar":
			acciones.guardar();
			break;
		case "Cancelar":
			acciones.cancelar();
			break;
		case "Salir":
			acciones.salir();
			break;
		default:
			break;
		}
	}
	
}
