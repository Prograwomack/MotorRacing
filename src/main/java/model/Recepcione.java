package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the recepciones database table.
 * 
 */
@Entity
@Table(name="recepciones")
@NamedQuery(name="Recepcione.findAll", query="SELECT r FROM Recepcione r")
public class Recepcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora")
	private Date fechaHora;

	private String foto;

	//bi-directional many-to-one association to CabecerasDiagnostico
	@ManyToOne
	@JoinColumn(name="id_diagnostico_inicial")
	private CabecerasDiagnostico cabecerasDiagnostico1;

	//bi-directional many-to-one association to CabecerasDiagnostico
	@ManyToOne
	@JoinColumn(name="id_diagnostico_final")
	private CabecerasDiagnostico cabecerasDiagnostico2;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleado empleado;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	public Recepcione() {
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

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public CabecerasDiagnostico getCabecerasDiagnostico1() {
		return this.cabecerasDiagnostico1;
	}

	public void setCabecerasDiagnostico1(CabecerasDiagnostico cabecerasDiagnostico1) {
		this.cabecerasDiagnostico1 = cabecerasDiagnostico1;
	}

	public CabecerasDiagnostico getCabecerasDiagnostico2() {
		return this.cabecerasDiagnostico2;
	}

	public void setCabecerasDiagnostico2(CabecerasDiagnostico cabecerasDiagnostico2) {
		this.cabecerasDiagnostico2 = cabecerasDiagnostico2;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}