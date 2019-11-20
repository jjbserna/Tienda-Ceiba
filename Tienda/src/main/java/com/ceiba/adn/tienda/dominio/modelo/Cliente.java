/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import java.util.Date;

/**
 * @author jeison.barbosa
 *
 */
public class Cliente {
	private static final String LA_IDENTIFICACION_ES_UN_DATO_OBLIGATORIO = "La identificacion es un dato obligatorio.";
	private static final String EL_NOMBRE_ES_UN_DATO_OBLIGATORIO = "El nombre es un dato obligatorio.";
	private static final String EL_APELLIDO_ES_UN_DATO_OBLIGATORIO = "El apellido es un dato obligatorio.";
	private static final String EL_CELULAR_ES_UN_DATO_OBLIGATORIO = "El celular es un dato obligatorio.";
	private static final String LA_CIUDAD_ES_UN_DATO_OBLIGATORIO = "La ciudad es un dato obligatorio.";
	private static final String LA_DIRECCION_ENTREGA_ES_UN_DATO_OBLIGATORIO = "La direccion es un dato obligatorio.";
	private static final String EL_USUARIO_ES_UN_DATO_OBLIGATORIO = "El usuario es un dato obligatorio.";
	private static final String EL_PASSWORD_ES_UN_DATO_OBLIGATORIO = "La contrase�a es un dato obligatorio.";
	private String identificacion;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String correo;
	private String celular;
	private String ciudad;
	private String direccionEntrega;
	private String usuario;
	private String pasword;
	/**
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param correo
	 * @param celular
	 * @param ciudad
	 * @param direccionEntrega
	 * @param usuario
	 * @param pasword
	 */
	public Cliente(String identificacion, String nombre, String apellido, Date fechaNacimiento, String correo,
			String celular, String ciudad, String direccionEntrega, String usuario, String pasword) {
		ValidarArgumento.validarObligatorio(identificacion, LA_IDENTIFICACION_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(nombre, EL_NOMBRE_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(apellido, EL_APELLIDO_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(celular, EL_CELULAR_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(ciudad, LA_CIUDAD_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(direccionEntrega, LA_DIRECCION_ENTREGA_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(usuario, EL_USUARIO_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(pasword, EL_PASSWORD_ES_UN_DATO_OBLIGATORIO);
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.celular = celular;
		this.ciudad = ciudad;
		this.direccionEntrega = direccionEntrega;
		this.usuario = usuario;
		this.pasword = pasword;
	}
	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}
	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the direccionEntrega
	 */
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	/**
	 * @param direccionEntrega the direccionEntrega to set
	 */
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the pasword
	 */
	public String getPasword() {
		return pasword;
	}
	/**
	 * @param pasword the pasword to set
	 */
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	
	
}