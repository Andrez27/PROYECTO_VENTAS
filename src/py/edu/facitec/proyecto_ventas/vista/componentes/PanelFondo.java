package py.edu.facitec.proyecto_ventas.vista.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondo extends JPanel {
	public PanelFondo() {
	}

	private Image fondo;//Atributo para almacenar la imagen de fondo
	
	@Override
	public void paint(Graphics g) {
		int ancho = this.getWidth();//recuperamos el ancho del panel
		int alto = this.getHeight();//recuperamos el alto del panel
		if (fondo!=null) {//si asignamos una imagen valida
			g.drawImage(fondo,0,0,ancho,alto,null);//Pinta el fondo
			setOpaque(false);//Color de fondo transparente par visualizar la imagen
		}
		super.paint(g);
	}
	
	public void setFondo(String nombre) {
		//Recuperamos la direccion fisica de la imagen
		URL url = PanelFondo.class.getResource("/img/"+nombre);
		//Cargar la imagen
		fondo = new ImageIcon(url).getImage();
		//Repintar el panel con la nueva imagen
		repaint();
	}

}
