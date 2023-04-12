package dao;

import interfaces.Dao;
import main.Conexion;
import mensajes.Mensajes;
import model.Cliente;
import model.Login;
import model.Sesiones;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import estructuraBaseDeDatos.EstructuraBbdd;
import estructuraBaseDeDatos.Tabla;

import java.util.Date;

public class SesionesDao extends DaoPrincipal implements Dao<Sesiones> {
	private String tabla = "sesiones";

	public SesionesDao(EstructuraBbdd estructuraTablas, Connection conexion) {
		super(estructuraTablas, conexion);
	}

	@Override
	public int insert(Sesiones sesion) {
		String ordenSql = ordenSqlInsert(tabla);

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setNull(1, 0); // id auto-generado
			introduceEntityEnPs(2, sesion, ps).executeUpdate();
			ResultSet claveGenerada = ps.getGeneratedKeys();
			claveGenerada.next();
			return claveGenerada.getInt(1); // Le decimos que guarde en id la clave generada en la primera columna.
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

	public PreparedStatement introduceEntityEnPs(int primerIndice, Sesiones sesion, PreparedStatement ps)
			throws SQLException {

		if (sesion.getInicioSesion() != null)
			ps.setTimestamp(primerIndice, new Timestamp(sesion.getInicioSesion().getTime()));
		else
			ps.setTimestamp(primerIndice, null); // Para guardar sólo la fecha ps.setDate(2, new
													// java.sql.Date(sesion.getInicioSesion().getTime()))
		if (sesion.getFinSesion() != null)
			ps.setTimestamp(primerIndice + 1, new Timestamp(sesion.getFinSesion().getTime()));
		else
			ps.setTimestamp(primerIndice + 1, null);
		ps.setInt(primerIndice + 2, sesion.getLogin().getId());
		return ps;
	}

	@Override
	public Sesiones read(int id) {
		Sesiones sesion = new Sesiones();
		String ordenSql = "select * from " + tabla + " where id= ?";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, id);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				rs.next();
				sesion = cargaDatosEnEntity(rs);
			} catch (SQLException e) {
				System.out.println("El id " + id + " no existe en la base de datos.");
				sesion = null;
			}
		} catch (Exception e1) {
			System.out.println(Mensajes.ERRORDELECTURA + e1.getMessage());
		}
		return sesion;
	}

	public Sesiones cargaDatosEnEntity(ResultSet rs) throws SQLException {

		Sesiones sesion = new Sesiones();
		sesion.setId(rs.getInt(1));
		if (rs.getDate(2) == null)
			sesion.setInicioSesion(null);
		else
			sesion.setInicioSesion(new Date(rs.getTimestamp(2).getTime()));
		if (rs.getDate(3) == null)
			sesion.setFinSesion(null);
		else
			sesion.setFinSesion(new Date(rs.getTimestamp(3).getTime()));
		Login loginId = new Login();
		loginId.setId(rs.getInt(4));
		sesion.setLogin(loginId);
		return sesion;
	}

	public List<Sesiones> read(String datoBuscado, String nombreCampo) {
		List<Sesiones> listaResultado = new ArrayList<>();
		String ordenarPor = "inicio_sesion";
		String ordenSql = "select * from " + tabla + " where " + nombreCampo + "= ? order by " + ordenarPor + " desc";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setString(1, datoBuscado);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				while (rs.next()) {
					Sesiones sesion = new Sesiones();
					sesion = cargaDatosEnEntity(rs);
					listaResultado.add(sesion);
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

	public List<Sesiones> readIdLogin(int idLogin) {
		List<Sesiones> listaResultado = new ArrayList<>();
		String nombreCampo = "id_login";
		String ordenarPor = "inicio_sesion";
		String ordenSql = "select * from " + tabla + " where " + nombreCampo + "= ? order by " + ordenarPor + " desc";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, idLogin);
			ps.executeQuery();
			try (ResultSet rs = ps.getResultSet()) {
				while (rs.next()) {
					Sesiones sesion = new Sesiones();
					sesion = cargaDatosEnEntity(rs);
					listaResultado.add(sesion);
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
	public List<Sesiones> getAll() {
		List<Sesiones> listaResultado = new ArrayList<>();
		String ordenarPor = "inicio_sesion";
		String ordenSql = "select * from " + tabla + " order by " + ordenarPor + " desc";

		try (Statement stm = getConexionAbierta().createStatement(); ResultSet rs = stm.executeQuery(ordenSql)) {
			while (rs.next()) {
				Sesiones sesion = new Sesiones();
				sesion = cargaDatosEnEntity(rs);
				listaResultado.add(sesion);
			}
			return listaResultado;
		} catch (Exception e) {
			System.out.println(Mensajes.ERRORDELECTURA + e.toString());
			return Collections.emptyList();
		}
	}

	@Override
	public int update(Sesiones sesion) {
		Tabla tablaBuscada;
		int numeroCamposEnTabla;

		String ordenSql = ordenSqlUpdate(tabla);
		tablaBuscada = getEstructuraTablas().buscaTabla(tabla);
		numeroCamposEnTabla = tablaBuscada.getCampos().size();
		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(numeroCamposEnTabla, sesion.getId());
			return introduceEntityEnPs(1, sesion, ps).executeUpdate();
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(Sesiones sesion) {
		String campoBusqueda = "id";
		String ordenSql = "delete from " + tabla + " WHERE " + campoBusqueda + "=?";

		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql)) {
			ps.setInt(1, sesion.getId());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(Mensajes.ERROR_BORRAR_REGISTRO + e.getMessage());
			return 0;
		}
	}
}
