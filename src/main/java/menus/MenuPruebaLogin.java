package menus;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.LoginDao;
import estructuraBaseDeDatos.EstructuraBbdd;
import main.ListadoEntity;
import main.Principal;
import mensajes.Mensajes;
import model.Login;

public class MenuPruebaLogin extends Menu{

	public MenuPruebaLogin(EstructuraBbdd estructuraTablas, Connection conexionAbierta) {
		super(estructuraTablas, conexionAbierta);
	}
	
	public void ejecutarMenuLogin() {
		LoginDao loginDao=new LoginDao(getEstructuraTablas(),getConexionAbierta());
		Login login=new Login();
		int numeroRegistrosAfectados;
		String textoIntroducido;
		
		int seleccionMenu;
		do {
			seleccionMenu=opcionSeleccionadaDelMenu(cadenaMenu("Menú de LOGIN",new String[] {"Volver","Introducir", "Buscar", "Editar", "Borrar", "Listar", "Listar tablas"}));
			switch (seleccionMenu) {
				case 0:						// Volver
					break;
				case 1:						// Introducir login					
					System.out.println("Por favor, Introduzca el e-mail:");
					textoIntroducido=Principal.introTextoTeclado(30);
					if (Principal.validarCorreo(textoIntroducido)) {
						login.setEmail(textoIntroducido);
						System.out.println("Por favor, Introduzca el pass_hash:");
						textoIntroducido=Principal.introTextoTeclado(20);
						login.setPassHash(textoIntroducido);	
						System.out.println("Se ha grabado el id: "+loginDao.insert(login));	// Devuelve la ID autogenerada tras insertar el objeto
					}else {
						System.out.println(Mensajes.DATO_INTRODUCIDO_ERRONEO);
					}
					break;
				case 2:						// Buscar login
					MenuPruebaBusquedaLogin menuPruebaBusquedaLogin=new MenuPruebaBusquedaLogin(getEstructuraTablas(), getConexionAbierta());
					menuPruebaBusquedaLogin.ejecutaMenuBusqueda();		 		
					break;
				case 3: 					//  Editar login
					
					login.setId(2);
					login.setEmail("email@modificado");
					login.setPassHash("clave_modificada");
					numeroRegistrosAfectados=loginDao.update(login);
					if (numeroRegistrosAfectados>0) {
						login=loginDao.read(2); 
						System.out.println("Número de registros modificados: "+numeroRegistrosAfectados+" =>Id: "+login.getId()+" E-mail: "+login.getEmail()+" PassWord: "+login.getPassHash());
					}
					break;
				case 4:						// Borrar login
					System.out.println("Introduzca el número de identificación (id) a eliminar:");
					textoIntroducido=Principal.introNumeroTeclado();
					if (textoIntroducido!=null) {
						int numeroId=Integer.parseInt(textoIntroducido);
						login.setId(numeroId);
						numeroRegistrosAfectados=loginDao.delete(login);
						if (numeroRegistrosAfectados>0) System.out.println("Registro eliminado."); 
						else System.out.println("El Registro no existe.");					
					}
					break;	
				case 5:						// Listar todas los campos de login	
					List <Login> resultadoConsulta=new ArrayList<>();
					resultadoConsulta=loginDao.getAll();
					boolean mostrarIndices=true;
					String tituloListado="* * Listado de usuarios y claves * *";
					ListadoEntity listadoLogin=new ListadoEntity(tituloListado, Principal.nombreDeLaTablaAsociadaALaEntity(Login.class), mostrarIndices, getEstructuraTablas(), resultadoConsulta);
					listadoLogin.listarDatosTablas();
					break;
				case 6:					// Listar la descripción de todas las tablas de la BBDD
					getEstructuraTablas().listadoEstructuraBbdd(getEstructuraTablas());
				}
		}while (seleccionMenu!=0);	
	}

}
