/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

import java.util.Date;

import com.ceiba.adn.tienda.dominio.modelo.Cliente;

/**
 * @author jeison.barbosa
 *
 */
public class ComandoPedido {
	private int idPedido;
	private int numeroOrden;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Cliente clienteId;
	
	
	/**
	 * @param idPedido
	 * @param numeroOrden
	 * @param fechaPedido
	 * @param fechaEntrega
	 * @param clienteId
	 */
	public ComandoPedido(int idPedido, int numeroOrden, Date fechaPedido, Date fechaEntrega, Cliente clienteId) {
		this.idPedido = idPedido;
		this.numeroOrden = numeroOrden;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.clienteId = clienteId;
	}

	/**
	 * 
	 */
	public ComandoPedido() {
	}

	/**
	 * @return the idPedido
	 */
	public int getIdPedido() {
		return idPedido;
	}

	/**
	 * @return the numeroOrden
	 */
	public int getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @return the fechaPedido
	 */
	public Date getFechaPedido() {
		return fechaPedido;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @return the clienteId
	 */
	public Cliente getClienteId() {
		return clienteId;
	}

	/**
	 * @param idPedido the idPedido to set
	 */
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @param fechaPedido the fechaPedido to set
	 */
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	
	
}
