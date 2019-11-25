/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import com.ceiba.adn.tienda.infraestructura.entidades.PedidoEntidad;
import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;

/**
 * @author jeison.barbosa
 *
 */
public class PedidoPrenda {
	
	private int idPedidoPrenda;
	private PedidoEntidad pedidoId;
	private PrendaEntidad prendaId;
	private double cantidad;
	private double valorTotal;
	/**
	 * @param idPedidoPrenda
	 * @param pedidoId
	 * @param prendaId
	 * @param cantidad
	 * @param valorTotal
	 */
	public PedidoPrenda(int idPedidoPrenda, PedidoEntidad pedidoId, PrendaEntidad prendaId, double cantidad,
			double valorTotal) {
		this.idPedidoPrenda = idPedidoPrenda;
		this.pedidoId = pedidoId;
		this.prendaId = prendaId;
		this.cantidad = cantidad;
		this.valorTotal = valorTotal;
	}
	/**
	 * @return the idPedidoPrenda
	 */
	public int getIdPedidoPrenda() {
		return idPedidoPrenda;
	}
	/**
	 * @param idPedidoPrenda the idPedidoPrenda to set
	 */
	public void setIdPedidoPrenda(int idPedidoPrenda) {
		this.idPedidoPrenda = idPedidoPrenda;
	}
	/**
	 * @return the pedidoId
	 */
	public PedidoEntidad getPedidoId() {
		return pedidoId;
	}
	/**
	 * @param pedidoId the pedidoId to set
	 */
	public void setPedidoId(PedidoEntidad pedidoId) {
		this.pedidoId = pedidoId;
	}
	/**
	 * @return the prendaId
	 */
	public PrendaEntidad getPrendaId() {
		return prendaId;
	}
	/**
	 * @param prendaId the prendaId to set
	 */
	public void setPrendaId(PrendaEntidad prendaId) {
		this.prendaId = prendaId;
	}
	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

}
