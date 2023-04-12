package menus;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import estructuraBaseDeDatos.EstructuraBbdd;
import main.ListadoEntity;
import main.Principal;
import mensajes.Mensajes;
import model.Cliente;

public class MenuCliente extends Menu {
	Cliente cliente;

	public MenuCliente(Cliente cliente, EstructuraBbdd estructuraTablas, Connection conexionAbierta) {
		super(estructuraTablas, conexionAbierta);	
		this.cliente=cliente;		
	}
	
	public void ejecutaMenuCliente() {
		int seleccionMenu;
		
		do {
			System.out.println(Mensajes.SALTOLINEA);
			seleccionMenu=opcionSeleccionadaDelMenu(cadenaMenu("Menú de CLIENTE",new String[] {"Volver","Ver mis datos", "Solicitar cita"}));
			switch (seleccionMenu) {
				case 0:						// Volver
					break;
				case 1:						// "Ver mis datos"
					List <Cliente> resultadoConsulta=new ArrayList<>();
					resultadoConsulta.add(cliente);
					boolean mostrarIndices=false;
					String tituloListado="* * Datos grabados en el sistema * *";
					ListadoEntity listadoSesiones=new ListadoEntity(tituloListado, Principal.nombreDeLaTablaAsociadaALaEntity(Cliente.class), mostrarIndices, getEstructuraTablas(), resultadoConsulta);
					listadoSesiones.listarDatosTablas();
					
					
					break;
				case 2:						// 
					
					break;
				}
		}while (seleccionMenu!=0);	
	}

}
