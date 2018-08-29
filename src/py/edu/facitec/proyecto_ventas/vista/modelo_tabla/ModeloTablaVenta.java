package py.edu.facitec.proyecto_ventas.vista.modelo_tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyecto_ventas.modelo.Venta;
import py.edu.facitec.proyecto_ventas.util.FechaUtil;

public class ModeloTablaVenta extends AbstractTableModel{
	
	private String[] columnas = {"#","Cliente","Fecha","Tipo"};
	private List<Venta> lista = new ArrayList<Venta>();
	
	public void setLista(List<Venta> lista) {
		this.lista = lista;
	}
	
	@Override
	public int getRowCount() {//recupera cantidad de filas
		return lista.size();
	}

	@Override
	public int getColumnCount() {//recupera cantidad de columnas
		return columnas.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	//carga los valores de los objetos en las celdas
	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return lista.get(row).getId();
		case 1:
			return lista.get(row).getCliente().getNombre() + " " 
					+ lista.get(row).getCliente().getApellido();
		case 2:
			return FechaUtil.convertirDateUtilAString(lista.get(row).getFecha());
		case 3:
			return lista.get(row).getTipoPago();
		default:
			return null;
		}		
	}

}
