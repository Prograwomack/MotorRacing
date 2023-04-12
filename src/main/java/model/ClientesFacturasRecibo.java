package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the clientes_facturas_recibos database table.
 * 
 */
@Entity
@Table(name="clientes_facturas_recibos")
@NamedQuery(name="ClientesFacturasRecibo.findAll", query="SELECT c FROM ClientesFacturasRecibo c")
public class ClientesFacturasRecibo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClientesFacturasReciboPK id;

	//bi-directional many-to-one association to CabecerasFactura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private CabecerasFactura cabecerasFactura;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Recibo
	@ManyToOne
	@JoinColumn(name="id_recibo")
	private Recibo recibo;

	public ClientesFacturasRecibo() {
	}

	public ClientesFacturasReciboPK getId() {
		return this.id;
	}

	public void setId(ClientesFacturasReciboPK id) {
		this.id = id;
	}

	public CabecerasFactura getCabecerasFactura() {
		return this.cabecerasFactura;
	}

	public void setCabecerasFactura(CabecerasFactura cabecerasFactura) {
		this.cabecerasFactura = cabecerasFactura;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Recibo getRecibo() {
		return this.recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}

}