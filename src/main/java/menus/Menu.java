package menus;

import java.sql.Connection;

import estructuraBaseDeDatos.EstructuraBbdd;
import main.Principal;
import mensajes.Mensajes;

public abstract class Menu{
	private EstructuraBbdd estructuraTablas;
	private Connection conexionAbierta;

	public Menu(EstructuraBbdd estructuraTablas, Connection conexionAbierta) {
			super();
			this.estructuraTablas=estructuraTablas;
			this.conexionAbierta=conexionAbierta;	
	}
	
	public Menu() {
		super();
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
	
	public int opcionSeleccionadaDelMenu(String opcionesMenu) {
		int numeroOpciones=0;					
		int opcionSeleccionada=0;
		String numeroIntroducido;
	
		numeroOpciones=numeroOpcionesMenu(opcionesMenu);
		if (numeroOpciones>0) {
			do {
				System.out.println(opcionesMenu);
				numeroIntroducido=Principal.introNumeroTeclado();
				if (numeroIntroducido!=null) {
					opcionSeleccionada=Integer.parseInt(numeroIntroducido);
					if(opcionSeleccionada<0 || opcionSeleccionada>numeroOpciones) {
						System.out.println(Mensajes.NUMERO_OPCION_INEXISTENTE);
					}
				}
			}while((opcionSeleccionada<0 || opcionSeleccionada>numeroOpciones) || numeroIntroducido==null);
		}		
		return opcionSeleccionada;
	}
	
	public int numeroOpcionesMenu(String cadenaMenu) {
				// Calcula el número de opciones de la cadena que contiene el menú en función del primer caracter de cada opción.
		int numeroOpciones=0;					
		int numeroCaracteres=cadenaMenu.length();
						
		for(int x=0;x<numeroCaracteres;x++) {	
			if((x+1)<numeroCaracteres) {
				if (cadenaMenu.codePointAt(x)==Mensajes.PRIMER_CARACTER_OPCION_MENU.codePointAt(0)) numeroOpciones++;
			}	
		}
		numeroOpciones--;				// Los números de opción empiezan en cero, por lo que quito uno para
										// que el límite sea el correcto.
		return numeroOpciones;
	}
	
	public String cadenaMenu(String titulo, String[] opciones) {
		// Crea un String con el título y las opciones indicadas.

		String stringMenu="";
		String caracterLinea="*";
		String caracterLateral="*";
		String caracterBlanco=" ";
		int espaciosIzquierdaOpcion=10;
		int numeroOpciones=opciones.length;
		int anchoMenu=50;							// Ancho menu por defecto
		int caracteresTitulo=titulo.length();
		if (caracteresTitulo>=anchoMenu) anchoMenu=caracteresTitulo+2;
		int espaciosIzquierda=((anchoMenu-caracteresTitulo)/2)-2;
		int espaciosDerecha=anchoMenu-caracteresTitulo-espaciosIzquierda-2;
		
		// Cabecera menu
		stringMenu=Mensajes.SALTOLINEA+caracterLinea.repeat(anchoMenu)+Mensajes.SALTOLINEA+
				caracterLateral+caracterBlanco.repeat(espaciosIzquierda)+titulo+caracterBlanco.repeat(espaciosDerecha)+caracterLateral+Mensajes.SALTOLINEA+
				caracterLinea.repeat(anchoMenu)+Mensajes.SALTOLINEA+
				Mensajes.SALTOLINEA+
				Mensajes.SELECCIONAR_OPCION;

					// Opciones Mneu
		if (numeroOpciones>0){
			int x=1;
			while(numeroOpciones>1 && x<numeroOpciones) {
				stringMenu+=caracterBlanco.repeat(espaciosIzquierdaOpcion)+Mensajes.PRIMER_CARACTER_OPCION_MENU+x+"] "+opciones[x]+Mensajes.SALTOLINEA;
				x++;
			}
			stringMenu+=caracterBlanco.repeat(espaciosIzquierdaOpcion)+Mensajes.PRIMER_CARACTER_OPCION_MENU+"0] "+opciones[0]+Mensajes.SALTOLINEA;	
		}
		return stringMenu;
	}

	
	
}
