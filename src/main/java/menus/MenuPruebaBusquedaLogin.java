package menus;

import java.sql.Connection;

import dao.LoginDao;
import estructuraBaseDeDatos.EstructuraBbdd;
import main.Principal;
import model.Login;

public class MenuPruebaBusquedaLogin extends Menu{

	public MenuPruebaBusquedaLogin(EstructuraBbdd estructuraTablas, Connection conexionAbierta) {
		super(estructuraTablas, conexionAbierta);
	}
	
	public void ejecutaMenuBusqueda() {
		Login login=new Login();
		LoginDao loginDao=new LoginDao(getEstructuraTablas(),getConexionAbierta());
		String textoIntroducido;
		int seleccionMenu;
		do {
			seleccionMenu=opcionSeleccionadaDelMenu(cadenaMenu("Menú de LOGIN: BUSQUEDA",new String[] {"Volver","Por ID", "Por e-mail", "Por pass_hash"}));
			switch (seleccionMenu) {
				case 0:						// VOLVER
					break;
				case 1:						// Buscar por id
					System.out.println("Introduzca el número de identificación (id) a buscar:");
					textoIntroducido=Principal.introNumeroTeclado();
					if (textoIntroducido!=null) {
						int numeroId=Integer.parseInt(textoIntroducido);
						login=loginDao.read(numeroId);						
						if (login!=null) System.out.println("El registro: "+login.getId()+" contiene el e_mail: "+login.getEmail()+" con pass_hash "+login.getPassHash());
					}
					
					break;
				case 2: 					//  Buscar por email
					System.out.println("Introduzca el email a buscar:");
					textoIntroducido=Principal.introTextoTeclado(30);
					if (textoIntroducido!=null) {
						login=loginDao.read(textoIntroducido, "email");						
						if (login!=null) 
							System.out.println("| registro: "+login.getId()+" | e_mail: "+login.getEmail()+" | pass_hash "+login.getPassHash());
						else
							System.out.println("El email "+textoIntroducido+" no existe en la base de datos.");
					}
					break;
				case 3:						// Buscar por pass_hash
					System.out.println("Introduzca el pass_hash a buscar:");
					textoIntroducido=Principal.introTextoTeclado(20);
					if (textoIntroducido!=null) {
						login=loginDao.read(textoIntroducido, "pass_hash");						
						if (login!=null) 
							System.out.println("| registro: "+login.getId()+" | e_mail: "+login.getEmail()+" | pass_hash "+login.getPassHash());
						else
							System.out.println("El pass_hash "+textoIntroducido+" no existe en la base de datos.");
					}
					break;	
				}
		}while (seleccionMenu!=0);	
	}
	
	

}
