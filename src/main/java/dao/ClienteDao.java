package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import estructuraBaseDeDatos.Campo;
import estructuraBaseDeDatos.EstructuraBbdd;
import estructuraBaseDeDatos.Tabla;
import interfaces.Dao;
import main.Conexion;
import mensajes.Mensajes;
import model.Cliente;
import model.Login;

public class ClienteDao extends DaoPrincipal implements Dao<Cliente> {
	private String tabla = "clientes";

	public ClienteDao(EstructuraBbdd estructuraTablas, Connection conexion) {
		super(estructuraTablas, conexion);
	}

	@Override
	public int insert(Cliente cliente) {
		String ordenSql = ordenSqlInsert(tabla);

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setNull(1, 0); // id auto-generado
			introduceEntityEnPs(2, cliente, ps).executeUpdate();
			ResultSet claveGenerada = ps.getGeneratedKeys();
			claveGenerada.next();
			return claveGenerada.getInt(1); // Devuelve la id auto-generada
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

	public PreparedStatement introduceEntityEnPs(int primerIndice, Cliente cliente, PreparedStatement ps)
			throws SQLException {
		// Introduce los datos de la Entity en el PreparedStatement. Se utiliza
		// primerIndice para empezar en el 2 en Insert y en el 1 en Update.
		ps.setString(primerIndice, cliente.getDni());
		ps.setString(primerIndice + 1, cliente.getNombre());
		ps.setString(primerIndice + 2, cliente.getApellido1());
		ps.setString(primerIndice + 3, cliente.getApellido2());
		ps.setInt(primerIndice + 4, cliente.getTelefono());
		ps.setString(primerIndice + 5, cliente.getTipoVia());
		ps.setString(primerIndice + 6, cliente.getNombreVia());
		ps.setInt(primerIndice + 7, cliente.getNumeroVia());
		ps.setInt(primerIndice + 8, cliente.getPiso());
		ps.setString(primerIndice + 9, cliente.getPuerta());
		ps.setInt(primerIndice + 10, cliente.getCodigoPostal());
		ps.setString(primerIndice + 11, cliente.getProvincia());
		ps.setInt(primerIndice + 12, cliente.getLogin().getId());
		return ps;
	}

	@Override
	public Cliente read(int id) {
		Cliente cliente = new Cliente();
		String ordenSql = "select * from " + tabla + " where id= ?";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, id);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				rs.next();
				cliente = cargaDatosEnEntity(rs);
			} catch (SQLException e) {
				System.out.println("El id " + id + " no existe en la base de datos.");
				cliente = null;
			}
		} catch (Exception e1) {
			System.out.println(Mensajes.ERRORDELECTURA + e1.getMessage());
			cliente = null;
		}
		return cliente;
	}

	public Cliente cargaDatosEnEntity(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt(1));
		cliente.setDni(rs.getString(2));
		cliente.setNombre(rs.getString(3));
		cliente.setApellido1(rs.getString(4));
		cliente.setApellido2(rs.getString(5));
		cliente.setTelefono(rs.getInt(6));
		cliente.setTipoVia(rs.getString(7));
		cliente.setNombreVia(rs.getString(8));
		cliente.setNumeroVia(rs.getInt(9));
		cliente.setPiso(rs.getInt(10));
		cliente.setPuerta(rs.getString(11));
		cliente.setCodigoPostal(rs.getInt(12));
		cliente.setProvincia(rs.getString(13));
		Login loginId = new Login();
		loginId.setId(rs.getInt(14));
		cliente.setLogin(loginId);
		return cliente;
	}

	public List<Cliente> read(String datoBuscado, String nombreCampo) {
		List<Cliente> listaResultado = new ArrayList<>();
		String ordenarPor1 = nombreCampo;
		String ordenSql = "select * from " + tabla + " where " + nombreCampo + "= ? order by " + ordenarPor1 + " desc";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setString(1, datoBuscado);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente = cargaDatosEnEntity(rs);
					listaResultado.add(cliente);
				}
				return listaResultado;
			} catch (SQLException e) {
				return Collections.emptyList();
			}
		} catch (Exception e1) {
			System.out.println(Mensajes.ERRORDELECTURA + e1.getMessage());
			return Collections.emptyList();
		}
	}

	public List<Cliente> readIdLogin(int idLogin) {
		List<Cliente> listaResultado = new ArrayList<>();
		String nombreCampo = "id_login";
		String ordenarPor1 = "apellido1";
		String ordenarPor2 = "apellido2";
		String ordenarPor3 = "nombre";
		String ordenSql = "select * from " + tabla + " where " + nombreCampo + "= ? order by " + ordenarPor1 + ","
				+ ordenarPor2 + "," + ordenarPor3 + " desc";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, idLogin);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente = cargaDatosEnEntity(rs);
					listaResultado.add(cliente);
				}
				return listaResultado;
			} catch (SQLException e) {
				return Collections.emptyList();
			}
		} catch (Exception e1) {
			System.out.println(Mensajes.ERRORDELECTURA + e1.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<Cliente> getAll() {
		List<Cliente> listaResultado = new ArrayList<>();
		String ordenarPor1 = "apellido1";
		String ordenarPor2 = "apellido2";
		String ordenarPor3 = "nombre";
		String ordenSql = "select * from " + tabla + " order by " + ordenarPor1 + "," + ordenarPor2 + "," + ordenarPor3
				+ " desc";

		try (Statement stm = getConexionAbierta().createStatement(); ResultSet rs = stm.executeQuery(ordenSql)) {
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente = cargaDatosEnEntity(rs);
				listaResultado.add(cliente);
			}
			return listaResultado;
		} catch (Exception e) {
			System.out.println(Mensajes.ERRORDELECTURA + e.toString());
			return Collections.emptyList();
		}
	}

	@Override
	public int update(Cliente cliente) {
		Tabla tablaBuscada;
		int numeroCamposEnTabla;
		String ordenSql;

		ordenSql = ordenSqlUpdate(tabla);
		tablaBuscada = getEstructuraTablas().buscaTabla(tabla);
		numeroCamposEnTabla = tablaBuscada.getCampos().size();
		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(numeroCamposEnTabla, cliente.getId());
			return introduceEntityEnPs(1, cliente, ps).executeUpdate();
//			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(Cliente cliente) {
		String campoBusqueda = "id";
		String ordenSql = "delete from " + tabla + " WHERE " + campoBusqueda + "=?";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, cliente.getId());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_BORRAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

}
