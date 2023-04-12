package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the totales_ordenes_taller database table.
 * 
 */
@Entity
@Table(name="totales_ordenes_taller")
@NamedQuery(name="TotalesOrdenesTaller.findAll", query="SELECT t FROM TotalesOrdenesTaller t")
public class TotalesOrdenesTaller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_orden_taller")
	private int idOrdenTaller;

	@Column(name="total_mano_obra")
	private int totalManoObra;

	@Column(name="total_piezas")
	private int totalPiezas;

	@Column(name="total_tiempo")
	private float totalTiempo;

	//bi-directional one-to-one association to CabecerasOrdenesTaller
	@OneToOne
	@JoinColumn(name="id_orden_taller")
	private CabecerasOrdenesTaller cabecerasOrdenesTaller;

	public TotalesOrdenesTaller() {
	}

	public int getIdOrdenTaller() {
		return this.idOrdenTaller;
	}

	public void setIdOrdenTaller(int idOrdenTaller) {
		this.idOrdenTaller = idOrdenTaller;
	}

	public int getTotalManoObra() {
		return this.totalManoObra;
	}

	public void setTotalManoObra(int totalManoObra) {
		this.totalManoObra = totalManoObra;
	}

	public int getTotalPiezas() {
		return this.totalPiezas;
	}

	public void setTotalPiezas(int totalPiezas) {
		this.totalPiezas = totalPiezas;
	}

	public float getTotalTiempo() {
		return this.totalTiempo;
	}

	public void setTotalTiempo(float totalTiempo) {
		this.totalTiempo = totalTiempo;
	}

	public CabecerasOrdenesTaller getCabecerasOrdenesTaller() {
		return this.cabecerasOrdenesTaller;
	}

	public void setCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		this.cabecerasOrdenesTaller = cabecerasOrdenesTaller;
	}

}