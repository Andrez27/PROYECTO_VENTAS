package py.edu.facitec.proyecto_ventas.vista.modelo_tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyecto_ventas.modelo.VentaDetalle;

public class ModeloTablaVentaItem extends AbstractTableModel{
	
	private String[] columnas = {"ID","PRODUCTO","P.U.","CANT.","SUBTOT."};
	private List<VentaDetalle> lista = new ArrayList<>();
	
	public void setLista(List<VentaDetalle> lista) {
		this.lista = lista;
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}
	
	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return lista.get(rowIndex).getProducto().getId();
		case 1:
			return lista.get(rowIndex).getProducto().getDescripcion();
		case 2: 
			return lista.get(rowIndex).getPrecio();
		case 3:
			return lista.get(rowIndex).getCantidad();
		case 4:
			return lista.get(rowIndex).getCantidad() * lista.get(rowIndex).getPrecio();
		default:
			return null;
		}
	}

}
