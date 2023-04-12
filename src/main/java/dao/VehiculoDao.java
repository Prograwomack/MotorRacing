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
import model.TiposVehiculo;
import model.Vehiculo;

public class VehiculoDao extends DaoPrincipal implements Dao<Vehiculo> {
	private String tabla = "clientes";

	public VehiculoDao(EstructuraBbdd estructuraTablas, Connection conexion) {
		super(estructuraTablas, conexion);
	}

	@Override
	public int insert(Vehiculo v) {
		String ordenSql = ordenSqlInsert(tabla);

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setNull(1, 0); // id auto-generado
			introduceEntityEnPs(2, v, ps).executeUpdate();
			ResultSet claveGenerada = ps.getGeneratedKeys();
			claveGenerada.next();
			return claveGenerada.getInt(1); // Devuelve la id auto-generada
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

	public PreparedStatement introduceEntityEnPs(int primerIndice, Vehiculo v, PreparedStatement ps)
			throws SQLException {
		// Introduce los datos de la Entity en el PreparedStatement. Se utiliza
		// primerIndice para empezar en el 2 en Insert y en el 1 en Update.
		ps.setInt(primerIndice, v.getCliente().getId());
		ps.setInt(primerIndice + 1, v.getTiposVehiculo().getId());
		ps.setString(primerIndice + 2, v.getMatricula());
		ps.setString(primerIndice + 3, v.getModelo());
		ps.setString(primerIndice + 4, v.getMarca());
		ps.setInt(primerIndice + 5, v.getAño());
		ps.setString(primerIndice + 6, v.getNumeroBastidor());
		return ps;
	}

	@Override
	public Vehiculo read(int id) {
		Vehiculo v = new Vehiculo();
		String ordenSql = "select * from " + tabla + " where id= ?";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, id);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				rs.next();
				v = cargaDatosEnEntity(rs);
			} catch (SQLException e) {
				System.out.println("El id " + id + " no existe en la base de datos.");
				v = null;
			}
		} catch (Exception e1) {
			System.out.println(Mensajes.ERRORDELECTURA + e1.getMessage());
			v = null;
		}
		return v;
	}

	public Vehiculo cargaDatosEnEntity(ResultSet rs) throws SQLException {
		Vehiculo v = new Vehiculo();
		v.setId(rs.getInt(1));
		v.setCliente(new Cliente(rs.getInt(2)));
		v.setTiposVehiculo(new TiposVehiculo(rs.getString(3)));
		v.setMatricula(rs.getString(4));
		v.setMarca(rs.getString(5));
		v.setModelo(rs.getString(6));
		v.setAño(rs.getInt(7));		
		v.setNumeroBastidor(rs.getString(7));		
		return v;
	}

	public List<Vehiculo> read(String datoBuscado, String nombreCampo) {
		List<Vehiculo> listaResultado = new ArrayList<>();
		String ordenarPor1 = nombreCampo;
		String ordenSql = "select * from " + tabla + " where " + nombreCampo + "= ? order by " + ordenarPor1 + " desc";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setString(1, datoBuscado);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				while (rs.next()) {
					Vehiculo v = new Vehiculo();
					v = cargaDatosEnEntity(rs);
					listaResultado.add(v);
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

	public List<Vehiculo> readIdLogin(int idLogin) {
		List<Vehiculo> listaResultado = new ArrayList<>();
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
					Vehiculo v = new Vehiculo();
					v = cargaDatosEnEntity(rs);
					listaResultado.add(v);
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
	public List<Vehiculo> getAll() {
		List<Vehiculo> listaResultado = new ArrayList<>();
		String ordenarPor1 = "apellido1";
		String ordenarPor2 = "apellido2";
		String ordenarPor3 = "nombre";
		String ordenSql = "select * from " + tabla + " order by " + ordenarPor1 + "," + ordenarPor2 + "," + ordenarPor3
				+ " desc";

		try (Statement stm = getConexionAbierta().createStatement(); ResultSet rs = stm.executeQuery(ordenSql)) {
			while (rs.next()) {
				Vehiculo v = new Vehiculo();
				v = cargaDatosEnEntity(rs);
				listaResultado.add(v);
			}
			return listaResultado;
		} catch (Exception e) {
			System.out.println(Mensajes.ERRORDELECTURA + e.toString());
			return Collections.emptyList();
		}
	}

	@Override
	public int update(Vehiculo v) {
		Tabla tablaBuscada;
		int numeroCamposEnTabla;
		String ordenSql;

		ordenSql = ordenSqlUpdate(tabla);
		tablaBuscada = getEstructuraTablas().buscaTabla(tabla);
		numeroCamposEnTabla = tablaBuscada.getCampos().size();
		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(numeroCamposEnTabla, v.getId());
			return introduceEntityEnPs(1, v, ps).executeUpdate();
//			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(Vehiculo v) {
		String campoBusqueda = "id";
		String ordenSql = "delete from " + tabla + " WHERE " + campoBusqueda + "=?";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, v.getId());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_BORRAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

}
