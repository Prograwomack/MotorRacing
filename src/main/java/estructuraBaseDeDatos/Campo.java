package estructuraBaseDeDatos;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

public class Campo extends Tabla {
	private String nombre;
	private String tipo;
	private int longitud;
	private Boolean esNulo;
	private Boolean esClave;
	private String valorPorDefecto;
	private Boolean esAutoIncrementado;

	public Campo(String nombreTabla, String nombreCampo, String tipo, int longitud, Boolean esNulo, Boolean esClave, String valorPorDefecto, Boolean esAutoIncrementado) {
		super(nombreTabla);
		this.nombre=nombreCampo;
		this.tipo=tipo;
		this.longitud=longitud;
		this.esNulo=esNulo;
		this.esClave=esClave;
		this.valorPorDefecto=valorPorDefecto;
		this.esAutoIncrementado=esAutoIncrementado;
	}
	
	public Campo(String nombreTabla) {
		super(nombreTabla);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	public Boolean getEsNulo() {
		return esNulo;
	}

	public void setEsNulo(Boolean esNulo) {
		this.esNulo = esNulo;
	}

	public Boolean getEsClave() {
		return esClave;
	}

	public void setEsClave(Boolean esClave) {
		this.esClave = esClave;
	}

	public String getValorPorDefecto() {
		return valorPorDefecto;
	}

	public void setValorPorDefecto(String valorPorDefecto) {
		this.valorPorDefecto = valorPorDefecto;
	}

	public Boolean getEsAutoIncrementado() {
		return esAutoIncrementado;
	}

	public void setEsAutoIncrementado(Boolean esAutoIncrementado) {
		this.esAutoIncrementado = esAutoIncrementado;
	}
}
