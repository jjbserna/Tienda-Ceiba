/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.pedidoprenda;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.dominio.servicio.pedidoprenda.ServicioListarPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorListarPedidoPrenda {
	
	private final ServicioListarPedidoPrenda servicioListarPedidoPrenda;

	/**
	 * @param servicioListarPedidoPrenda
	 */
	public ManejadorListarPedidoPrenda(ServicioListarPedidoPrenda servicioListarPedidoPrenda) {
		this.servicioListarPedidoPrenda = servicioListarPedidoPrenda;
	}
	
	public List<ComandoPedidoPrenda> listarPorPedido(int numeroOrden){
		return servicioListarPedidoPrenda.listarPorPedido(numeroOrden);
	}

}
