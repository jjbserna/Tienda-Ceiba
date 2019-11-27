package com.ceiba.adn.tienda.aplicacion.comando;

import java.util.Date;

public class ComandoClienteTestDataBuilder {

	private static final int ID_CLIENTE = 1;
	private static final int IDENTIFICACION = 123456;
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
	public ComandoClienteTestDataBuilder() {
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

	public ComandoClienteTestDataBuilder conIdCliente(int idCliente) {
		this.idCliente = idCliente;
		return this;
	}

	public ComandoClienteTestDataBuilder conIdentificacion(int identificacion) {
		this.identificacion = identificacion;
		return this;
	}

	public ComandoClienteTestDataBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public ComandoClienteTestDataBuilder conApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public ComandoClienteTestDataBuilder conFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		return this;
	}

	public ComandoClienteTestDataBuilder conCorreo(String correo) {
		this.correo = correo;
		return this;
	}

	public ComandoClienteTestDataBuilder conCelular(String celular) {
		this.celular = celular;
		return this;
	}

	public ComandoClienteTestDataBuilder conCiudad(String ciudad) {
		this.ciudad = ciudad;
		return this;
	}

	public ComandoClienteTestDataBuilder conDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
		return this;
	}

	public ComandoClienteTestDataBuilder conUsuario(String usuario) {
		this.usuario = usuario;
		return this;
	}

	public ComandoClienteTestDataBuilder conContrasenia(String password) {
		this.contrasenia = password;
		return this;
	}

	public ComandoCliente build() {
		return new ComandoCliente(this.idCliente, this.identificacion, this.nombre, this.apellido, this.fechaNacimiento,
				this.correo, this.celular, this.ciudad, this.direccionEntrega, this.usuario, this.contrasenia);
	}

}
