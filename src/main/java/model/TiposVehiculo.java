package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_vehiculos database table.
 * 
 */
@Entity
@Table(name="tipos_vehiculos")
@NamedQuery(name="TiposVehiculo.findAll", query="SELECT t FROM TiposVehiculo t")
public class TiposVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String tipo;

	//bi-directional many-to-one association to TarifasManoObra
	@OneToMany(mappedBy="tiposVehiculo")
	private List<TarifasManoObra> tarifasManoObras;

	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="tiposVehiculo")
	private List<Vehiculo> vehiculos;

	public TiposVehiculo() {
	}
	
	public TiposVehiculo(String tipo) {
		super();
		this.tipo = tipo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<TarifasManoObra> getTarifasManoObras() {
		return this.tarifasManoObras;
	}

	public void setTarifasManoObras(List<TarifasManoObra> tarifasManoObras) {
		this.tarifasManoObras = tarifasManoObras;
	}

	public TarifasManoObra addTarifasManoObra(TarifasManoObra tarifasManoObra) {
		getTarifasManoObras().add(tarifasManoObra);
		tarifasManoObra.setTiposVehiculo(this);

		return tarifasManoObra;
	}

	public TarifasManoObra removeTarifasManoObra(TarifasManoObra tarifasManoObra) {
		getTarifasManoObras().remove(tarifasManoObra);
		tarifasManoObra.setTiposVehiculo(null);

		return tarifasManoObra;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo addVehiculo(Vehiculo vehiculo) {
		getVehiculos().add(vehiculo);
		vehiculo.setTiposVehiculo(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setTiposVehiculo(null);

		return vehiculo;
	}

}