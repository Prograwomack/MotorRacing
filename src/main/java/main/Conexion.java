package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import mensajes.Mensajes;

public class Conexion {

	//No creo final para poder crear otros métodos de acceso a otro tipo de base de datos
	private static String jdbc_driver;
	private static String direccion;
	private static String usuario;
	private static String pass;
	
	public static boolean registraDriver() {
		jdbc_driver="com.mysql.cj.jdbc.Driver";
		boolean driverOk=false;
		
		try {
			Class.forName(jdbc_driver).newInstance(); 
			driverOk=true;
		}catch (Exception e){		
			System.err.println(Mensajes.ERROR_JBDC+e.getMessage());
			//e.printStackTrace();
		}finally {
			return driverOk;
		}
	}

	public static Connection conexionMySQL() {
		String basedatos="motor_racing_ver5";
		direccion="jdbc:mysql://localhost:3306/"+basedatos;
		usuario="root";
		pass="mysqlrootcontra";
		try {			 
			return DriverManager.getConnection(direccion, usuario, pass);
		}catch (Exception e){		
			System.out.println("Error en la conexión:" + e.toString() );
			return null;
		}
	}
	
	public static Connection conectarBBDD() {
		Connection conexion=null;
											
		if (registraDriver()) {									// Registra el driver
			conexion=conexionMySQL();
		}
		return conexion;
	}
	
	public static void desconectarBBDD(Connection conexion) {
		try {
//			System.out.println(Mensajes.CERRAR_CONEXION_BBDD);
			conexion.close();
		} catch (SQLException e1) {
			System.out.println(Mensajes.ERROR_CIERRE_BBDD);
			e1.printStackTrace();
		} 
	}
	

}
