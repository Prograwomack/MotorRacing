package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tarifas_mano_obra database table.
 * 
 */
@Entity
@Table(name="tarifas_mano_obra")
@NamedQuery(name="TarifasManoObra.findAll", query="SELECT t FROM TarifasManoObra t")
public class TarifasManoObra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="precio_hora")
	private float precioHora;

	private String seccion;

	//bi-directional many-to-one association to LineasFacturasManoObra
	@OneToMany(mappedBy="tarifasManoObra")
	private List<LineasFacturasManoObra> lineasFacturasManoObras;

	//bi-directional many-to-one association to ManoObraOrdenesTaller
	@OneToMany(mappedBy="tarifasManoObra")
	private List<ManoObraOrdenesTaller> manoObraOrdenesTallers;

	//bi-directional many-to-one association to TiposVehiculo
	@ManyToOne
	@JoinColumn(name="id_tipo_vehiculo")
	private TiposVehiculo tiposVehiculo;

	public TarifasManoObra() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrecioHora() {
		return this.precioHora;
	}

	public void setPrecioHora(float precioHora) {
		this.precioHora = precioHora;
	}

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public List<LineasFacturasManoObra> getLineasFacturasManoObras() {
		return this.lineasFacturasManoObras;
	}

	public void setLineasFacturasManoObras(List<LineasFacturasManoObra> lineasFacturasManoObras) {
		this.lineasFacturasManoObras = lineasFacturasManoObras;
	}

	public LineasFacturasManoObra addLineasFacturasManoObra(LineasFacturasManoObra lineasFacturasManoObra) {
		getLineasFacturasManoObras().add(lineasFacturasManoObra);
		lineasFacturasManoObra.setTarifasManoObra(this);

		return lineasFacturasManoObra;
	}

	public LineasFacturasManoObra removeLineasFacturasManoObra(LineasFacturasManoObra lineasFacturasManoObra) {
		getLineasFacturasManoObras().remove(lineasFacturasManoObra);
		lineasFacturasManoObra.setTarifasManoObra(null);

		return lineasFacturasManoObra;
	}

	public List<ManoObraOrdenesTaller> getManoObraOrdenesTallers() {
		return this.manoObraOrdenesTallers;
	}

	public void setManoObraOrdenesTallers(List<ManoObraOrdenesTaller> manoObraOrdenesTallers) {
		this.manoObraOrdenesTallers = manoObraOrdenesTallers;
	}

	public ManoObraOrdenesTaller addManoObraOrdenesTaller(ManoObraOrdenesTaller manoObraOrdenesTaller) {
		getManoObraOrdenesTallers().add(manoObraOrdenesTaller);
		manoObraOrdenesTaller.setTarifasManoObra(this);

		return manoObraOrdenesTaller;
	}

	public ManoObraOrdenesTaller removeManoObraOrdenesTaller(ManoObraOrdenesTaller manoObraOrdenesTaller) {
		getManoObraOrdenesTallers().remove(manoObraOrdenesTaller);
		manoObraOrdenesTaller.setTarifasManoObra(null);

		return manoObraOrdenesTaller;
	}

	public TiposVehiculo getTiposVehiculo() {
		return this.tiposVehiculo;
	}

	public void setTiposVehiculo(TiposVehiculo tiposVehiculo) {
		this.tiposVehiculo = tiposVehiculo;
	}

}