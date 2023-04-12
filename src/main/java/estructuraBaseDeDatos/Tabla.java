package estructuraBaseDeDatos;

import java.util.ArrayList;

import mensajes.Mensajes;

public class Tabla extends EstructuraBbdd{
	private String nombreTabla;
	private ArrayList<Campo> campos;//	=new ArrayList<Campo>();

	public Tabla(String nombreTabla) {
		super();
		this.nombreTabla=nombreTabla;
		this.campos=new ArrayList<>();
	}
		
	public void addCampo(Campo campo) {
		campos.add(campo);
	}
	public String getNombreTabla() {
		return nombreTabla;
	}
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	public ArrayList<Campo> getCampos() {
		return campos;
	}
	public void setCampos(ArrayList<Campo> campos) {
		this.campos = campos;
	}	
	
	public Campo buscaCampo(String nombreCampo) {
		Campo campoBuscado=null;
		boolean busquedaOk=false;
		if(!(campos.isEmpty())){
			for(Campo campo:campos) {
				if (campo.getNombre().equals(nombreCampo)) {
					campoBuscado=campo;
					busquedaOk=true;
					break;
				}
			}
			if (busquedaOk==false) System.out.println(Mensajes.ERROR_TABLA_NO_EXISTE);	
		}else {
			System.out.println(Mensajes.ERROR_AL_CARGAR_TABLAS);
		}
		return campoBuscado;	
	}	
	
	
	@Override
	// Sobreescribimos el método HashCode de Object para que devuelva el hashcode del
	// atributo nombre.
	public int hashCode() {
		return nombreTabla.hashCode();
	}
	
	@Override
	 //Si el hashcode del este objeto es igual al objeto con el que comparo, entonces que devuelva true
	 
	public boolean equals(Object obj) {
		if (this.hashCode() == obj.hashCode()) {
			return true;
		}else {
			return false;
		}
	
	}

}
