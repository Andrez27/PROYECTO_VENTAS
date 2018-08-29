package py.edu.facitec.proyecto_ventas.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import py.edu.facitec.proyecto_ventas.dao.ConfiguracionDao;
import py.edu.facitec.proyecto_ventas.modelo.Configuracion;
import py.edu.facitec.proyecto_ventas.vista.configuracion.VentanaConfiguracion;

public class VentanaConfiguracionController implements ActionListener{
	
	private VentanaConfiguracion vConfiguracion;
	private ConfiguracionDao dao;
	private Configuracion configuracion;
	private String accion;
	
	public VentanaConfiguracionController(VentanaConfiguracion vConfiguracion) {
		this.vConfiguracion = vConfiguracion;
		
		//escuchar eventos
		this.vConfiguracion.getBtnGuardar().addActionListener(this);
		this.vConfiguracion.getBtnCancelar().addActionListener(this);
		
		recuperarConfiguracion();
	}
	
	private void recuperarConfiguracion() {
		dao = new ConfiguracionDao();
		configuracion = dao.recuperaPorId(1);
		if (configuracion == null) {
			accion = "NUEVO";
		}else{
			accion = "MODIFICAR";
			
			vConfiguracion.gettEmpresa().setText(configuracion.getNombreEmpresa());
			vConfiguracion.gettTelefono().setText(configuracion.getTelefono());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Guardar":
			guardar();
			break;
		case "Cancelar":
			cancelar();
			break;
		default:
			break;
		}
	}

	private void guardar() {
		if(accion.equals("NUEVO")){
			configuracion = new Configuracion();
		}
		configuracion.setNombreEmpresa(vConfiguracion.gettEmpresa().getText());
		configuracion.setTelefono(vConfiguracion.gettTelefono().getText());
		configuracion.setId(1);
		
		dao = new ConfiguracionDao();
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(configuracion);
			}else {
				dao.modificar(configuracion);
			}
			dao.commit();
			vConfiguracion.dispose();
		} catch (Exception e) {
			dao.rollback();
			JOptionPane.showMessageDialog(null, "Se produjo un error");
		}
	}

	private void cancelar() {
		vConfiguracion.dispose();
	}
	
	

}
