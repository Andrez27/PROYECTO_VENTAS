package py.edu.facitec.proyecto_ventas.vista.modelo_tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyecto_ventas.modelo.Cliente;

public class ModeloTablaCliente extends AbstractTableModel{
	
	private String[] columnas = {"#","Nombre y Apellido","Documento","Activo"};
	private List<Cliente> lista = new ArrayList<Cliente>();
	
	public void setLista(List<Cliente> lista) {
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
			return lista.get(row).getNombre() + " " 
					+ lista.get(row).getApellido();
		case 2:
			return lista.get(row).getDocumento();
		case 3:
			return lista.get(row).getActivo();
		default:
			return null;
		}		
	}

}
