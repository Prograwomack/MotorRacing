package dao;

import java.sql.Connection;

import estructuraBaseDeDatos.Campo;
import estructuraBaseDeDatos.EstructuraBbdd;
import estructuraBaseDeDatos.Tabla;

public abstract class DaoPrincipal {
	private EstructuraBbdd estructuraTablas;
	private Connection conexionAbierta;

	public DaoPrincipal(EstructuraBbdd estructuraTablas, Connection conexionAbierta) {
		super();
		this.estructuraTablas=estructuraTablas;
		this.conexionAbierta=conexionAbierta;	
	}

	public EstructuraBbdd getEstructuraTablas() {
		return estructuraTablas;
	}

	public void setEstructuraTablas(EstructuraBbdd estructuraTablas) {
		this.estructuraTablas = estructuraTablas;
	}

	public Connection getConexionAbierta() {
		return conexionAbierta;
	}

	public void setConexionAbierta(Connection conexionAbierta) {
		this.conexionAbierta = conexionAbierta;
	}

	public String ordenSqlInsert(String tabla) {
		//Devuelve: insert into tabla values (?, ?,..., ?)
		Tabla tablaBuscada;
		int numeroCamposEnTabla;
		String ordenSql="insert into "+tabla+" values (";
		int x;
		
		tablaBuscada=estructuraTablas.buscaTabla(tabla);
		numeroCamposEnTabla=tablaBuscada.getCampos().size();
		for(x=0;x<numeroCamposEnTabla; x++) {
			ordenSql+="?";
			if(x<(numeroCamposEnTabla-1)) ordenSql+=", ";
		}
		ordenSql+=")";
		return ordenSql;
	}
	
	public String ordenSqlUpdate(String tabla) {
		//Devuelve: update tabla set campo2=?, campo3=?, ....., campo n=? where id=?
	Tabla tablaBuscada;
	int x=1;
	int numeroCamposEnTabla;
	String campoBusqueda="";
	String ordenSql="update "+tabla+" set ";
	
	tablaBuscada=estructuraTablas.buscaTabla(tabla);
	numeroCamposEnTabla=tablaBuscada.getCampos().size();
	for(Campo campo:tablaBuscada.getCampos()) {
		if (x>1) {									// El primer campo corresponde al campo de búsqueda, el ID
			ordenSql+=campo.getNombre()+"=?";
			if(x<numeroCamposEnTabla) ordenSql+=", ";
		}else {
			campoBusqueda=campo.getNombre();
		}
		x++;
	}
	ordenSql+=" where "+campoBusqueda+"=?";
	return ordenSql;
}
	
	
}
