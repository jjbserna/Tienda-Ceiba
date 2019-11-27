/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import java.util.Date;

import com.ceiba.adn.tienda.infraestructura.entidades.PedidoEntidad;
import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;

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
public class PedidoPrenda {
	
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
	public PedidoPrenda(int idPedidoPrenda, Pedido pedidoId, Prenda prendaId, double cantidad,
			double valorTotal) {
		this.idPedidoPrenda = idPedidoPrenda;
		this.pedidoId = pedidoId;
		this.prendaId = prendaId;
		this.cantidad = cantidad;
		this.valorTotal = valorTotal;
	}
	
	
	

}
