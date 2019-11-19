/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author jeison.barbosa
 *
 */
@Entity(name="medio_pago")
public class MedioPago implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMedioPago;
	
	@Column(name="banco")
	private String banco;

	@Column(name="numero_cuenta")
	private int numeroCuenta;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Prenda clienteId;
}
