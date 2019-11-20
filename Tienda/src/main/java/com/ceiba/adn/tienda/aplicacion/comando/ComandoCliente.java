/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

import java.util.Date;

/**
 * @author jeison.barbosa
 *
 */
public class ComandoCliente {
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
	public ComandoCliente(String identificacion, String nombre, String apellido, Date fechaNacimiento, String correo,
			String celular, String ciudad, String direccionEntrega, String usuario, String pasword) {
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
	 * 
	 */
	public ComandoCliente() {
		super();
	}
	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @return the direccionEntrega
	 */
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @return the pasword
	 */
	public String getPasword() {
		return pasword;
	}
	
	
}
