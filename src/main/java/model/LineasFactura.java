package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lineas_facturas database table.
 * 
 */
@Entity
@Table(name="lineas_facturas")
@NamedQuery(name="LineasFactura.findAll", query="SELECT l FROM LineasFactura l")
public class LineasFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LineasFacturaPK id;

	private int cantidad;

	private String concepto;

	@Column(name="precio_unitario")
	private float precioUnitario;

	//bi-directional many-to-one association to CabecerasFactura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private CabecerasFactura cabecerasFactura;

	//bi-directional many-to-one association to Iva
	@ManyToOne
	@JoinColumn(name="id_ivas")
	private Iva iva;

	public LineasFactura() {
	}

	public LineasFacturaPK getId() {
		return this.id;
	}

	public void setId(LineasFacturaPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public float getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public CabecerasFactura getCabecerasFactura() {
		return this.cabecerasFactura;
	}

	public void setCabecerasFactura(CabecerasFactura cabecerasFactura) {
		this.cabecerasFactura = cabecerasFactura;
	}

	public Iva getIva() {
		return this.iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

}