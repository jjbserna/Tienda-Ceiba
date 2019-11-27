/**
 * 
 */
package com.ceiba.adn.tienda.dominio.testdatabuilder;

import java.util.Date;

import com.ceiba.adn.tienda.dominio.modelo.Cliente;

/**
 * @author jeison.barbosa
 *
 */
public class ClienteTestDataBuilder {
	private static final int ID_CLIENTE = 1;
	private static final int IDENTIFICACION = 1234567;
	private static final String NOMBRE = "Jeison";
	private static final String APELLIDO = "Barbosa";
	private static final Date FECHA_NACIMIENTO = new Date();
	private static final String CORREO = "jjbarser@gmail.com";
	private static final String CELULAR = "3117640511";
	private static final String CIUDAD = "Armenia";
	private static final String DIRECCION_ENTREGA = "B/7 de Agosto";
	private static final String USUARIO = "jjbarser";
	private static final String CONTRASENIA = "xxxxxx";

	private int idCliente;
	private int identificacion;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String correo;
	private String celular;
	private String ciudad;
	private String direccionEntrega;
	private String usuario;
	private String contrasenia;

	/**
	 * @param idCliente
	 * @param identificacion
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param correo
	 * @param celular
	 * @param ciudad
	 * @param direccionEntrega
	 * @param usuario
	 * @param password
	 */
	public ClienteTestDataBuilder() {
		this.idCliente = ID_CLIENTE;
		this.identificacion = IDENTIFICACION;
		this.nombre = NOMBRE;
		this.apellido = APELLIDO;
		this.fechaNacimiento = FECHA_NACIMIENTO;
		this.correo = CORREO;
		this.celular = CELULAR;
		this.ciudad = CIUDAD;
		this.direccionEntrega = DIRECCION_ENTREGA;
		this.usuario = USUARIO;
		this.contrasenia = CONTRASENIA;
	}

	public ClienteTestDataBuilder conIdCliente(int idCliente) {
		this.idCliente = idCliente;
		return this;
	}

	public ClienteTestDataBuilder conIdentificacion(int identificacion) {
		this.identificacion = identificacion;
		return this;
	}

	public ClienteTestDataBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public ClienteTestDataBuilder conApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public ClienteTestDataBuilder conFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		return this;
	}

	public ClienteTestDataBuilder conCorreo(String correo) {
		this.correo = correo;
		return this;
	}

	public ClienteTestDataBuilder conCelular(String celular) {
		this.celular = celular;
		return this;
	}

	public ClienteTestDataBuilder conCiudad(String ciudad) {
		this.ciudad = ciudad;
		return this;
	}

	public ClienteTestDataBuilder conDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
		return this;
	}

	public ClienteTestDataBuilder conUsuario(String usuario) {
		this.usuario = usuario;
		return this;
	}

	public ClienteTestDataBuilder conContrasenia(String password) {
		this.contrasenia = password;
		return this;
	}

	public Cliente build() {
		return new Cliente(this.idCliente, this.identificacion, this.nombre, this.apellido, this.fechaNacimiento,
				this.correo, this.celular, this.ciudad, this.direccionEntrega, this.usuario, this.contrasenia);
	}

}
