/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.pedidoprenda;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.fabrica.FabricaPedidoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioCrearPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedidoprenda.ServicioCrearPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorCrearPedidoPrenda {

	private final ServicioCrearPedidoPrenda servicioCrearPedidoPrenda;
	private final FabricaPedidoPrenda fabricaPedidoPrenda;
	/**
	 * @param servicioCrearPedidoPrenda
	 * @param fabricaPedidoPrenda
	 */
	public ManejadorCrearPedidoPrenda(ServicioCrearPedidoPrenda servicioCrearPedidoPrenda,
			FabricaPedidoPrenda fabricaPedidoPrenda) {
		this.servicioCrearPedidoPrenda = servicioCrearPedidoPrenda;
		this.fabricaPedidoPrenda = fabricaPedidoPrenda;
	}
	
	public ComandoPedidoPrenda crear(ComandoPedidoPrenda comandoPedidoPrenda) {
		PedidoPrenda pedidoPrenda = fabricaPedidoPrenda.crearPedidoPrenda(comandoPedidoPrenda);
		return servicioCrearPedidoPrenda.crear(pedidoPrenda);
	}
	
}
