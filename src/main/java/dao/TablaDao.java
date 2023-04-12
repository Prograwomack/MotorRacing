package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraBaseDeDatos.Campo;
import estructuraBaseDeDatos.EstructuraBbdd;
import estructuraBaseDeDatos.Tabla;
import main.Conexion;
import mensajes.Mensajes;

public class TablaDao {
	Connection conexion;
	
	public TablaDao	(Connection conexion) {						
		this.conexion=conexion;
	}

	public Tabla descripcionTabla(String nombreTabla) { //String nombreTabla, Connection conexion) {
		Tabla tabla=new Tabla(nombreTabla);
		String nombre;
		String tipo;
		int longitud;
		boolean nulo;
		boolean clave;
		String valorPorDefecto;
		boolean autoIncrementado;		
		String ordenSql="describe "+nombreTabla;
		
		try(Statement stm=conexion.createStatement()){	
			ResultSet rs=stm.executeQuery(ordenSql);
			while (rs.next()) {
				nombre=rs.getString(1);
				tipo=rs.getString(2);	
				if (tipo.contains("(")) {
					longitud=Integer.parseInt(tipo.substring(tipo.indexOf("(")+1, tipo.indexOf(")")));
					tipo=tipo.substring(0, tipo.indexOf("("));
				}else {
					longitud=0;
				}
				if(rs.getNString(3).equals("NO")) nulo=false; else nulo=true;		// Si admite nulo entonces=true;
				if(rs.getNString(4).equals("")) clave=false; else clave=true;
				valorPorDefecto= rs.getNString(5);
				if (rs.getNString(6).equals("auto_increment")) autoIncrementado=true; else autoIncrementado=false; 
				Campo campo=new Campo(nombreTabla,nombre,tipo,longitud,nulo,clave,valorPorDefecto,autoIncrementado);
				tabla.addCampo(campo);
			}
			stm.close();
			rs.close();
		}catch (Exception e){		
			System.out.println(Mensajes.ERRORDELECTURA + e.toString() );
			Conexion.desconectarBBDD(conexion);
			tabla=null;
		}finally {
			return tabla;
		}
	}
	
	public ArrayList<String> nombreTablasEnBbdd() { 
		ArrayList<String> nombreTablas=new ArrayList<>();
		String ordenSql="show tables";
		
		try(Statement stm=conexion.createStatement()){	
			ResultSet rs=stm.executeQuery(ordenSql);
			while (rs.next()) {
				nombreTablas.add(rs.getString(1));
			}
			stm.close();
			rs.close();
		}catch (Exception e){		
			System.out.println(Mensajes.ERRORDELECTURA + e.toString() );
			Conexion.desconectarBBDD(conexion);
			nombreTablas=null;
		}finally {
			return nombreTablas;
		}
	}
	
	public EstructuraBbdd cargaEstructuraTablas() {
		ArrayList<String> nombreTablas=new ArrayList<>();
		EstructuraBbdd estructuraBbdd =new EstructuraBbdd();
		
		nombreTablas=nombreTablasEnBbdd();
		if (nombreTablas!=null) {
			for(String nombreTabla: nombreTablas) {
				estructuraBbdd.addTablas(descripcionTabla(nombreTabla));
			}
		}
		return estructuraBbdd;		
	}
	
}
