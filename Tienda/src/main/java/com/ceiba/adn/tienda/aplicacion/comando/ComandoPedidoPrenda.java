/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.infraestructura.entidades.PedidoEntidad;
import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;

/**
 * @author jeison.barbosa
 *
 */

public class ComandoPedidoPrenda {
	private int idPedidoPrenda;
	private Pedido pedidoId;
	private Prenda prendaId;
	private double cantidad;
	private double valorTotal;
	
	
	/**
	 * @param idPedidoPrenda
	 * @param pedidoId
	 * @param prendaId
	 * @param cantidad
	 * @param valorTotal
	 */
	public ComandoPedidoPrenda(int idPedidoPrenda, Pedido pedidoId, Prenda prendaId, double cantidad,
			double valorTotal) {
		this.idPedidoPrenda = idPedidoPrenda;
		this.pedidoId = pedidoId;
		this.prendaId = prendaId;
		this.cantidad = cantidad;
		this.valorTotal = valorTotal;
	}
	
	
	/**
	 * 
	 */
	public ComandoPedidoPrenda() {
	}


	/**
	 * @return the idPedidoPrenda
	 */
	public int getIdPedidoPrenda() {
		return idPedidoPrenda;
	}
	/**
	 * @return the pedidoId
	 */
	public Pedido getPedidoId() {
		return pedidoId;
	}
	/**
	 * @return the prendaId
	 */
	public Prenda getPrendaId() {
		return prendaId;
	}
	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}
	/**
	 * @return the valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}


	/**
	 * @param idPedidoPrenda the idPedidoPrenda to set
	 */
	public void setIdPedidoPrenda(int idPedidoPrenda) {
		this.idPedidoPrenda = idPedidoPrenda;
	}


	/**
	 * @param pedidoId the pedidoId to set
	 */
	public void setPedidoId(Pedido pedidoId) {
		this.pedidoId = pedidoId;
	}


	/**
	 * @param prendaId the prendaId to set
	 */
	public void setPrendaId(Prenda prendaId) {
		this.prendaId = prendaId;
	}


	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

}
