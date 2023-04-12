package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lineas_facturas_piezas database table.
 * 
 */
@Entity
@Table(name="lineas_facturas_piezas")
@NamedQuery(name="LineasFacturasPieza.findAll", query="SELECT l FROM LineasFacturasPieza l")
public class LineasFacturasPieza implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LineasFacturasPiezaPK id;

	//bi-directional many-to-one association to CabecerasFactura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private CabecerasFactura cabecerasFactura;

	//bi-directional many-to-one association to InventariosPieza
	@ManyToOne
	@JoinColumn(name="id_pieza")
	private InventariosPieza inventariosPieza;

	public LineasFacturasPieza() {
	}

	public LineasFacturasPiezaPK getId() {
		return this.id;
	}

	public void setId(LineasFacturasPiezaPK id) {
		this.id = id;
	}

	public CabecerasFactura getCabecerasFactura() {
		return this.cabecerasFactura;
	}

	public void setCabecerasFactura(CabecerasFactura cabecerasFactura) {
		this.cabecerasFactura = cabecerasFactura;
	}

	public InventariosPieza getInventariosPieza() {
		return this.inventariosPieza;
	}

	public void setInventariosPieza(InventariosPieza inventariosPieza) {
		this.inventariosPieza = inventariosPieza;
	}

}