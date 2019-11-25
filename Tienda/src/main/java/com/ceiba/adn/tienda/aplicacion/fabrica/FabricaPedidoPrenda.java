/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class FabricaPedidoPrenda {

	public PedidoPrenda crearPedidoPrenda(ComandoPedidoPrenda comandoPedidoPrenda) {
		return new PedidoPrenda(comandoPedidoPrenda.getIdPedidoPrenda(), comandoPedidoPrenda.getPedidoId(),
				comandoPedidoPrenda.getPrendaId(), comandoPedidoPrenda.getCantidad(),
				comandoPedidoPrenda.getValorTotal());
	}
}
