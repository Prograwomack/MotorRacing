package main;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import estructuraBaseDeDatos.Campo;
import estructuraBaseDeDatos.EstructuraBbdd;
import estructuraBaseDeDatos.Tabla;
import mensajes.Mensajes;

public class ListadoEntity {
	final String LINEA_VERTICAL="|";
	final String LINEA_HORIZONTAL_SUPERIOR=String.valueOf((char)168);
	final String LINEA_HORIZONTAL_INFERIOR=String.valueOf((char)95);
	final char CARACTER_A_ELIMINAR_NOMBRE_TABLA='_';
	final int NUMERO_DIGITOS_CAMPO_NUMERICO=20;
	private String titulo;
	private String nombreTabla;
	private boolean mostrarIndices;
	private EstructuraBbdd estructuraTablas;
	private List<?> matrizDatos;
	ArrayList<Campo> campos;
	boolean[] visible;

	public ListadoEntity(String titulo, String nombreTabla, boolean mostrarIndices, EstructuraBbdd estructuraTablas, List<?> matrizDatos) {
		super();
		this.titulo=titulo;
		this.nombreTabla=nombreTabla;
		this.mostrarIndices=mostrarIndices;
		this.estructuraTablas=estructuraTablas;
		this.matrizDatos=matrizDatos;
	}
	
	
	
	public void listarDatosTablas() {
		// Crea y muestra un listado con el título y los datos de la Entity indicado en matrizDatos

		String[] atributosEntity;
		int[] posicionimpresion;
		String[] encabezadoTablaParaComparar;
		String[] valores;
		String[] valoresImprimir;

		Tabla tabla;
		tabla=estructuraTablas.buscaTabla(nombreTabla);
		campos=tabla.getCampos();
		visible=campoVisible();

			// título listado
		System.out.println(tituloListado());
			// Primera línea del listado
		String linea=lineaListado(LINEA_HORIZONTAL_SUPERIOR);
		System.out.println(linea);
			// Línea con los títulos del encabezado 
		System.out.println(encabezadoListado());
		System.out.println(linea);
			// Líneas de datos de la matriz de objetos
		for (Object registro:matrizDatos) {
			atributosEntity=nombreAtributosClase(registro);
			encabezadoTablaParaComparar=minusculas(borraCaracter());
			posicionimpresion=posiciónImpresionAtributos(encabezadoTablaParaComparar,atributosEntity);
			valores=valorAtributosClase(registro);
			valoresImprimir=ordenaValoresClasePorPosicion(posicionimpresion, valores);
			System.out.println(filaDatosListado(valoresImprimir));
		}	
			// última línea listado
		System.out.println(lineaListado(LINEA_HORIZONTAL_INFERIOR));
	}
	
	public boolean[] campoVisible() {
		// Devuelve una matriz booleana en la que se indica si los campos ID de la tabla se imprimirán (true) o no (false) - Se utiliza listarDatosTablas
		boolean[] visible=new boolean[campos.size()];
		int x=0;

		for (Campo campo: campos) {
//			if(!((!mostrarIndices && campo.getEsClave().equals(true)) || (mostrarIndices && campo.getEsClave().equals(true) && x>0)))  visible[x]=true; else visible[x]=false;		// Sólo muestra los índices (id son siempre las primeras columnas) no las foreing keys.
			if((!mostrarIndices && campo.getEsClave().equals(true)) || (mostrarIndices && campo.getEsClave().equals(true) && x>0))  visible[x]=false; else visible[x]=true;		// Sólo muestra los índices (id son siempre las primeras columnas) no las foreing keys.
			x++;
		}
		return visible;
	}
	
	public String tituloListado() {
		// Devuelve una cadena centrada con respecto al ancho del listado - Se utiliza listarDatosTablas
		String linea=" ";
		int ancho=1;		// El listado tendrá al menos una línea vertical inicial, una final y otra entre cada una de los campos.
		int anchoTitulo=titulo.length();
		int x=0;

		for (Campo campo: campos) {
			if(visible[x])  {
				if (campo.getTipo().equals("varchar")) 
					ancho+=campo.getLongitud()+1; 
				else 
					ancho+=NUMERO_DIGITOS_CAMPO_NUMERICO+1;	
			}
			x++;
		}
		if (anchoTitulo>=ancho) {
			linea=titulo.substring(0, ancho-1);
		}else {
			linea=linea.repeat((int)((ancho-anchoTitulo)/2));
			linea+=titulo;
		}
		return linea;
		}
	
	public String lineaListado(String lineaHorizontal) {
		// Crea un String con la línea horizontal que se usa en el listado - Se utiliza listarDatosTablas

		String linea=LINEA_VERTICAL;
		int ancho;
		int x=0;

		for (Campo campo: campos) {
			if(visible[x])  {
				if (campo.getTipo().equals("varchar")) ancho=campo.getLongitud(); else ancho=NUMERO_DIGITOS_CAMPO_NUMERICO;
				linea+=lineaHorizontal.repeat(ancho)+LINEA_VERTICAL;
			}
			x++;
		}	
		return linea;
	}
	
	public String encabezadoListado() {
			// Crea un String con los títulos de los campos de la tabla separada por el caracter vertical - Se utiliza listarDatosTablas
			//encabezadoListado(visible, campos, NUMERO_DIGITOS_CAMPO_NUMERICO, LINEA_VERTICAL)
		
		String linea=LINEA_VERTICAL;
		int ancho;
		int x=0;

		for (Campo campo: campos) {
			if(visible[x])  {
				if (campo.getTipo().equals("varchar")) ancho=campo.getLongitud(); else ancho=NUMERO_DIGITOS_CAMPO_NUMERICO;
				String nombreCampo=campo.getNombre();
				if (nombreCampo.length()>ancho) nombreCampo=nombreCampo.substring(0, ancho-1);
				linea+=String.format("%-"+ancho+"s%s",nombreCampo,LINEA_VERTICAL);
			}
			x++;
		}
		return linea;
	}
	
	public String[] nombreAtributosClase(Object obj) {
		// Devuelve una matriz con el nombre de los atributos del objeto pasado como parámetro - Se utiliza en listarDatosTablas para obtener el nombre de los atributos de la Entity
		// poder compararlos posteriormente con los de la tabla asociada a esa Entity.

		ArrayList<String> nombreAtributos=new ArrayList<>();

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
				nombreAtributos.add((propertyDesc.getName()).toLowerCase()); 
																// modificar el atributo: propertyDesc.setValue(String attributeName,  Object value);
																// Para obtener el valor del atributo:  Object value = propertyDesc.getReadMethod().invoke(obj);  
			}
			String[] listadoAtributos=new String[nombreAtributos.size()];
			listadoAtributos=nombreAtributos.toArray(listadoAtributos);
			return listadoAtributos;	
		}catch(Exception e) {
			System.out.println(Mensajes.ERROR_ACCESO_ATRIBUTOS_CLASE+e.getMessage());
			return new String[0];
		}
	}
	
	public String[] borraCaracter() {
			// Devuelve una cadena eliminando el caracter indicado en caracterEliminar en todos los elementos del la matriz pasada como atributo.
			// Se utiliza listarDatosTablas para eliminar _ de los nombres de los campos de las tablas, pues las Entitys los eliminan.
	
		String[] lista=new String[campos.size()];
		String cadena;
		int y=0;
		
		for (Campo campo: campos) {
			cadena=campo.getNombre();
			String nuevaCadena="";
			for (int x=0; x<cadena.length();x++ ) {
				char caracter=cadena.charAt(x);
				if (caracter!=CARACTER_A_ELIMINAR_NOMBRE_TABLA) nuevaCadena+=caracter;
			}	
			lista[y]=nuevaCadena;
			y++;
		}
		return lista;
	}
	
	public String[] minusculas(String [] cadenas) {
			// Convierte a minúsculas la cadena 

		for (int x=0; x<cadenas.length; x++) {
			cadenas[x]=cadenas[x].toLowerCase();
		}
		return cadenas;
		}

	public int[] posiciónImpresionAtributos(String[] encabezadoTablaParaComparar, String[] nombreAtributosClase) {
			// Devuelve una matriz con la posición de impresión de los atributos de la Entity con respecto a los campos de la tabla asociada a ésta, para que al imprimirlos coincida el orden
			// de la Entity y de la tabla de la base de datos. Empieza en 1. Cero no se imprimirá - Se utiliza listarDatosTablas

		int totalAtributos=nombreAtributosClase.length;
		int totalCampos=encabezadoTablaParaComparar.length;
		int[] ordenImpresionAtributosClase=new int[totalAtributos];
		
		for(int y=0; y<totalAtributos; y++) {
			ordenImpresionAtributosClase[y]=0;
			for (int x=0; x<totalCampos;x++) {
				if(nombreAtributosClase[y].equals(encabezadoTablaParaComparar[x])) { 
					ordenImpresionAtributosClase[y]=x+1; 
					break;
				}
			}
		}	
		return ordenImpresionAtributosClase;
	}
	
	public String[] valorAtributosClase(Object obj) {
			// Obtiene el valor de los atributos del objeto pasado como parámetro (una Entity) y lo formatéa en función de su contenido.
			// Devuelve una matriz con el resultado de los valores - Se utiliza listarDatosTablas
		
		ArrayList<String> valorAtributos=new ArrayList<>();
		String valor;
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {			
				Object value = propertyDesc.getReadMethod().invoke(obj);
														// 	Obtenemos el atributo: System.out.println("Tipo atributo: "+value.getClass().getSimpleName());
				if (value==null) {
					valor=" ";
				}else {
					String tipoAtributo=value.getClass().getSimpleName();
					switch (tipoAtributo) {
						case "String":	
							valor=String.valueOf(value);
							break;
						case "Integer":
							valor=String.valueOf(value);
							break;
						case "Float":
							valor=String.format("%2f", value);
							break;
						case "Double":
							valor=String.format("%2d", value);
							break;
						case "Date":
							valor=String.format("%1$te-%1$tm-%1$tY %1$tH:%1$tM %1$tH", value);
							break;
						default:
							valor=" ";
							break;
					}
				}
				valorAtributos.add(valor);
			}
			String[] listadoValores=new String[valorAtributos.size()];
			listadoValores=valorAtributos.toArray(listadoValores);
			return listadoValores;	
		}catch(Exception e) {
			System.out.println(Mensajes.ERROR_ACCESO_VALORES_CLASE+e.getMessage());
			return new String[0];
		}
	}
	
	public String[] ordenaValoresClasePorPosicion (int[] posicionImpresionAtributos, String[] valorAtributosClase) {
			// Ordena por el orden de impresión los valores de los atributos de la Entity. El 0 no se imprime, por lo que se elimina de la lista - Se utiliza listarDatosTablas

		int totalAtributos=valorAtributosClase.length;
		String cadenaIntermedia="";
		int posicionIntermedia=0;
		int totalAtributosAImprimir=0;

		for(int x=0;x<totalAtributos;x++) {
			for(int y=x+1; y<totalAtributos;y++) {
				if (posicionImpresionAtributos[x]>posicionImpresionAtributos[y]) {
					posicionIntermedia=posicionImpresionAtributos[y];
					cadenaIntermedia=valorAtributosClase[y];
					posicionImpresionAtributos[y]=posicionImpresionAtributos[x];
					valorAtributosClase[y]=valorAtributosClase[x];
					posicionImpresionAtributos[x]=posicionIntermedia;
					valorAtributosClase[x]=cadenaIntermedia;
				}
			}
			if (posicionImpresionAtributos[x]!=0) totalAtributosAImprimir++;
		}	
		String[] nuevaListaImpresion=new String[totalAtributosAImprimir];
		int atributo=0;
		for (int x=0; x<totalAtributos; x++) {
			if (posicionImpresionAtributos[x]!=0) {
				nuevaListaImpresion[atributo]=valorAtributosClase[x];
				atributo++;
			}
		}
		return nuevaListaImpresion;
	}

	public String filaDatosListado(String[] valoresAtributos) {
			// filaDatosListado(valoresImprimir, visible, campos, NUMERO_DIGITOS_CAMPO_NUMERICO, LINEA_VERTICAL)
			// Devuelve un String con los valores de los atributos de la entidad, formateados y separados por una línea para su impresión - Se utiliza listarDatosTablas

		String linea=LINEA_VERTICAL;
		int ancho;
		int x=0;

		for (Campo campo: campos) {
			if(visible[x])  {
				if (campo.getTipo().equals("varchar")) ancho=campo.getLongitud(); else ancho=NUMERO_DIGITOS_CAMPO_NUMERICO;
				String valorAtributo=valoresAtributos[x];
				if (valorAtributo.length()>ancho) valorAtributo=valorAtributo.substring(0, ancho-1);
				linea+=String.format("%-"+ancho+"s%s",valorAtributo,LINEA_VERTICAL);
			}
			x++;
		}
		return linea;
	}
}
