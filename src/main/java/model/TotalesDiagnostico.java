package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the totales_diagnosticos database table.
 * 
 */
@Entity
@Table(name="totales_diagnosticos")
@NamedQuery(name="TotalesDiagnostico.findAll", query="SELECT t FROM TotalesDiagnostico t")
public class TotalesDiagnostico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cabecera_diagnostico")
	private int idCabeceraDiagnostico;

	@Column(name="tiempo_total")
	private float tiempoTotal;

	@Column(name="total_mano_obra")
	private float totalManoObra;

	@Column(name="total_piezas")
	private int totalPiezas;

	//bi-directional one-to-one association to CabecerasDiagnostico
	@OneToOne
	@JoinColumn(name="id_cabecera_diagnostico")
	private CabecerasDiagnostico cabecerasDiagnostico;

	public TotalesDiagnostico() {
	}

	public int getIdCabeceraDiagnostico() {
		return this.idCabeceraDiagnostico;
	}

	public void setIdCabeceraDiagnostico(int idCabeceraDiagnostico) {
		this.idCabeceraDiagnostico = idCabeceraDiagnostico;
	}

	public float getTiempoTotal() {
		return this.tiempoTotal;
	}

	public void setTiempoTotal(float tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public float getTotalManoObra() {
		return this.totalManoObra;
	}

	public void setTotalManoObra(float totalManoObra) {
		this.totalManoObra = totalManoObra;
	}

	public int getTotalPiezas() {
		return this.totalPiezas;
	}

	public void setTotalPiezas(int totalPiezas) {
		this.totalPiezas = totalPiezas;
	}

	public CabecerasDiagnostico getCabecerasDiagnostico() {
		return this.cabecerasDiagnostico;
	}

	public void setCabecerasDiagnostico(CabecerasDiagnostico cabecerasDiagnostico) {
		this.cabecerasDiagnostico = cabecerasDiagnostico;
	}

}