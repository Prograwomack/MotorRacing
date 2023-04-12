package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the piezas_ordenes_taller database table.
 * 
 */
@Entity
@Table(name="piezas_ordenes_taller")
@NamedQuery(name="PiezasOrdenesTaller.findAll", query="SELECT p FROM PiezasOrdenesTaller p")
public class PiezasOrdenesTaller implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PiezasOrdenesTallerPK id;

	private int cantidad;

	//bi-directional many-to-one association to CabecerasOrdenesTaller
	@ManyToOne
	@JoinColumn(name="id_orden_taller")
	private CabecerasOrdenesTaller cabecerasOrdenesTaller;

	//bi-directional many-to-one association to InventariosPieza
	@ManyToOne
	@JoinColumn(name="id_pieza")
	private InventariosPieza inventariosPieza;

	public PiezasOrdenesTaller() {
	}

	public PiezasOrdenesTallerPK getId() {
		return this.id;
	}

	public void setId(PiezasOrdenesTallerPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public CabecerasOrdenesTaller getCabecerasOrdenesTaller() {
		return this.cabecerasOrdenesTaller;
	}

	public void setCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		this.cabecerasOrdenesTaller = cabecerasOrdenesTaller;
	}

	public InventariosPieza getInventariosPieza() {
		return this.inventariosPieza;
	}

	public void setInventariosPieza(InventariosPieza inventariosPieza) {
		this.inventariosPieza = inventariosPieza;
	}

}