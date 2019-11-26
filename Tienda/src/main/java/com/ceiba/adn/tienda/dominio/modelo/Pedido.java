/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import java.util.Date;

import com.ceiba.adn.tienda.infraestructura.entidades.ClienteEntidad;

/**
 * @author jeison.barbosa
 *
 */
public class Pedido {
	
	public static final String EL_NUMERO_DE_ORDEN_ES_OBLIGATORIO = "El numero de orden es obligatorio";
	private int idPedido;
	private int numeroOrden;
	private Date fechaPedido;
	private Date fechaEntrega;
	private ClienteEntidad clienteId;
	/**
	 * @param idPedido
	 * @param numeroOrden
	 * @param fechaPedido
	 * @param fechaEntrega
	 * @param clienteId
	 */
	public Pedido(int idPedido, int numeroOrden, Date fechaPedido, Date fechaEntrega, ClienteEntidad clienteId) {
		ValidarArgumento.validarObligatorio(numeroOrden, EL_NUMERO_DE_ORDEN_ES_OBLIGATORIO);
		this.idPedido = idPedido;
		this.numeroOrden = numeroOrden;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.clienteId = clienteId;
	}
	/**
	 * @return the idPedido
	 */
	public int getIdPedido() {
		return idPedido;
	}
	/**
	 * @param idPedido the idPedido to set
	 */
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	/**
	 * @return the numeroOrden
	 */
	public int getNumeroOrden() {
		return numeroOrden;
	}
	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	/**
	 * @return the fechaPedido
	 */
	public Date getFechaPedido() {
		return fechaPedido;
	}
	/**
	 * @param fechaPedido the fechaPedido to set
	 */
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	/**
	 * @return the clienteId
	 */
	public ClienteEntidad getClienteId() {
		return clienteId;
	}
	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(ClienteEntidad clienteId) {
		this.clienteId = clienteId;
	}
	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", numeroOrden=" + numeroOrden + ", fechaPedido=" + fechaPedido
				+ ", fechaEntrega=" + fechaEntrega + ", clienteId=" + clienteId + "]";
	}
	
	
	
}
