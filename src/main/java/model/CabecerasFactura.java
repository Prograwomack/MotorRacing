package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cabeceras_facturas database table.
 * 
 */
@Entity
@Table(name="cabeceras_facturas")
@NamedQuery(name="CabecerasFactura.findAll", query="SELECT c FROM CabecerasFactura c")
public class CabecerasFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora")
	private Date fechaHora;

	//bi-directional many-to-one association to CabecerasOrdenesTaller
	@ManyToOne
	@JoinColumn(name="id_orden_taller")
	private CabecerasOrdenesTaller cabecerasOrdenesTaller;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	//bi-directional many-to-one association to ClientesFacturasRecibo
	@OneToMany(mappedBy="cabecerasFactura")
	private List<ClientesFacturasRecibo> clientesFacturasRecibos;

	//bi-directional many-to-one association to EncuestaSatisfaccion
	@OneToMany(mappedBy="cabecerasFactura")
	private List<EncuestaSatisfaccion> encuestaSatisfaccions;

	//bi-directional many-to-one association to LineasFactura
	@OneToMany(mappedBy="cabecerasFactura")
	private List<LineasFactura> lineasFacturas;

	//bi-directional many-to-one association to LineasFacturasManoObra
	@OneToMany(mappedBy="cabecerasFactura")
	private List<LineasFacturasManoObra> lineasFacturasManoObras;

	//bi-directional many-to-one association to LineasFacturasPieza
	@OneToMany(mappedBy="cabecerasFactura")
	private List<LineasFacturasPieza> lineasFacturasPiezas;

	//bi-directional many-to-one association to Recibo
	@OneToMany(mappedBy="cabecerasFactura")
	private List<Recibo> recibos;

	public CabecerasFactura() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public CabecerasOrdenesTaller getCabecerasOrdenesTaller() {
		return this.cabecerasOrdenesTaller;
	}

	public void setCabecerasOrdenesTaller(CabecerasOrdenesTaller cabecerasOrdenesTaller) {
		this.cabecerasOrdenesTaller = cabecerasOrdenesTaller;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<ClientesFacturasRecibo> getClientesFacturasRecibos() {
		return this.clientesFacturasRecibos;
	}

	public void setClientesFacturasRecibos(List<ClientesFacturasRecibo> clientesFacturasRecibos) {
		this.clientesFacturasRecibos = clientesFacturasRecibos;
	}

	public ClientesFacturasRecibo addClientesFacturasRecibo(ClientesFacturasRecibo clientesFacturasRecibo) {
		getClientesFacturasRecibos().add(clientesFacturasRecibo);
		clientesFacturasRecibo.setCabecerasFactura(this);

		return clientesFacturasRecibo;
	}

	public ClientesFacturasRecibo removeClientesFacturasRecibo(ClientesFacturasRecibo clientesFacturasRecibo) {
		getClientesFacturasRecibos().remove(clientesFacturasRecibo);
		clientesFacturasRecibo.setCabecerasFactura(null);

		return clientesFacturasRecibo;
	}

	public List<EncuestaSatisfaccion> getEncuestaSatisfaccions() {
		return this.encuestaSatisfaccions;
	}

	public void setEncuestaSatisfaccions(List<EncuestaSatisfaccion> encuestaSatisfaccions) {
		this.encuestaSatisfaccions = encuestaSatisfaccions;
	}

	public EncuestaSatisfaccion addEncuestaSatisfaccion(EncuestaSatisfaccion encuestaSatisfaccion) {
		getEncuestaSatisfaccions().add(encuestaSatisfaccion);
		encuestaSatisfaccion.setCabecerasFactura(this);

		return encuestaSatisfaccion;
	}

	public EncuestaSatisfaccion removeEncuestaSatisfaccion(EncuestaSatisfaccion encuestaSatisfaccion) {
		getEncuestaSatisfaccions().remove(encuestaSatisfaccion);
		encuestaSatisfaccion.setCabecerasFactura(null);

		return encuestaSatisfaccion;
	}

	public List<LineasFactura> getLineasFacturas() {
		return this.lineasFacturas;
	}

	public void setLineasFacturas(List<LineasFactura> lineasFacturas) {
		this.lineasFacturas = lineasFacturas;
	}

	public LineasFactura addLineasFactura(LineasFactura lineasFactura) {
		getLineasFacturas().add(lineasFactura);
		lineasFactura.setCabecerasFactura(this);

		return lineasFactura;
	}

	public LineasFactura removeLineasFactura(LineasFactura lineasFactura) {
		getLineasFacturas().remove(lineasFactura);
		lineasFactura.setCabecerasFactura(null);

		return lineasFactura;
	}

	public List<LineasFacturasManoObra> getLineasFacturasManoObras() {
		return this.lineasFacturasManoObras;
	}

	public void setLineasFacturasManoObras(List<LineasFacturasManoObra> lineasFacturasManoObras) {
		this.lineasFacturasManoObras = lineasFacturasManoObras;
	}

	public LineasFacturasManoObra addLineasFacturasManoObra(LineasFacturasManoObra lineasFacturasManoObra) {
		getLineasFacturasManoObras().add(lineasFacturasManoObra);
		lineasFacturasManoObra.setCabecerasFactura(this);

		return lineasFacturasManoObra;
	}

	public LineasFacturasManoObra removeLineasFacturasManoObra(LineasFacturasManoObra lineasFacturasManoObra) {
		getLineasFacturasManoObras().remove(lineasFacturasManoObra);
		lineasFacturasManoObra.setCabecerasFactura(null);

		return lineasFacturasManoObra;
	}

	public List<LineasFacturasPieza> getLineasFacturasPiezas() {
		return this.lineasFacturasPiezas;
	}

	public void setLineasFacturasPiezas(List<LineasFacturasPieza> lineasFacturasPiezas) {
		this.lineasFacturasPiezas = lineasFacturasPiezas;
	}

	public LineasFacturasPieza addLineasFacturasPieza(LineasFacturasPieza lineasFacturasPieza) {
		getLineasFacturasPiezas().add(lineasFacturasPieza);
		lineasFacturasPieza.setCabecerasFactura(this);

		return lineasFacturasPieza;
	}

	public LineasFacturasPieza removeLineasFacturasPieza(LineasFacturasPieza lineasFacturasPieza) {
		getLineasFacturasPiezas().remove(lineasFacturasPieza);
		lineasFacturasPieza.setCabecerasFactura(null);

		return lineasFacturasPieza;
	}

	public List<Recibo> getRecibos() {
		return this.recibos;
	}

	public void setRecibos(List<Recibo> recibos) {
		this.recibos = recibos;
	}

	public Recibo addRecibo(Recibo recibo) {
		getRecibos().add(recibo);
		recibo.setCabecerasFactura(this);

		return recibo;
	}

	public Recibo removeRecibo(Recibo recibo) {
		getRecibos().remove(recibo);
		recibo.setCabecerasFactura(null);

		return recibo;
	}

}