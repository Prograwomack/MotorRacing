package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ivas database table.
 * 
 */
@Entity
@Table(name="ivas")
@NamedQuery(name="Iva.findAll", query="SELECT i FROM Iva i")
public class Iva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="tipo_iva")
	private float tipoIva;

	//bi-directional many-to-one association to LineasDiagnostico
	@OneToMany(mappedBy="iva")
	private List<LineasDiagnostico> lineasDiagnosticos;

	//bi-directional many-to-one association to LineasFactura
	@OneToMany(mappedBy="iva")
	private List<LineasFactura> lineasFacturas;

	public Iva() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTipoIva() {
		return this.tipoIva;
	}

	public void setTipoIva(float tipoIva) {
		this.tipoIva = tipoIva;
	}

	public List<LineasDiagnostico> getLineasDiagnosticos() {
		return this.lineasDiagnosticos;
	}

	public void setLineasDiagnosticos(List<LineasDiagnostico> lineasDiagnosticos) {
		this.lineasDiagnosticos = lineasDiagnosticos;
	}

	public LineasDiagnostico addLineasDiagnostico(LineasDiagnostico lineasDiagnostico) {
		getLineasDiagnosticos().add(lineasDiagnostico);
		lineasDiagnostico.setIva(this);

		return lineasDiagnostico;
	}

	public LineasDiagnostico removeLineasDiagnostico(LineasDiagnostico lineasDiagnostico) {
		getLineasDiagnosticos().remove(lineasDiagnostico);
		lineasDiagnostico.setIva(null);

		return lineasDiagnostico;
	}

	public List<LineasFactura> getLineasFacturas() {
		return this.lineasFacturas;
	}

	public void setLineasFacturas(List<LineasFactura> lineasFacturas) {
		this.lineasFacturas = lineasFacturas;
	}

	public LineasFactura addLineasFactura(LineasFactura lineasFactura) {
		getLineasFacturas().add(lineasFactura);
		lineasFactura.setIva(this);

		return lineasFactura;
	}

	public LineasFactura removeLineasFactura(LineasFactura lineasFactura) {
		getLineasFacturas().remove(lineasFactura);
		lineasFactura.setIva(null);

		return lineasFactura;
	}

}