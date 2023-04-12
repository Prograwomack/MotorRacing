package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehiculos database table.
 * 
 */
@Entity
@Table(name="vehiculos")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int año;

	private String marca;

	private String matricula;

	private String modelo;

	@Column(name="numero_bastidor")
	private String numeroBastidor;

	//bi-directional many-to-one association to CabecerasDiagnostico
	@OneToMany(mappedBy="vehiculo")
	private List<CabecerasDiagnostico> cabecerasDiagnosticos;

	//bi-directional many-to-one association to CabecerasFactura
	@OneToMany(mappedBy="vehiculo")
	private List<CabecerasFactura> cabecerasFacturas;

	//bi-directional many-to-one association to CabecerasOrdenesTaller
	@OneToMany(mappedBy="vehiculo")
	private List<CabecerasOrdenesTaller> cabecerasOrdenesTallers;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="vehiculo")
	private List<Cita> citas;

	//bi-directional many-to-one association to Recepcione
	@OneToMany(mappedBy="vehiculo")
	private List<Recepcione> recepciones;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to TiposVehiculo
	@ManyToOne
	@JoinColumn(name="id_tipo_vehiculo")
	private TiposVehiculo tiposVehiculo;

	public Vehiculo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAño() {
		return this.año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroBastidor() {
		return this.numeroBastidor;
	}

	public void setNumeroBastidor(String numeroBastidor) {
		this.numeroBastidor = numeroBastidor;
	}

	public List<CabecerasDiagnostico> getCabecerasDiagnosticos() {
		return this.cabecerasDiagnosticos;
	}

	public void setCabecerasDiagnosticos(List<CabecerasDiagnostico> cabecerasDiagnosticos) {
		this.cabecerasDiagnosticos = cabecerasDiagnosticos;
	}

	public CabecerasDiagnostico addCabecerasDiagnostico(CabecerasDiagnostico cabecerasDiagnostico) {
		getCabecerasDiagnosticos().add(cabecerasDiagnostico);
		cabecerasDiagnostico.setVehiculo(this);

		return cabecerasDiagnostico;
	}

	public CabecerasDiagnostico removeCabecerasDiagnostico(CabecerasDiagnostico cabecerasDiagnostico) {
		getCabecerasDiagnosticos().remove(cabecerasDiagnostico);
		cabecerasDiagnostico.setVehiculo(null);

		return cabecerasDiagnostico;
	}

	public List<CabecerasFactura> getCabecerasFacturas() {
		return this.cabecerasFacturas;
	}

	public void setCabecerasFacturas(List<CabecerasFactura> cabecerasFacturas) {
		this.cabecerasFacturas = cabecerasFacturas;
	}

	public CabecerasFactura addCabecerasFactura(CabecerasFactura cabecerasFactura) {
		getCabecerasFacturas().add(cabecerasFactura);
		cabecerasFactura.setVehiculo(this);

		return cabecerasFactura;
	}

	public CabecerasFactura removeCabecerasFactura(CabecerasFactura cabecerasFactura) {
		getCabecerasFacturas().remove(cabecerasFactura);
		cabecerasFactura.setVehiculo(null);

		return cabecerasFactura;
	}

	public List<CabecerasOrdenesTaller> getCabecerasOrdenesTallers() {
		return this.cabecerasOrdenesTallers;
	}

	public void setCabecerasOrdenesTallers(List<CabecerasOrdenesTaller> cabecerasOrdenesTallers) {
		this.cabecerasOrdenesTallers = cabecerasOrdenesTallers;
	}

	public CabecerasOrdenesTaller addCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		getCabecerasOrdenesTallers().add(cabecerasOrdenesTaller);
		cabecerasOrdenesTaller.setVehiculo(this);

		return cabecerasOrdenesTaller;
	}

	public CabecerasOrdenesTaller removeCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		getCabecerasOrdenesTallers().remove(cabecerasOrdenesTaller);
		cabecerasOrdenesTaller.setVehiculo(null);

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
		cita.setVehiculo(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setVehiculo(null);

		return cita;
	}

	public List<Recepcione> getRecepciones() {
		return this.recepciones;
	}

	public void setRecepciones(List<Recepcione> recepciones) {
		this.recepciones = recepciones;
	}

	public Recepcione addRecepcione(Recepcione recepcione) {
		getRecepciones().add(recepcione);
		recepcione.setVehiculo(this);

		return recepcione;
	}

	public Recepcione removeRecepcione(Recepcione recepcione) {
		getRecepciones().remove(recepcione);
		recepcione.setVehiculo(null);

		return recepcione;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TiposVehiculo getTiposVehiculo() {
		return this.tiposVehiculo;
	}

	public void setTiposVehiculo(TiposVehiculo tiposVehiculo) {
		this.tiposVehiculo = tiposVehiculo;
	}

}