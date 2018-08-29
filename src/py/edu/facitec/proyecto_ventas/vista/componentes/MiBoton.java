package py.edu.facitec.proyecto_ventas.vista.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class MiBoton extends JButton{
	
	public MiBoton() {
		setMaximumSize(new Dimension(100, 60));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setBackground(new Color(64, 224, 208));
		setForeground(Color.WHITE);
		setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 12));
	}
	
	
	public void setIcon(String icon) {
		setIcon(new ImageIcon(MiBoton.class.getResource("/img/"+icon)));
	}

}
