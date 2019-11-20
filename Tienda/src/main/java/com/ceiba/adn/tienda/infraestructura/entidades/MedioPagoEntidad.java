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
public class MedioPagoEntidad implements Serializable{

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
	private PrendaEntidad clienteId;

	/**
	 * @return the idMedioPago
	 */
	public int getIdMedioPago() {
		return idMedioPago;
	}

	/**
	 * @param idMedioPago the idMedioPago to set
	 */
	public void setIdMedioPago(int idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return the numeroCuenta
	 */
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the clienteId
	 */
	public PrendaEntidad getClienteId() {
		return clienteId;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(PrendaEntidad clienteId) {
		this.clienteId = clienteId;
	}
	
	
}
