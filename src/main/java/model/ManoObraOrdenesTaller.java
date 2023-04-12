package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the mano_obra_ordenes_taller database table.
 * 
 */
@Entity
@Table(name="mano_obra_ordenes_taller")
@NamedQuery(name="ManoObraOrdenesTaller.findAll", query="SELECT m FROM ManoObraOrdenesTaller m")
public class ManoObraOrdenesTaller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp hora_fin;

	private Timestamp hora_inicio;

	private String operacion;

	//bi-directional many-to-one association to CabecerasOrdenesTaller
	@ManyToOne
	@JoinColumn(name="id_orden_taller")
	private CabecerasOrdenesTaller cabecerasOrdenesTaller;

	//bi-directional many-to-one association to TarifasManoObra
	@ManyToOne
	@JoinColumn(name="id_tarifa_mano_obra")
	private TarifasManoObra tarifasManoObra;

	public ManoObraOrdenesTaller() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getHora_fin() {
		return this.hora_fin;
	}

	public void setHora_fin(Timestamp hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Timestamp getHora_inicio() {
		return this.hora_inicio;
	}

	public void setHora_inicio(Timestamp hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public CabecerasOrdenesTaller getCabecerasOrdenesTaller() {
		return this.cabecerasOrdenesTaller;
	}

	public void setCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		this.cabecerasOrdenesTaller = cabecerasOrdenesTaller;
	}

	public TarifasManoObra getTarifasManoObra() {
		return this.tarifasManoObra;
	}

	public void setTarifasManoObra(TarifasManoObra tarifasManoObra) {
		this.tarifasManoObra = tarifasManoObra;
	}

}