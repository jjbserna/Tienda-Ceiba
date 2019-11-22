/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class FabricaPedido {

	public Pedido crearPedido(ComandoPedido comandoPedido) {
		return new Pedido(comandoPedido.getIdPedido(), comandoPedido.getNumeroOrden(), comandoPedido.getFechaPedido(),
				comandoPedido.getFechaEntrega(), comandoPedido.getClienteId());
	}
}
