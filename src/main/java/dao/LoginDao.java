package dao;

import interfaces.Dao;
import main.Conexion;
import mensajes.Mensajes;
import model.Login;
import model.Sesiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

import estructuraBaseDeDatos.Campo;
import estructuraBaseDeDatos.EstructuraBbdd;
import estructuraBaseDeDatos.Tabla;


public class LoginDao extends DaoPrincipal implements Dao<Login> {
private String tabla="logins";

	public LoginDao(EstructuraBbdd estructuraTablas, Connection conexion) {
		super(estructuraTablas, conexion);
	}

	@Override
	public int insert(Login login) {
		String ordenSql=ordenSqlInsert(tabla);
		try (PreparedStatement ps = getConexionAbierta().prepareStatement(ordenSql, Statement.RETURN_GENERATED_KEYS);
				) {
			ps.setNull(1, 0);										// id auto-generado
			introduceEntityEnPs(2,login,ps).executeUpdate();
			ResultSet claveGenerada=ps.getGeneratedKeys();
			claveGenerada.next();									
			return claveGenerada.getInt(1); // Devuelve la id auto-generada
		}catch (Exception e){		
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage());
			return 0;
		}
	}
	
	public PreparedStatement introduceEntityEnPs(int primerIndice, Login login, PreparedStatement ps) throws SQLException {
		
		ps.setString(primerIndice, login.getEmail());
		ps.setString(primerIndice+1, login.getPassHash());	
		return ps;		
	}
	
	@Override
	public Login read  (int id) {
		Login login=new Login();
		String ordenSql="select * from "+tabla+ " where id= ?";
		try(PreparedStatement ps=getConexionAbierta().prepareStatement(ordenSql)){	
			ps.setInt(1, id);
			ps.executeQuery();
			try (ResultSet rs=ps.getResultSet()){
				rs.next();
				login=cargaDatosEnEntity(rs);
			}catch(SQLException e){
				System.out.println("El id "+id+" no existe en la base de datos.");
				login=null;
			}
		}catch (Exception e1){	
			System.out.println(Mensajes.ERRORDELECTURA+e1.getMessage());
		} 
		return  login;
	}
	
	public Login cargaDatosEnEntity(ResultSet rs) throws SQLException {
		
		Login login=new Login();
		login.setId(rs.getInt(1));
		login.setEmail(rs.getString(2));
		login.setPassHash(rs.getNString(3));
		return login;
	}
	
	public Login read  (String datoBuscado, String nombreCampo) {
		Login login=new Login();
		String ordenSql="select * from "+tabla+ " where "+nombreCampo+"= ?";
		try(PreparedStatement ps=getConexionAbierta().prepareStatement(ordenSql)){	
			ps.setString(1, datoBuscado);
			ps.executeQuery();
			try (ResultSet rs=ps.getResultSet()){
				rs.next();
				login=cargaDatosEnEntity(rs);
			}catch(SQLException e){
				login=null;
			}
		}catch (Exception e1){	
			System.out.println(Mensajes.ERRORDELECTURA+e1.getMessage());
		}
		return  login;
	}
	
	@Override
	public List<Login> getAll() {
		List <Login> listaResultado=new ArrayList<>();
		String ordenarPor="email";
		String ordenSql="select * from "+tabla+" order by "+ordenarPor+" asc";
		try(Statement stm=getConexionAbierta().createStatement();
				ResultSet rs=stm.executeQuery(ordenSql)){	
			while (rs.next()) {
				Login login=new Login();
				login=cargaDatosEnEntity(rs);
				listaResultado.add(login);
			}
			return listaResultado;
		}catch (Exception e){		
			System.out.println(Mensajes.ERRORDELECTURA + e.toString() );
			return Collections.emptyList();
		}
	}

	@Override
	public int update(Login login) {
		Tabla tablaBuscada;
		int numeroCamposEnTabla;
		
		String ordenSql=ordenSqlUpdate(tabla);
		tablaBuscada=getEstructuraTablas().buscaTabla(tabla);
		numeroCamposEnTabla=tablaBuscada.getCampos().size();
		try(PreparedStatement ps=getConexionAbierta().prepareStatement(ordenSql)){
			ps.setInt(numeroCamposEnTabla, login.getId());
			return introduceEntityEnPs(1, login, ps).executeUpdate();
		}catch (Exception e){		
			System.out.println(Mensajes.ERROR_GRABAR_REGISTRO + e.getMessage() );
			return 0;
		}
	}

	@Override
	public int delete(Login login) {
		String campoBusqueda="id";
		String ordenSql="delete from "+tabla+" WHERE "+campoBusqueda+"=?";
		try(PreparedStatement ps=getConexionAbierta().prepareStatement(ordenSql)){
			ps.setInt(1, login.getId());
			return ps.executeUpdate();
		}catch (Exception e){		
			System.out.println(Mensajes.ERROR_BORRAR_REGISTRO + e.getMessage() );
			return 0;
		}		
	}
}
