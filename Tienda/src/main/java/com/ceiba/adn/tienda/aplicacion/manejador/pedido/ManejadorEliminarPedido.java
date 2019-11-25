/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.pedido;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioEliminarPedido;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorEliminarPedido {

	private final ServicioEliminarPedido servicioEliminarPedido;

	/**
	 * @param servicioEliminarPedido
	 */
	public ManejadorEliminarPedido(ServicioEliminarPedido servicioEliminarPedido) {
		this.servicioEliminarPedido = servicioEliminarPedido;
	}
	
	public boolean eliminar(int numeroPedido) {
		return servicioEliminarPedido.eliminar(numeroPedido);
	}
}
