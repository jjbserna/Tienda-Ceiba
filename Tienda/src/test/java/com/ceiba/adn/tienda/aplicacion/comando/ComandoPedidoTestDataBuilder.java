/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

import java.util.Date;

import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.testdatabuilder.ClienteTestDataBuilder;


/**
 * @author jeison.barbosa
 *
 */
public class ComandoPedidoTestDataBuilder {
	
	private static final int ID_PEDIDO = 1;
	private static final int NUMERO_ORDEN = 1001;

	private int idPedido;
	private int numeroOrden;
	private Cliente cliente;

	/**
	 * @param idPedido
	 * @param numeroOrden
	 * @param fechaPedido
	 * @param fechaEntrega
	 * @param cliente
	 */
	public ComandoPedidoTestDataBuilder() {
		this.idPedido = ID_PEDIDO;
		this.numeroOrden = NUMERO_ORDEN;
		this.cliente = new Cliente();
		cliente.setIdCliente(123456);
	}

	public ComandoPedidoTestDataBuilder conIdPedido(int idPedido) {
		this.idPedido = idPedido;
		return this;
	}

	public ComandoPedidoTestDataBuilder conNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
		return this;
	}


	public ComandoPedidoTestDataBuilder conCliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}

	public ComandoPedido build() {
		return new ComandoPedido(this.idPedido, this.numeroOrden, null, null, this.cliente);
	}

}
