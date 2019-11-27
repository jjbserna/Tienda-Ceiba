/**
 * 
 */
package com.ceiba.adn.tienda.dominio.testdatabuilder;

import java.util.Date;

import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.infraestructura.entidades.ClienteEntidad;

/**
 * @author jeison.barbosa
 *
 */
public class PedidoTestDataBuilder {

	private static final int ID_PEDIDO = 1234;
	private static final int NUMERO_ORDEN = 1001;
	private static final Date FECHA_PEDIDO = new Date();
	private static final Date FECHA_ENTREGA = new Date();

	private int idPedido;
	private int numeroOrden;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Cliente cliente;

	/**
	 * @param idPedido
	 * @param numeroOrden
	 * @param fechaPedido
	 * @param fechaEntrega
	 * @param cliente
	 */
	public PedidoTestDataBuilder() {
		this.idPedido = ID_PEDIDO;
		this.numeroOrden = NUMERO_ORDEN;
		this.fechaPedido = FECHA_PEDIDO;
		this.fechaEntrega = FECHA_ENTREGA;
		this.cliente = new ClienteTestDataBuilder().build();
	}

	public PedidoTestDataBuilder conIdPedido(int idPedido) {
		this.idPedido = idPedido;
		return this;
	}

	public PedidoTestDataBuilder conNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
		return this;
	}

	public PedidoTestDataBuilder conFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
		return this;
	}

	public PedidoTestDataBuilder conFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
		return this;
	}

	public PedidoTestDataBuilder conCliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}

	public Pedido build() {
		return new Pedido(this.idPedido, this.numeroOrden, this.fechaPedido, this.fechaEntrega, this.cliente);
	}
}
