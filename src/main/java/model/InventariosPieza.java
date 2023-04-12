package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inventarios_piezas database table.
 * 
 */
@Entity
@Table(name="inventarios_piezas")
@NamedQuery(name="InventariosPieza.findAll", query="SELECT i FROM InventariosPieza i")
public class InventariosPieza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String nombre;

	@Column(name="precio_venta")
	private float precioVenta;

	@Column(name="stock_total")
	private int stockTotal;

	//bi-directional many-to-one association to LineasFacturasPieza
	@OneToMany(mappedBy="inventariosPieza")
	private List<LineasFacturasPieza> lineasFacturasPiezas;

	//bi-directional many-to-one association to PiezasOrdenesTaller
	@OneToMany(mappedBy="inventariosPieza")
	private List<PiezasOrdenesTaller> piezasOrdenesTallers;

	public InventariosPieza() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getStockTotal() {
		return this.stockTotal;
	}

	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}

	public List<LineasFacturasPieza> getLineasFacturasPiezas() {
		return this.lineasFacturasPiezas;
	}

	public void setLineasFacturasPiezas(List<LineasFacturasPieza> lineasFacturasPiezas) {
		this.lineasFacturasPiezas = lineasFacturasPiezas;
	}

	public LineasFacturasPieza addLineasFacturasPieza(LineasFacturasPieza lineasFacturasPieza) {
		getLineasFacturasPiezas().add(lineasFacturasPieza);
		lineasFacturasPieza.setInventariosPieza(this);

		return lineasFacturasPieza;
	}

	public LineasFacturasPieza removeLineasFacturasPieza(LineasFacturasPieza lineasFacturasPieza) {
		getLineasFacturasPiezas().remove(lineasFacturasPieza);
		lineasFacturasPieza.setInventariosPieza(null);

		return lineasFacturasPieza;
	}

	public List<PiezasOrdenesTaller> getPiezasOrdenesTallers() {
		return this.piezasOrdenesTallers;
	}

	public void setPiezasOrdenesTallers(List<PiezasOrdenesTaller> piezasOrdenesTallers) {
		this.piezasOrdenesTallers = piezasOrdenesTallers;
	}

	public PiezasOrdenesTaller addPiezasOrdenesTaller(PiezasOrdenesTaller piezasOrdenesTaller) {
		getPiezasOrdenesTallers().add(piezasOrdenesTaller);
		piezasOrdenesTaller.setInventariosPieza(this);

		return piezasOrdenesTaller;
	}

	public PiezasOrdenesTaller removePiezasOrdenesTaller(PiezasOrdenesTaller piezasOrdenesTaller) {
		getPiezasOrdenesTallers().remove(piezasOrdenesTaller);
		piezasOrdenesTaller.setInventariosPieza(null);

		return piezasOrdenesTaller;
	}

}