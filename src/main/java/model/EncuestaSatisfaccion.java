package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the encuesta_satisfaccion database table.
 * 
 */
@Entity
@Table(name="encuesta_satisfaccion")
@NamedQuery(name="EncuestaSatisfaccion.findAll", query="SELECT e FROM EncuestaSatisfaccion e")
public class EncuestaSatisfaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_encuenta")
	private Date fechaEncuenta;

	private int puntuacion;

	//bi-directional many-to-one association to CabecerasFactura
	@ManyToOne
	@JoinColumn(name="id_factura")
	private CabecerasFactura cabecerasFactura;

	public EncuestaSatisfaccion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaEncuenta() {
		return this.fechaEncuenta;
	}

	public void setFechaEncuenta(Date fechaEncuenta) {
		this.fechaEncuenta = fechaEncuenta;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public CabecerasFactura getCabecerasFactura() {
		return this.cabecerasFactura;
	}

	public void setCabecerasFactura(CabecerasFactura cabecerasFactura) {
		this.cabecerasFactura = cabecerasFactura;
	}

}