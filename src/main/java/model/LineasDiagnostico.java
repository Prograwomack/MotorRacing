package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lineas_diagnosticos database table.
 * 
 */
@Entity
@Table(name="lineas_diagnosticos")
@NamedQuery(name="LineasDiagnostico.findAll", query="SELECT l FROM LineasDiagnostico l")
public class LineasDiagnostico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	@Column(name="codigo_articulo")
	private int codigoArticulo;

	private String concepto;

	@Column(name="numero_linea")
	private int numeroLinea;

	//bi-directional many-to-one association to CabecerasDiagnostico
	@ManyToOne
	@JoinColumn(name="id_cabecera_diagnostico")
	private CabecerasDiagnostico cabecerasDiagnostico;

	//bi-directional many-to-one association to Iva
	@ManyToOne
	@JoinColumn(name="id_iva")
	private Iva iva;

	public LineasDiagnostico() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCodigoArticulo() {
		return this.codigoArticulo;
	}

	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public int getNumeroLinea() {
		return this.numeroLinea;
	}

	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public CabecerasDiagnostico getCabecerasDiagnostico() {
		return this.cabecerasDiagnostico;
	}

	public void setCabecerasDiagnostico(CabecerasDiagnostico cabecerasDiagnostico) {
		this.cabecerasDiagnostico = cabecerasDiagnostico;
	}

	public Iva getIva() {
		return this.iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

}