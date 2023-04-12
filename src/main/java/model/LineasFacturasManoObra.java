package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lineas_facturas_mano_obra database table.
 * 
 */
@Entity
@Table(name="lineas_facturas_mano_obra")
@NamedQuery(name="LineasFacturasManoObra.findAll", query="SELECT l FROM LineasFacturasManoObra l")
public class LineasFacturasManoObra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LineasFacturasManoObraPK id;

	//bi-directional many-to-one association to CabecerasFactura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private CabecerasFactura cabecerasFactura;

	//bi-directional many-to-one association to TarifasManoObra
	@ManyToOne
	@JoinColumn(name="id_mano_obra")
	private TarifasManoObra tarifasManoObra;

	public LineasFacturasManoObra() {
	}

	public LineasFacturasManoObraPK getId() {
		return this.id;
	}

	public void setId(LineasFacturasManoObraPK id) {
		this.id = id;
	}

	public CabecerasFactura getCabecerasFactura() {
		return this.cabecerasFactura;
	}

	public void setCabecerasFactura(CabecerasFactura cabecerasFactura) {
		this.cabecerasFactura = cabecerasFactura;
	}

	public TarifasManoObra getTarifasManoObra() {
		return this.tarifasManoObra;
	}

	public void setTarifasManoObra(TarifasManoObra tarifasManoObra) {
		this.tarifasManoObra = tarifasManoObra;
	}

}