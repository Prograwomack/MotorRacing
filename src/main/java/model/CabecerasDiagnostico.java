package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cabeceras_diagnosticos database table.
 * 
 */
@Entity
@Table(name="cabeceras_diagnosticos")
@NamedQuery(name="CabecerasDiagnostico.findAll", query="SELECT c FROM CabecerasDiagnostico c")
public class CabecerasDiagnostico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora")
	private Date fechaHora;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	//bi-directional many-to-one association to CabecerasOrdenesTaller
	@OneToMany(mappedBy="cabecerasDiagnostico")
	private List<CabecerasOrdenesTaller> cabecerasOrdenesTallers;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="cabecerasDiagnostico")
	private List<Cita> citas;

	//bi-directional many-to-one association to LineasDiagnostico
	@OneToMany(mappedBy="cabecerasDiagnostico")
	private List<LineasDiagnostico> lineasDiagnosticos;

	//bi-directional many-to-one association to Recepcione
	@OneToMany(mappedBy="cabecerasDiagnostico1")
	private List<Recepcione> recepciones1;

	//bi-directional many-to-one association to Recepcione
	@OneToMany(mappedBy="cabecerasDiagnostico2")
	private List<Recepcione> recepciones2;

	//bi-directional one-to-one association to TotalesDiagnostico
	@OneToOne(mappedBy="cabecerasDiagnostico")
	private TotalesDiagnostico totalesDiagnostico;

	public CabecerasDiagnostico() {
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

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<CabecerasOrdenesTaller> getCabecerasOrdenesTallers() {
		return this.cabecerasOrdenesTallers;
	}

	public void setCabecerasOrdenesTallers(List<CabecerasOrdenesTaller> cabecerasOrdenesTallers) {
		this.cabecerasOrdenesTallers = cabecerasOrdenesTallers;
	}

	public CabecerasOrdenesTaller addCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		getCabecerasOrdenesTallers().add(cabecerasOrdenesTaller);
		cabecerasOrdenesTaller.setCabecerasDiagnostico(this);

		return cabecerasOrdenesTaller;
	}

	public CabecerasOrdenesTaller removeCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		getCabecerasOrdenesTallers().remove(cabecerasOrdenesTaller);
		cabecerasOrdenesTaller.setCabecerasDiagnostico(null);

		return cabecerasOrdenesTaller;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setCabecerasDiagnostico(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setCabecerasDiagnostico(null);

		return cita;
	}

	public List<LineasDiagnostico> getLineasDiagnosticos() {
		return this.lineasDiagnosticos;
	}

	public void setLineasDiagnosticos(List<LineasDiagnostico> lineasDiagnosticos) {
		this.lineasDiagnosticos = lineasDiagnosticos;
	}

	public LineasDiagnostico addLineasDiagnostico(LineasDiagnostico lineasDiagnostico) {
		getLineasDiagnosticos().add(lineasDiagnostico);
		lineasDiagnostico.setCabecerasDiagnostico(this);

		return lineasDiagnostico;
	}

	public LineasDiagnostico removeLineasDiagnostico(LineasDiagnostico lineasDiagnostico) {
		getLineasDiagnosticos().remove(lineasDiagnostico);
		lineasDiagnostico.setCabecerasDiagnostico(null);

		return lineasDiagnostico;
	}

	public List<Recepcione> getRecepciones1() {
		return this.recepciones1;
	}

	public void setRecepciones1(List<Recepcione> recepciones1) {
		this.recepciones1 = recepciones1;
	}

	public Recepcione addRecepciones1(Recepcione recepciones1) {
		getRecepciones1().add(recepciones1);
		recepciones1.setCabecerasDiagnostico1(this);

		return recepciones1;
	}

	public Recepcione removeRecepciones1(Recepcione recepciones1) {
		getRecepciones1().remove(recepciones1);
		recepciones1.setCabecerasDiagnostico1(null);

		return recepciones1;
	}

	public List<Recepcione> getRecepciones2() {
		return this.recepciones2;
	}

	public void setRecepciones2(List<Recepcione> recepciones2) {
		this.recepciones2 = recepciones2;
	}

	public Recepcione addRecepciones2(Recepcione recepciones2) {
		getRecepciones2().add(recepciones2);
		recepciones2.setCabecerasDiagnostico2(this);

		return recepciones2;
	}

	public Recepcione removeRecepciones2(Recepcione recepciones2) {
		getRecepciones2().remove(recepciones2);
		recepciones2.setCabecerasDiagnostico2(null);

		return recepciones2;
	}

	public TotalesDiagnostico getTotalesDiagnostico() {
		return this.totalesDiagnostico;
	}

	public void setTotalesDiagnostico(TotalesDiagnostico totalesDiagnostico) {
		this.totalesDiagnostico = totalesDiagnostico;
	}

}