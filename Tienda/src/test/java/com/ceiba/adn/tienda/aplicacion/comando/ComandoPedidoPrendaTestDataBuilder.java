/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;

/**
 * @author jeison.barbosa
 *
 */
public class ComandoPedidoPrendaTestDataBuilder {
	private final static int ID_PEDIDO_PRENDA = 12345;
	private final static double CANTIDAD = 30000;
	private final static double VALOR_TOTAL = 900;

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
	public ComandoPedidoPrendaTestDataBuilder() {
		this.idPedidoPrenda = ID_PEDIDO_PRENDA;
		this.pedidoId = new Pedido();
		this.prendaId = new Prenda();
		this.cantidad = CANTIDAD;
		this.valorTotal = VALOR_TOTAL;
		pedidoId.setIdPedido(1001);
		prendaId.setIdPrenda(1234433);

	}

	public ComandoPedidoPrendaTestDataBuilder conIdPedidoPrenda(int idPedidoPrenda) {
		this.idPedidoPrenda = idPedidoPrenda;
		return this;
	}

	public ComandoPedidoPrendaTestDataBuilder conPedido(Pedido pedidoId) {
		this.pedidoId = pedidoId;
		return this;
	}

	public ComandoPedidoPrendaTestDataBuilder conPrenda(Prenda prendaId) {
		this.prendaId = prendaId;
		return this;
	}

	public ComandoPedidoPrendaTestDataBuilder ConCantidad(double cantidad) {
		this.cantidad = cantidad;
		return this;
	}

	public ComandoPedidoPrendaTestDataBuilder conValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}

	public ComandoPedidoPrenda build() {
		return new ComandoPedidoPrenda(this.idPedidoPrenda, this.pedidoId, this.prendaId, this.cantidad,
				this.valorTotal);
	}
}
