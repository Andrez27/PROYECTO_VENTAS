package py.edu.facitec.proyecto_ventas.util;

import java.awt.Dialog.ModalExclusionType;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteUtil {
	private static JasperReport report;
	private static JasperPrint print;
	private static JasperViewer viewer;
	
	public static void imprimir(List<?> lista, Map<String, Object> map, String nombreReporte){
		//Solo si el reporte va tener un logo
		InputStream logo = ReporteUtil.class.getResourceAsStream("/img/logo.png");
		map.put("logo", logo);
		
		//Codigo para imprimir reportes
		
		String urlReporte = "/py/edu/facitec/proyecto_ventas/jasper/"+nombreReporte+".jasper";
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		
		try {
			report = (JasperReport) JRLoader.loadObject(ReporteUtil.class.getResource(urlReporte));
			print = JasperFillManager.fillReport(report, map, dataSource);
			viewer = new JasperViewer(print, false);
			viewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
}
