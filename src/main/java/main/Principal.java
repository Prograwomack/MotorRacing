package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.swing.JOptionPane;

import dao.LoginDao;
import dao.TablaDao;
import estructuraBaseDeDatos.Campo;
import estructuraBaseDeDatos.EstructuraBbdd;
import estructuraBaseDeDatos.Tabla;
import mensajes.Mensajes;
import menus.MenuLoginAltaCliente;
import model.Cliente;
import model.Login;
import model.Sesiones;

public class Principal {

	public static void main(String[] args) {

		// Conecta base datos
		Connection conexionAbierta;
		conexionAbierta = Conexion.conectarBBDD();
		if (conexionAbierta != null) {
			// Carga en memoria la estructura de tablas de la base de datos
			TablaDao tablaDao = new TablaDao(conexionAbierta);
			EstructuraBbdd estructuraBD;
			estructuraBD = tablaDao.cargaEstructuraTablas();

			MenuLoginAltaCliente menuInicial = new MenuLoginAltaCliente(estructuraBD, conexionAbierta);
			menuInicial.ejecutarMenuInicial();

			Conexion.desconectarBBDD(conexionAbierta);
		}
	}

	public static String introNumeroTeclado() {
		// Captura el número entero introducido por teclado y controla un posible error
		String lineaCapturada;
		try {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			lineaCapturada = teclado.readLine();
			Integer.parseInt(lineaCapturada);
		} catch (IOException e) {
			System.out.println(Mensajes.ERRORENTRADATECLADO + " " + e.getMessage());
			lineaCapturada = null;
		} catch (NumberFormatException f) {
			System.out.println(Mensajes.ERRORALINTRODUCIRNUMERO);
			lineaCapturada = null;
		}
		return lineaCapturada;
	}

	public static String introTextoTeclado(int longitudMaxima) {
		// Captura un texto introducido por teclado, su tamaño y controla un posible
		// error
		String lineaCapturada;
		try {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			lineaCapturada = teclado.readLine();
			if (lineaCapturada.length() > longitudMaxima) {
				lineaCapturada = lineaCapturada.substring(0, longitudMaxima);
			}
		} catch (IOException e) {
			System.out.println(Mensajes.ERRORENTRADATECLADO + " " + e.getMessage());
			lineaCapturada = null;
		}
		return lineaCapturada;
	}

	public static boolean validarCorreo(String correo) {
		boolean emailValido = false;
		Pattern patron = Pattern
				.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");


		Matcher matcher = patron.matcher(correo);
		if (matcher.find())
			emailValido = true;
		return emailValido;
	}

	public static void limpiaConsola() {
		String saltosLinea = Mensajes.SALTOLINEA.repeat(50);
		System.out.println(saltosLinea);
	}

	public static String nombreDeLaTablaAsociadaALaEntity(Class entidad) {
		Object value = null;
		String nombreTabla = null;
		boolean salir = false;
		try {
			for (Annotation anotacion : entidad.getAnnotations()) {
				Class<? extends Annotation> tipo = anotacion.annotationType();
				if (tipo.getName().equals("javax.persistence.Table")) {
					for (Method method : tipo.getDeclaredMethods()) {
						value = method.invoke(anotacion, (Object[]) null);
						if (method.getName().equals("name")) {
							nombreTabla = (String) value;
							salir = true;
							break;
						}
					}
				}
				if (salir)
					break;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return nombreTabla;
	}

	public static String nombreDelCampoAsociadoALaEntity(Class entidad, String atributo) {
		String nombreColumna = null;

		for (Field field : entidad.getDeclaredFields()) {
			if (field.getName().equals(atributo)) {
				if (field.isAnnotationPresent(Column.class))
					nombreColumna = field.getAnnotation(Column.class).name();
				else if (field.isAnnotationPresent(JoinColumn.class))
					nombreColumna = field.getAnnotation(JoinColumn.class).name();
				else
					nombreColumna = field.getName();
				break;
			}
		}
		return nombreColumna;
	}

	public static String introduceAtributoPorTeclado(String texto, Class entidad, String atributo,
			EstructuraBbdd estructuraBD) {
		String lineaCapturada = "";
		String nombreTablaBD = null;
		String nombreCampoBD = null;
		String avisoError;
		Tabla tabla;
		Campo campo;

		nombreTablaBD = nombreDeLaTablaAsociadaALaEntity(entidad);
		nombreCampoBD = nombreDelCampoAsociadoALaEntity(entidad, atributo);
		if (nombreTablaBD != null && nombreCampoBD != null) {
			tabla = estructuraBD.buscaTabla(nombreTablaBD);
			campo = tabla.buscaCampo(nombreCampoBD);
			if (tabla != null && campo != null) {
				do {
					System.out.println("Por favor, introduzca " + texto + ":");
					avisoError = null;
					try {
						BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
						lineaCapturada = teclado.readLine();
						// Comprueba si el dato introducido se ajusta al tipo de dato del campo de la
						// base de datos
						switch (campo.getTipo()) {
						case "int":
							Integer.parseInt(lineaCapturada);
							break;
						case "float":
							Float.parseFloat(lineaCapturada);
							break;
						case "double":
							Double.parseDouble(lineaCapturada);
							break;
						case "date", "datetime", "timestamp":
							Date.parse(lineaCapturada);
							break;
						case "varchar":
							if (lineaCapturada.length() > campo.getLongitud())
								lineaCapturada = lineaCapturada.substring(0, campo.getLongitud());
							break;
						}
						// Comprueba si el valor es nulo y si puede serlo en la base de datos.
						if (lineaCapturada.length() == 0 && campo.getEsNulo() == false)
							avisoError = Mensajes.DATO_NO_PUEDE_SER_NULO;
					} catch (IOException e) {
						avisoError = Mensajes.ERRORENTRADATECLADO;
						lineaCapturada = null;
					} catch (NumberFormatException f) {
						avisoError = Mensajes.ERRORALINTRODUCIRNUMERO;
						lineaCapturada = null;
					} catch (IllegalArgumentException f) {
						avisoError = Mensajes.FECHA_INCORRECTA;
						lineaCapturada = null;
					}
					if (avisoError != null)
						System.out.println(avisoError);
				} while (avisoError != null);
			}
		}
		return lineaCapturada;
	}
}
