package py.edu.facitec.proyecto_ventas.vista.modelo_tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyecto_ventas.modelo.Producto;



public class ModeloTablaProducto extends AbstractTableModel{

	private List<Producto> lista = new ArrayList<>();
	private String[] columnas = {"ID","DESCRIPCION","PRECIO VENTA"};
	
	public void setLista(List<Producto> lista) {
		this.lista = lista;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex==0){
			return lista.get(rowIndex).getId();
		}
		if(columnIndex==1){
			return lista.get(rowIndex).getDescripcion();
		}
		if(columnIndex==2){
			return lista.get(rowIndex).getPrecioVenta();
		}
		return null;
	}
	
	//En casos especiales como boolean para mostra check
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==3){
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

}
