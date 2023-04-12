package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sesiones database table.
 * 
 */
@Entity
@Table(name="sesiones")
@NamedQuery(name="Sesiones.findAll", query="SELECT s FROM Sesiones s")
public class Sesiones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fin_sesion")
	private Date finSesion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inicio_sesion")
	private Date inicioSesion;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="id_login")
	private Login login;
	
	// Creado por JL
//	private int idLogin;
//
//	public Sesiones() {
//	}
//	
//	public int getIdLogin() {
//		return idLogin;
//	}
//
//	public void setIdLogin(int idLogin) {
//		this.idLogin=idLogin;
//	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFinSesion() {
		return this.finSesion;
	}

	public void setFinSesion(Date finSesion) {
		this.finSesion = finSesion;
	}

	public Date getInicioSesion() {
		return this.inicioSesion;
	}

	public void setInicioSesion(Date inicioSesion) {
		this.inicioSesion = inicioSesion;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}