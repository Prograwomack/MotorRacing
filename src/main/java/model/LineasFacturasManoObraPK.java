package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the lineas_facturas_mano_obra database table.
 * 
 */
@Embeddable
public class LineasFacturasManoObraPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_factura", insertable=false, updatable=false)
	private int idFactura;

	@Column(name="numero_linea")
	private int numeroLinea;

	public LineasFacturasManoObraPK() {
	}
	public int getIdFactura() {
		return this.idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getNumeroLinea() {
		return this.numeroLinea;
	}
	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LineasFacturasManoObraPK)) {
			return false;
		}
		LineasFacturasManoObraPK castOther = (LineasFacturasManoObraPK)other;
		return 
			(this.idFactura == castOther.idFactura)
			&& (this.numeroLinea == castOther.numeroLinea);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idFactura;
		hash = hash * prime + this.numeroLinea;
		
		return hash;
	}
}