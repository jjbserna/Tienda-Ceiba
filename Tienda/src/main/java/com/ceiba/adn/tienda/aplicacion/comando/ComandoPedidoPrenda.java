/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

import com.ceiba.adn.tienda.infraestructura.entidades.PedidoEntidad;
import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;

/**
 * @author jeison.barbosa
 *
 */

public class ComandoPedidoPrenda {
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
	public ComandoPedidoPrenda(int idPedidoPrenda, PedidoEntidad pedidoId, PrendaEntidad prendaId, double cantidad,
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
		super();
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
	public PedidoEntidad getPedidoId() {
		return pedidoId;
	}
	/**
	 * @return the prendaId
	 */
	public PrendaEntidad getPrendaId() {
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
	
	

}
