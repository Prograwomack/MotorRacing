package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the piezas_ordenes_taller database table.
 * 
 */
@Embeddable
public class PiezasOrdenesTallerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_orden_taller", insertable=false, updatable=false)
	private int idOrdenTaller;

	@Column(name="id_pieza", insertable=false, updatable=false)
	private int idPieza;

	public PiezasOrdenesTallerPK() {
	}
	public int getIdOrdenTaller() {
		return this.idOrdenTaller;
	}
	public void setIdOrdenTaller(int idOrdenTaller) {
		this.idOrdenTaller = idOrdenTaller;
	}
	public int getIdPieza() {
		return this.idPieza;
	}
	public void setIdPieza(int idPieza) {
		this.idPieza = idPieza;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PiezasOrdenesTallerPK)) {
			return false;
		}
		PiezasOrdenesTallerPK castOther = (PiezasOrdenesTallerPK)other;
		return 
			(this.idOrdenTaller == castOther.idOrdenTaller)
			&& (this.idPieza == castOther.idPieza);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idOrdenTaller;
		hash = hash * prime + this.idPieza;
		
		return hash;
	}
}