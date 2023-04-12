package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the totales_facturas database table.
 * 
 */
@Entity
@Table(name="totales_facturas")
@NamedQuery(name="TotalesFactura.findAll", query="SELECT t FROM TotalesFactura t")
public class TotalesFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura")
	private int idFactura;

	@Column(name="total_factura")
	private float totalFactura;

	@Column(name="total_iva")
	private float totalIva;

	@Column(name="total_mano_obra")
	private float totalManoObra;

	@Column(name="total_piezas")
	private float totalPiezas;

	public TotalesFactura() {
	}

	public int getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public float getTotalFactura() {
		return this.totalFactura;
	}

	public void setTotalFactura(float totalFactura) {
		this.totalFactura = totalFactura;
	}

	public float getTotalIva() {
		return this.totalIva;
	}

	public void setTotalIva(float totalIva) {
		this.totalIva = totalIva;
	}

	public float getTotalManoObra() {
		return this.totalManoObra;
	}

	public void setTotalManoObra(float totalManoObra) {
		this.totalManoObra = totalManoObra;
	}

	public float getTotalPiezas() {
		return this.totalPiezas;
	}

	public void setTotalPiezas(float totalPiezas) {
		this.totalPiezas = totalPiezas;
	}

}