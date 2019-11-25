/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.pedidoprenda;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.dominio.servicio.pedidoprenda.ServicioEliminarPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorEliminarPedidoPrenda {
	
	private ServicioEliminarPedidoPrenda servicioEliminarPedidoPrenda;

	/**
	 * @param servicioEliminarPedidoPrenda
	 */
	public ManejadorEliminarPedidoPrenda(ServicioEliminarPedidoPrenda servicioEliminarPedidoPrenda) {
		this.servicioEliminarPedidoPrenda = servicioEliminarPedidoPrenda;
	}
	
	public boolean eliminar(int idPedidoPrenda) {
		return servicioEliminarPedidoPrenda.eliminar(idPedidoPrenda);
	}
	
	
}
