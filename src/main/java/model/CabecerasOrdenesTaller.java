package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cabeceras_ordenes_taller database table.
 * 
 */
@Entity
@Table(name="cabeceras_ordenes_taller")
@NamedQuery(name="CabecerasOrdenesTaller.findAll", query="SELECT c FROM CabecerasOrdenesTaller c")
public class CabecerasOrdenesTaller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora")
	private Date fechaHora;

	//bi-directional many-to-one association to CabecerasFactura
	@OneToMany(mappedBy="cabecerasOrdenesTaller")
	private List<CabecerasFactura> cabecerasFacturas;

	//bi-directional many-to-one association to CabecerasDiagnostico
	@ManyToOne
	@JoinColumn(name="id_diagnostico_final")
	private CabecerasDiagnostico cabecerasDiagnostico;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	//bi-directional many-to-one association to ManoObraOrdenesTaller
	@OneToMany(mappedBy="cabecerasOrdenesTaller")
	private List<ManoObraOrdenesTaller> manoObraOrdenesTallers;

	//bi-directional many-to-one association to PiezasOrdenesTaller
	@OneToMany(mappedBy="cabecerasOrdenesTaller")
	private List<PiezasOrdenesTaller> piezasOrdenesTallers;

	//bi-directional one-to-one association to TotalesOrdenesTaller
	@OneToOne(mappedBy="cabecerasOrdenesTaller")
	private TotalesOrdenesTaller totalesOrdenesTaller;

	public CabecerasOrdenesTaller() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<CabecerasFactura> getCabecerasFacturas() {
		return this.cabecerasFacturas;
	}

	public void setCabecerasFacturas(List<CabecerasFactura> cabecerasFacturas) {
		this.cabecerasFacturas = cabecerasFacturas;
	}

	public CabecerasFactura addCabecerasFactura(CabecerasFactura cabecerasFactura) {
		getCabecerasFacturas().add(cabecerasFactura);
		cabecerasFactura.setCabecerasOrdenesTaller(this);

		return cabecerasFactura;
	}

	public CabecerasFactura removeCabecerasFactura(CabecerasFactura cabecerasFactura) {
		getCabecerasFacturas().remove(cabecerasFactura);
		cabecerasFactura.setCabecerasOrdenesTaller(null);

		return cabecerasFactura;
	}

	public CabecerasDiagnostico getCabecerasDiagnostico() {
		return this.cabecerasDiagnostico;
	}

	public void setCabecerasDiagnostico(CabecerasDiagnostico cabecerasDiagnostico) {
		this.cabecerasDiagnostico = cabecerasDiagnostico;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<ManoObraOrdenesTaller> getManoObraOrdenesTallers() {
		return this.manoObraOrdenesTallers;
	}

	public void setManoObraOrdenesTallers(List<ManoObraOrdenesTaller> manoObraOrdenesTallers) {
		this.manoObraOrdenesTallers = manoObraOrdenesTallers;
	}

	public ManoObraOrdenesTaller addManoObraOrdenesTaller(ManoObraOrdenesTaller manoObraOrdenesTaller) {
		getManoObraOrdenesTallers().add(manoObraOrdenesTaller);
		manoObraOrdenesTaller.setCabecerasOrdenesTaller(this);

		return manoObraOrdenesTaller;
	}

	public ManoObraOrdenesTaller removeManoObraOrdenesTaller(ManoObraOrdenesTaller manoObraOrdenesTaller) {
		getManoObraOrdenesTallers().remove(manoObraOrdenesTaller);
		manoObraOrdenesTaller.setCabecerasOrdenesTaller(null);

		return manoObraOrdenesTaller;
	}

	public List<PiezasOrdenesTaller> getPiezasOrdenesTallers() {
		return this.piezasOrdenesTallers;
	}

	public void setPiezasOrdenesTallers(List<PiezasOrdenesTaller> piezasOrdenesTallers) {
		this.piezasOrdenesTallers = piezasOrdenesTallers;
	}

	public PiezasOrdenesTaller addPiezasOrdenesTaller(PiezasOrdenesTaller piezasOrdenesTaller) {
		getPiezasOrdenesTallers().add(piezasOrdenesTaller);
		piezasOrdenesTaller.setCabecerasOrdenesTaller(this);

		return piezasOrdenesTaller;
	}

	public PiezasOrdenesTaller removePiezasOrdenesTaller(PiezasOrdenesTaller piezasOrdenesTaller) {
		getPiezasOrdenesTallers().remove(piezasOrdenesTaller);
		piezasOrdenesTaller.setCabecerasOrdenesTaller(null);

		return piezasOrdenesTaller;
	}

	public TotalesOrdenesTaller getTotalesOrdenesTaller() {
		return this.totalesOrdenesTaller;
	}

	public void setTotalesOrdenesTaller(TotalesOrdenesTaller totalesOrdenesTaller) {
		this.totalesOrdenesTaller = totalesOrdenesTaller;
	}

}