package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellido1;

	private String apellido2;

	@Column(name="codigo_postal")
	private int codigoPostal;

	private String dni;

	private String nombre;

	@Column(name="nombre_via")
	private String nombreVia;

	@Column(name="numero_via")
	private int numeroVia;

	private int piso;

	private String provincia;

	private String puerta;

	private int telefono;

	@Column(name="tipo_via")
	private String tipoVia;

	//bi-directional many-to-one association to CabecerasFactura
	@OneToMany(mappedBy="cliente")
	private List<CabecerasFactura> cabecerasFacturas;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="cliente")
	private List<Cita> citas;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="id_login")
	private Login login;

	//bi-directional many-to-one association to ClientesFacturasRecibo
	@OneToMany(mappedBy="cliente")
	private List<ClientesFacturasRecibo> clientesFacturasRecibos;

	//bi-directional one-to-one association to PreferenciasCliente
	@OneToOne(mappedBy="cliente")
	private PreferenciasCliente preferenciasCliente;

	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="cliente")
	private List<Vehiculo> vehiculos;

	public Cliente() {
	}
	
	public Cliente(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreVia() {
		return this.nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public int getNumeroVia() {
		return this.numeroVia;
	}

	public void setNumeroVia(int numeroVia) {
		this.numeroVia = numeroVia;
	}

	public int getPiso() {
		return this.piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPuerta() {
		return this.puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getTipoVia() {
		return this.tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public List<CabecerasFactura> getCabecerasFacturas() {
		return this.cabecerasFacturas;
	}

	public void setCabecerasFacturas(List<CabecerasFactura> cabecerasFacturas) {
		this.cabecerasFacturas = cabecerasFacturas;
	}

	public CabecerasFactura addCabecerasFactura(CabecerasFactura cabecerasFactura) {
		getCabecerasFacturas().add(cabecerasFactura);
		cabecerasFactura.setCliente(this);

		return cabecerasFactura;
	}

	public CabecerasFactura removeCabecerasFactura(CabecerasFactura cabecerasFactura) {
		getCabecerasFacturas().remove(cabecerasFactura);
		cabecerasFactura.setCliente(null);

		return cabecerasFactura;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setCliente(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setCliente(null);

		return cita;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<ClientesFacturasRecibo> getClientesFacturasRecibos() {
		return this.clientesFacturasRecibos;
	}

	public void setClientesFacturasRecibos(List<ClientesFacturasRecibo> clientesFacturasRecibos) {
		this.clientesFacturasRecibos = clientesFacturasRecibos;
	}

	public ClientesFacturasRecibo addClientesFacturasRecibo(ClientesFacturasRecibo clientesFacturasRecibo) {
		getClientesFacturasRecibos().add(clientesFacturasRecibo);
		clientesFacturasRecibo.setCliente(this);

		return clientesFacturasRecibo;
	}

	public ClientesFacturasRecibo removeClientesFacturasRecibo(ClientesFacturasRecibo clientesFacturasRecibo) {
		getClientesFacturasRecibos().remove(clientesFacturasRecibo);
		clientesFacturasRecibo.setCliente(null);

		return clientesFacturasRecibo;
	}

	public PreferenciasCliente getPreferenciasCliente() {
		return this.preferenciasCliente;
	}

	public void setPreferenciasCliente(PreferenciasCliente preferenciasCliente) {
		this.preferenciasCliente = preferenciasCliente;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo addVehiculo(Vehiculo vehiculo) {
		getVehiculos().add(vehiculo);
		vehiculo.setCliente(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setCliente(null);

		return vehiculo;
	}

}