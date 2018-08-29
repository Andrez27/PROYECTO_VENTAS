package py.edu.facitec.proyecto_ventas.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ventas")
public class Venta {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date fecha;
	@Column(nullable=false)
	private String tipoPago;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy="venta",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
	private List<VentaDetalle> items;
	
	public List<VentaDetalle> getItems() {
		return items;
	}
	public void setItems(List<VentaDetalle> items) {
		this.items = items;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	
}
