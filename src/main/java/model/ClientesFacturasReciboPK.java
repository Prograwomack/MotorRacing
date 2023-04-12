package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the clientes_facturas_recibos database table.
 * 
 */
@Embeddable
public class ClientesFacturasReciboPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_cliente", insertable=false, updatable=false)
	private int idCliente;

	@Column(name="id_factura", insertable=false, updatable=false)
	private int idFactura;

	@Column(name="id_recibo", insertable=false, updatable=false)
	private int idRecibo;

	public ClientesFacturasReciboPK() {
	}
	public int getIdCliente() {
		return this.idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdFactura() {
		return this.idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getIdRecibo() {
		return this.idRecibo;
	}
	public void setIdRecibo(int idRecibo) {
		this.idRecibo = idRecibo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClientesFacturasReciboPK)) {
			return false;
		}
		ClientesFacturasReciboPK castOther = (ClientesFacturasReciboPK)other;
		return 
			(this.idCliente == castOther.idCliente)
			&& (this.idFactura == castOther.idFactura)
			&& (this.idRecibo == castOther.idRecibo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCliente;
		hash = hash * prime + this.idFactura;
		hash = hash * prime + this.idRecibo;
		
		return hash;
	}
}