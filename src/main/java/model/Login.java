package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the logins database table.
 * 
 */
@Entity
@Table(name="logins")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name="pass_hash")
	private String passHash;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="login")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Sesione
	@OneToMany(mappedBy="login")
	private List<Sesiones> sesiones;

	public Login() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassHash() {
		return this.passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setLogin(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setLogin(null);

		return cliente;
	}

	public List<Sesiones> getSesiones() {
		return this.sesiones;
	}

	public void setSesiones(List<Sesiones> sesiones) {
		this.sesiones = sesiones;
	}

	public Sesiones addSesione(Sesiones sesione) {
		getSesiones().add(sesione);
		sesione.setLogin(this);

		return sesione;
	}

	public Sesiones removeSesione(Sesiones sesione) {
		getSesiones().remove(sesione);
		sesione.setLogin(null);

		return sesione;
	}

}