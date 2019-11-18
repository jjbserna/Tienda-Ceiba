/**
 * 
 */
package com.ceiba.adn.tienda.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author jeison.barbosa
 *
 */
@Entity(name="cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCliente;
	
	@Column(name="identificacion" , nullable = false)
	private String identificacion;
	
	@Column(name="nombre", nullable = false)
	private String nombre;
	
	@Column(name="apellido" , nullable = false)
	private String apellido;
	
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="celular")
	private String celular;
	
	@Column(name="ciudad")
	private String ciudad;
	
	@Column(name="direccion_entrega")
	private String direccionEntrega;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="password")
	private String pasword;
	
	
}
