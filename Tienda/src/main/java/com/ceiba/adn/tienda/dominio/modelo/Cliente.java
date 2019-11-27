/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jeison.barbosa
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
	private static final String LA_IDENTIFICACION_ES_UN_DATO_OBLIGATORIO = "La identificacion es un dato obligatorio.";
	private static final String EL_NOMBRE_ES_UN_DATO_OBLIGATORIO = "El nombre es un dato obligatorio.";
	private static final String EL_APELLIDO_ES_UN_DATO_OBLIGATORIO = "El apellido es un dato obligatorio.";
	private static final String EL_CELULAR_ES_UN_DATO_OBLIGATORIO = "El celular es un dato obligatorio.";
	private static final String LA_CIUDAD_ES_UN_DATO_OBLIGATORIO = "La ciudad es un dato obligatorio.";
	private static final String LA_DIRECCION_ENTREGA_ES_UN_DATO_OBLIGATORIO = "La direccion es un dato obligatorio.";
	private static final String EL_USUARIO_ES_UN_DATO_OBLIGATORIO = "El usuario es un dato obligatorio.";
	private static final String EL_PASSWORD_ES_UN_DATO_OBLIGATORIO = "La contrasena es un dato obligatorio.";
	private static final String LA_IDENTIFICACION_DEBE_SER_NUMERICA = "La identificacion debe ser un valor numerico.";
	private static final String LA_LONGITUD_IDENTIFICACION_NO_ES_CORRECTA = "La longitud de la identificacion no es permitida.";
	private static final String LA_LONGITUD_CONTRASENA_NO_ES_CORRECTA = "La longitud de la contrasena no es permitida.";
	private static final int LONGITUD = 5;
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
	private String password;
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
	 * @param password
	 */
	
	public Cliente(int idCliente, int identificacion, String nombre, String apellido, Date fechaNacimiento, String correo,
			String celular, String ciudad, String direccionEntrega, String usuario, String password) {
		ValidarArgumento.validarObligatorio(identificacion, LA_IDENTIFICACION_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(nombre, EL_NOMBRE_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(apellido, EL_APELLIDO_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(celular, EL_CELULAR_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(ciudad, LA_CIUDAD_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(direccionEntrega, LA_DIRECCION_ENTREGA_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(usuario, EL_USUARIO_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(password, EL_PASSWORD_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarNumerico(identificacion, LA_IDENTIFICACION_DEBE_SER_NUMERICA);
		ValidarArgumento.validarLongitud(identificacion, LONGITUD, LA_LONGITUD_IDENTIFICACION_NO_ES_CORRECTA);
		ValidarArgumento.validarLongitud(password, LONGITUD, LA_LONGITUD_CONTRASENA_NO_ES_CORRECTA);
		this.idCliente=idCliente;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.celular = celular;
		this.ciudad = ciudad;
		this.direccionEntrega = direccionEntrega;
		this.usuario = usuario;
		this.password = password;
	}
	


	
}
