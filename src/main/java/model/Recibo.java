package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the recibos database table.
 * 
 */
@Entity
@Table(name="recibos")
@NamedQuery(name="Recibo.findAll", query="SELECT r FROM Recibo r")
public class Recibo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cobro")
	private Date fechaCobro;

	private float importe;

	//bi-directional many-to-one association to ClientesFacturasRecibo
	@OneToMany(mappedBy="recibo")
	private List<ClientesFacturasRecibo> clientesFacturasRecibos;

	//bi-directional many-to-one association to CabecerasFactura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private CabecerasFactura cabecerasFactura;

	//bi-directional many-to-one association to MetodosPago
	@ManyToOne
	@JoinColumn(name="id_metodos_pago")
	private MetodosPago metodosPago;

	public Recibo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaCobro() {
		return this.fechaCobro;
	}

	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public List<ClientesFacturasRecibo> getClientesFacturasRecibos() {
		return this.clientesFacturasRecibos;
	}

	public void setClientesFacturasRecibos(List<ClientesFacturasRecibo> clientesFacturasRecibos) {
		this.clientesFacturasRecibos = clientesFacturasRecibos;
	}

	public ClientesFacturasRecibo addClientesFacturasRecibo(ClientesFacturasRecibo clientesFacturasRecibo) {
		getClientesFacturasRecibos().add(clientesFacturasRecibo);
		clientesFacturasRecibo.setRecibo(this);

		return clientesFacturasRecibo;
	}

	public ClientesFacturasRecibo removeClientesFacturasRecibo(ClientesFacturasRecibo clientesFacturasRecibo) {
		getClientesFacturasRecibos().remove(clientesFacturasRecibo);
		clientesFacturasRecibo.setRecibo(null);

		return clientesFacturasRecibo;
	}

	public CabecerasFactura getCabecerasFactura() {
		return this.cabecerasFactura;
	}

	public void setCabecerasFactura(CabecerasFactura cabecerasFactura) {
		this.cabecerasFactura = cabecerasFactura;
	}

	public MetodosPago getMetodosPago() {
		return this.metodosPago;
	}

	public void setMetodosPago(MetodosPago metodosPago) {
		this.metodosPago = metodosPago;
	}

}