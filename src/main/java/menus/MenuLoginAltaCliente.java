package menus;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.ClienteDao;
import dao.LoginDao;
import dao.SesionesDao;
import estructuraBaseDeDatos.EstructuraBbdd;
import main.ListadoEntity;
import main.Principal;
import mensajes.Mensajes;
import model.Cliente;
import model.Login;
import model.Sesiones;

public class MenuLoginAltaCliente extends Menu {

	public MenuLoginAltaCliente(EstructuraBbdd estructuraTablas, Connection conexionAbierta) {
		super(estructuraTablas, conexionAbierta);	
	}
	
	public void ejecutarMenuInicial() {
		
		Login login;
		Sesiones sesion=null;
		int numeroMaximoIntentos=3;
		int numeroIntentos;
		int seleccionMenu;
		
		do {
			numeroIntentos=0;
			System.out.println(Mensajes.SALTOLINEA);
			seleccionMenu=opcionSeleccionadaDelMenu(cadenaMenu("Menú de acceso a MOTOR RACING",new String[] {"Salir aplicación","Acceso clientes", "Alta nuevo usuario"}));
			switch (seleccionMenu) {
				case 0:						// Volver
					if (sesion!=null) sesionFinalizada(sesion);
					break;
				case 1:						// Login
					do{
						login=loguearse();
						if (login!=null) {	
							sesion=sesionIniciadaOK(login);
							Cliente cliente=leeDatosClienteLogueado(login);
							if (cliente!=null) {
								MenuCliente menuCliente=new MenuCliente(cliente, getEstructuraTablas(), getConexionAbierta());
								menuCliente.ejecutaMenuCliente();						
							}
							numeroIntentos=numeroMaximoIntentos+1;
						}else {
							System.out.println(Mensajes.ERROR_ACCESO_LOGIN);
						}	
						numeroIntentos++;
					}while(numeroIntentos<numeroMaximoIntentos);
					break;
				case 2:						// Alta nuevo cliente
					login=altaNuevoCliente();
					if (login!=null) {
						sesion=sesionIniciadaOK(login);
						Cliente cliente=grabaDatosClienteEnBD(login); 
						if (cliente!=null) {
							MenuCliente menuCliente=new MenuCliente(cliente, getEstructuraTablas(), getConexionAbierta());
							menuCliente.ejecutaMenuCliente();						
						}

					}
					break;
				}
		}while (seleccionMenu!=0);	
	}
	
	public Login loguearse() {
		LoginDao loginDao=new LoginDao(getEstructuraTablas(), getConexionAbierta());
		Login login;
		login=null;
		String passIntroducido;
		String emailIntroducido;
		
		System.out.println("Por favor, Introduzca su e-mail:");
		emailIntroducido=Principal.introTextoTeclado(30);
		System.out.println(Mensajes.SALTOLINEA+"Por favor, Introduzca su contraseña:");
		passIntroducido=Principal.introTextoTeclado(20);
		if(passIntroducido!=null) {
			login=loginDao.read(emailIntroducido, "email");	
			if (login==null || !(passIntroducido.equals(login.getPassHash()))) login=null;
		}
		return login;
	}
	
	public Login altaNuevoCliente() {
		LoginDao logDao=new LoginDao(getEstructuraTablas(), getConexionAbierta());
		Login login;
		String passIntroducido;
		String emailIntroducido;
		
		System.out.println("* * ALTA NUEVO CLIENTE * *");
		System.out.println("Por favor, Introduzca su e-mail:");
		emailIntroducido=Principal.introTextoTeclado(30);
		if (Principal.validarCorreo(emailIntroducido)) {
			login=logDao.read(emailIntroducido, "email");	
			if (login!=null) {
				System.out.println(Mensajes.ERROR_USUARIO_YA_EXISTE);
				login=null;
			}else {
				login=new Login();
				login.setEmail(emailIntroducido);
				System.out.println("Por favor, Introduzca su pass_hash:");
				passIntroducido=Principal.introTextoTeclado(20);
				login.setPassHash(passIntroducido);	
				login.setId(logDao.insert(login));
			}
		}else {
			System.out.println(Mensajes.DATO_INTRODUCIDO_ERRONEO);
			login=null;
		}	
		return login;
	}
	
	public Date fechaInicioFinSesion() {	
		return new Date();
	}
	
	public void imprimeBienvenida(Login login, Date inicioSesion) {
		String mensaje=String.format("%sBienvenido %s                   Fecha: %s%s",Mensajes.SALTOLINEA,login.getEmail(),inicioSesion,Mensajes.SALTOLINEA);
		String caracter="_";
		String linea=caracter.repeat(mensaje.length());
		String cabecera=linea+mensaje+linea;

		System.out.println(cabecera);

	}
	
	public Sesiones sesionIniciadaOK(Login login) {
		Sesiones sesion;
		sesion=grabaInicioSesionEnBD(login);
		if (sesion.getId()!=0) imprimeBienvenida(login,sesion.getInicioSesion());
		return sesion;
	}
	
	public Sesiones grabaInicioSesionEnBD(Login login) {
		Sesiones sesion=new Sesiones();
		SesionesDao sesionDao=new SesionesDao(getEstructuraTablas(),getConexionAbierta());
		sesion.setLogin(login);
		sesion.setInicioSesion(fechaInicioFinSesion());
		sesion.setId(sesionDao.insert(sesion));
		return sesion;
	}
	
	public void sesionFinalizada(Sesiones sesion) {
		if (grabaFinSesionEnBD(sesion)!=0) System.out.println(Mensajes.AGRADECIMIENTO);	
	}
	
	public int grabaFinSesionEnBD(Sesiones sesion) {
		SesionesDao sesionDao=new SesionesDao(getEstructuraTablas(),getConexionAbierta());
		sesion.setFinSesion(fechaInicioFinSesion());
		return sesionDao.update(sesion);
	}
	
	public void listadoSesiones() {
		List <Sesiones> resultadoConsulta;
		SesionesDao sesionDao=new SesionesDao(getEstructuraTablas(),getConexionAbierta());
		resultadoConsulta=sesionDao.getAll();
		boolean mostrarIndices=true;
		String tituloListado="* * Listado de sesiones * *";
		ListadoEntity listadoSesiones=new ListadoEntity(tituloListado, Principal.nombreDeLaTablaAsociadaALaEntity(Sesiones.class), mostrarIndices, getEstructuraTablas(), resultadoConsulta);
		listadoSesiones.listarDatosTablas();
	}
	
	public void listadoLogins() {
		List <Login> resultadoConsulta;
		LoginDao loginDao=new LoginDao(getEstructuraTablas(),getConexionAbierta());
		resultadoConsulta=loginDao.getAll();
		boolean mostrarIndices=true;
		String tituloListado="* * Listado de login * *";
		ListadoEntity listadoLogin=new ListadoEntity(tituloListado, Principal.nombreDeLaTablaAsociadaALaEntity(Login.class), mostrarIndices, getEstructuraTablas(), resultadoConsulta);
		listadoLogin.listarDatosTablas();
	}
	
	public Cliente introduceDatosCliente(Login login) {
		Cliente cliente=new Cliente();
		String numero;	
		boolean dniValido;
		int maxNumeroIntendosDni=3;
		int numeroIntentosDni=0;
		numeroIntentosDni++;
			
		System.out.println(Mensajes.SALTOLINEA+"          * PROCESO DE ALTA *"+Mensajes.SALTOLINEA);
		do {
			cliente.setDni(Principal.introduceAtributoPorTeclado("su número de DNI",Cliente.class, "dni", getEstructuraTablas()));
			dniValido=validarDNI(cliente.getDni());
			if (!dniValido || compruebaExisteDniEnBD(cliente.getDni())) {
				System.out.println(Mensajes.DNI_NO_VALIDO);	// Si el formato no es el adecuado o ya existe el dni en la BD
				dniValido=false;
			}
			numeroIntentosDni++;
		}while(!dniValido && numeroIntentosDni<=maxNumeroIntendosDni );
		if (dniValido) {
			cliente.setNombre(Principal.introduceAtributoPorTeclado("su nombre",Cliente.class, "nombre", getEstructuraTablas()));
			cliente.setApellido1(Principal.introduceAtributoPorTeclado("su primer apellido",Cliente.class, "apellido1", getEstructuraTablas()));
			cliente.setApellido2(Principal.introduceAtributoPorTeclado("su segundo apellido",Cliente.class, "apellido2", getEstructuraTablas()));
			numero=Principal.introduceAtributoPorTeclado("su número de teléfono",Cliente.class, "telefono", getEstructuraTablas());
			if (numero==null) cliente.setTelefono(0); else cliente.setTelefono(Integer.parseInt(numero));
			cliente.setTipoVia(Principal.introduceAtributoPorTeclado("el tipo de vía (Ej. Aveniada, calle, ...)",Cliente.class, "tipoVia", getEstructuraTablas()));
			cliente.setNombreVia(Principal.introduceAtributoPorTeclado("el nombre de la vía",Cliente.class, "nombreVia", getEstructuraTablas()));
			numero=Principal.introduceAtributoPorTeclado("el número de la vía",Cliente.class, "numeroVia", getEstructuraTablas());
			if (numero==null) cliente.setNumeroVia(0); else cliente.setNumeroVia(Integer.parseInt(numero));
			numero=Principal.introduceAtributoPorTeclado("el número de piso",Cliente.class, "piso", getEstructuraTablas());
			if (numero==null) cliente.setPiso(0); else cliente.setPiso(Integer.parseInt(numero));
			cliente.setPuerta(Principal.introduceAtributoPorTeclado("la letra",Cliente.class, "puerta", getEstructuraTablas()));
			numero=Principal.introduceAtributoPorTeclado("el código postal",Cliente.class, "codigoPostal", getEstructuraTablas());
			if (numero==null) cliente.setCodigoPostal(0); else cliente.setCodigoPostal(Integer.parseInt(numero));
			cliente.setProvincia(Principal.introduceAtributoPorTeclado("la provincia",Cliente.class, "provincia", getEstructuraTablas()));
			cliente.setLogin(login);
		}else {
			cliente=null;
		}
		return cliente;
	}
	
	public Cliente grabaDatosClienteEnBD(Login login) {
		Cliente cliente;
		ClienteDao clienteDao=new ClienteDao(getEstructuraTablas(), getConexionAbierta());
		cliente=introduceDatosCliente(login);
		if(cliente!=null) cliente.setId(clienteDao.insert(cliente));
		return cliente;
	}
	
	public Cliente leeDatosClienteLogueado(Login login) {
		ClienteDao clienteDao=new ClienteDao(getEstructuraTablas(), getConexionAbierta());
		List<Cliente> clientes=clienteDao.read(Integer.toString(login.getId()), Principal.nombreDelCampoAsociadoALaEntity(Cliente.class, "login"));
		if (clientes.isEmpty()) return  null; else return clientes.get(0); 		// Sólo puede existir un registro de cliente por cada idLogin, por eso devuelve sólo uno.
	}
	
	public Boolean verificaPatronDni(String dni) {
		boolean dniValido=false;
	
		Pattern patron=Pattern.compile("^[0-9]{8}[A-Za-z]$");			// {7,8} Permite 7 u 8 carácteres numéricos {8} sólo 8 y una letra mayúscula o minúscula
		Matcher matcher=patron.matcher(dni);
		if (matcher.find()) dniValido=true;
		return dniValido;
	}	
	  
	 private boolean validarDNI(String dni) {
		 
		 boolean dniValido=false;
		 if(verificaPatronDni(dni)) {
			 String letrasDni="TRWAGMYFPDXBNJZSQVHLCKE"; 
			 String parteEnteraDni = dni.substring(0, 8);  							//  recoge la parte entera del DNI (carácteres del 0 al 7).
			 char letraDni = dni.toUpperCase().charAt(8);
			 int valNumDni = Integer.parseInt(parteEnteraDni) % 23;
			 if (letrasDni.charAt(valNumDni)==letraDni) dniValido=true;
		 }
		 return dniValido;
	 }
	 
	 private boolean compruebaExisteDniEnBD(String dni) {
		 boolean existeDni=false;
		 
		 ClienteDao clienteDao=new ClienteDao(getEstructuraTablas(),getConexionAbierta());
		 List<Cliente> clientes=clienteDao.read(dni, Principal.nombreDelCampoAsociadoALaEntity(Cliente.class, "dni"));
		 if (!clientes.isEmpty()) existeDni=true;
		 return existeDni;		 
	 }
	
	
	

//	MenuPruebaLogin menuPruebaLogin=new MenuPruebaLogin(getEstructuraTablas(),getConexionAbierta());
//	menuPruebaLogin.ejecutarMenuLogin();	
	
	
	//	System.out.println("Se grabó el cliente número "+cliente.getId());
	
	
	
//	Lee los datos del cliente por id
//ClienteDao clienteDao=new ClienteDao(getConexionAbierta());
//Cliente cliente=clienteDao.read(2);
//System.out.println("Nombre cliente :"+cliente.getNombre());

//	Lee los datoss del cliente por otro campo
//ClienteDao clienteDao=new ClienteDao(getConexionAbierta());
//List<Cliente> clientes=clienteDao.read("90", Principal.nombreDelCampoAsociadoALaEntity(Cliente.class, "login"));
//for(Cliente cliente: clientes) {
//	System.out.println("nombre cliente: "+cliente.getNombre()+" "+cliente.getLogin().getId());
//}

//	Lee todos los clientes
//ClienteDao clienteDao=new ClienteDao(getEstructuraTablas(), getConexionAbierta());
//List<Cliente> clientes=clienteDao.getAll();
//Cliente c=null;
//for(Cliente cliente: clientes) {
//	System.out.println("nombre cliente: "+cliente.getNombre()+" DNI: "+cliente.getDni() +cliente.getLogin().getId());
//}

//	Actualiza un registro
//c=clientes.get(1);
//c.setNombre("Modificado");
//System.out.println("DNI antes de modificar: "+c.getDni());
//clienteDao.update(c);
	 
//		Lee los datos de la sesión por por id
//		SesionesDao sesionDao=new SesionesDao(getEstructuraTablas(), getConexionAbierta());
//		Sesiones sesiones=sesionDao.read(182);
//		System.out.println("Inicio sesión :"+sesiones.getInicioSesion()+" fin sesion: "+sesiones.getFinSesion());
		
//		Actualiza un registro de sesiones
//		Sesiones s=sesion;
//		System.out.println("Fecha antes de modificar: "+s.getInicioSesion());
//		s.setInicioSesion(null);
//		SesionesDao sesionDao=new SesionesDao(getEstructuraTablas(), getConexionAbierta());
//		sesionDao.update(s);	 

//		System.out.println("Nombre cliente :"+cliente.getNombre()+" "+cliente.getApellido1()+" "+cliente.getApellido2());
		
//		listadoSesiones();
//		listadoLogins();
//		System.out.println("Está el dni en la BD? "+compruebaExisteDniEnBD(cliente.getDni()));
			 
	 
}
