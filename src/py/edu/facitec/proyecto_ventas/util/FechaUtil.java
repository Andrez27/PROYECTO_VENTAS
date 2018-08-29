package py.edu.facitec.proyecto_ventas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class FechaUtil {
	
	final private static SimpleDateFormat FORMATO_FECHA
			= new SimpleDateFormat("dd/MM/yyyy");//MM=mes y mm=minuto
	private static MaskFormatter mascara;
	
	public static MaskFormatter getMascara() {
		if(mascara == null){
			try {
				mascara = new MaskFormatter("##/##/####");
				mascara.setPlaceholderCharacter('_');
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mascara;
	}
	
	public static Date convertirStringADateUtil(String s){
		//Para evitar que acepte fechas fuera de rango
		FORMATO_FECHA.setLenient(false);
		Date fecha = null;
		try {
			fecha = FORMATO_FECHA.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fecha;
	}
	
	public static String convertirDateUtilAString(Date d){
		return FORMATO_FECHA.format(d);
	}
	
}
