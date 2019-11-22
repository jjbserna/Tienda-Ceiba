/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.pedido;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.aplicacion.fabrica.FabricaPedido;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioCrearPedido;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorCrearPedido {

		private final ServicioCrearPedido servicioCrearPedido;
		private final FabricaPedido fabricaPedido;
		/**
		 * @param servicioCrearPedido
		 * @param fabricaPedido
		 */
		public ManejadorCrearPedido(ServicioCrearPedido servicioCrearPedido, FabricaPedido fabricaPedido) {
			this.servicioCrearPedido = servicioCrearPedido;
			this.fabricaPedido = fabricaPedido;
		}
		
		
		public ComandoPedido crear(ComandoPedido comandoPedido) {
			Pedido pedido=fabricaPedido.crearPedido(comandoPedido);
			return servicioCrearPedido.crear(pedido);
		}
}
