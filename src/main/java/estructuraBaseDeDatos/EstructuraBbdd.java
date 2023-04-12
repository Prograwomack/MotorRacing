package estructuraBaseDeDatos;

import java.util.ArrayList;

import mensajes.Mensajes;

public class EstructuraBbdd {
	
		// Constantes utilizadas en los métodos imprimeDatosCampo, generaLinea, generaLineaDatos
	private final String CARACTER_VERTICAL="|";
	private final String CARACTER_LINEA_HORIZONTAL_SUPERIOR=String.valueOf((char)168);
	private final String CARACTER_LINEA_HORIZONTAL_INFERIOR=String.valueOf((char)95);		// Otro posible es el  173 Imprimir códigos Ascii =>for(int x=0;x<255;x++) { System.out.println(x+" =>"+(char)x);}
	private final int[] ANCHO_COLUMNAS=new int[] {30, 10, 10, 8, 7, 20, 18};
	private final String[] TITULOS_COLUMNAS=new String[] {"Nombre","Tipo","Longitud","¿Nulo?", "¿Clave?", "Default", "¿Autoincrementado?"};
	
		// Atributos
	private ArrayList<Tabla> tablas;
	

	public EstructuraBbdd() {
		super();
		this.tablas=new ArrayList<>();		
	}
	
	public ArrayList<Tabla> getTablas() {
		return tablas;
	}

	public void setTablas(ArrayList<Tabla> tablas) {
		this.tablas = tablas;
	}
	
	public void addTablas(Tabla tabla) {
		tablas.add(tabla);
	}	
	
	public Tabla buscaTabla(String nombreTabla) {
		Tabla tablaBuscada=null;
		boolean busquedaOk=false;
		if(!(tablas.isEmpty())){
			for(Tabla tabla:tablas) {
				if (tabla.getNombreTabla().equals(nombreTabla)) {
					tablaBuscada=tabla;
					busquedaOk=true;
					break;
				}
			}
			if (busquedaOk==false) System.out.println(Mensajes.ERROR_TABLA_NO_EXISTE);	
		}else {
			System.out.println(Mensajes.ERROR_AL_CARGAR_TABLAS);
		}
		return tablaBuscada;	
	}	
	
	public void listadoEstructuraBbdd(EstructuraBbdd estructuraTablas) {
		ArrayList<Tabla> tablas=new ArrayList<>();
		tablas=estructuraTablas.getTablas();
		for(Tabla tabla:tablas) {
			System.out.println("Tabla: "+tabla.getNombreTabla());
			imprimeCabeceraListado();
			for(Campo elemento:tabla.getCampos()) {
				elemento.imprimeDatosCampo(elemento);
			}
			imprimePieListado();
		}
	}
		
	public void imprimeCabeceraListado() {
		String lineaHorizontalSuperior=generaLinea(ANCHO_COLUMNAS,CARACTER_VERTICAL,CARACTER_LINEA_HORIZONTAL_SUPERIOR);
			
					// Encabezado
		String listado=lineaHorizontalSuperior+Mensajes.SALTOLINEA+generaLineaDatos(ANCHO_COLUMNAS,TITULOS_COLUMNAS, CARACTER_VERTICAL)+Mensajes.SALTOLINEA+lineaHorizontalSuperior;
		System.out.println(listado);    		
	}
		
	public void imprimeDatosCampo(Campo campo) {
			
		String listado=String.format("%s%"+ANCHO_COLUMNAS[0]+"s%s",CARACTER_VERTICAL,campo.getNombre(),CARACTER_VERTICAL)+
				String.format("%"+ANCHO_COLUMNAS[1]+"s%s",campo.getTipo(),CARACTER_VERTICAL)+
				String.format("%"+ANCHO_COLUMNAS[2]+"s%s",String.valueOf(campo.getLongitud()),CARACTER_VERTICAL)+
				String.format("%"+ANCHO_COLUMNAS[3]+"s%s",String.valueOf(campo.getEsNulo()),CARACTER_VERTICAL)+
				String.format("%"+ANCHO_COLUMNAS[4]+"s%s",String.valueOf(campo.getEsClave()),CARACTER_VERTICAL)+
				String.format("%"+ANCHO_COLUMNAS[5]+"s%s",campo.getValorPorDefecto(),CARACTER_VERTICAL)+
				String.format("%"+ANCHO_COLUMNAS[6]+"s%s%s",String.valueOf(campo.getEsAutoIncrementado()),CARACTER_VERTICAL,Mensajes.SALTOLINEA);
			
				System.out.print(listado);    			
				
					//	String holaFormateado=String.format("%10s","hola"); 	System.out.println(holaFormateado+"|"); //		% s – para cadenas //		% f – para flotadores //		% d – para enteros
					//	static String formatoDeseado = "0000000000.000"; 	static DecimalFormat formateador = new DecimalFormat(formatoDeseado); 
					//	Para obtener el número formateado: 	double valor = 123.455; String valorFormateado = formateador.format(valor); System.out.println("valor formateado="+valorFormateado);
					// 										float valor=123455f;
	}
		
	public void imprimePieListado() {
		System.out.print(generaLinea(ANCHO_COLUMNAS,CARACTER_VERTICAL,CARACTER_LINEA_HORIZONTAL_INFERIOR)+Mensajes.SALTOLINEA);   
	}
		
		
	public String generaLinea(int[] ancho, String caracterLineaVertical, String caracterLineaHorizontal) {
		String linea=caracterLineaVertical;
		for(int columna:ancho) {
			linea+=caracterLineaHorizontal.repeat(columna)+caracterLineaVertical;
		}
		return linea;
	}
		
	public String generaLineaDatos(int[] ancho, String[] datos, String lineaVertical) {
		String linea=lineaVertical;
		for(int x=0;x<datos.length;x++) {
			linea+=String.format("%"+String.valueOf(ancho[x])+"s%s",datos[x],lineaVertical);
		}
		return linea;
	}
		
	
}
