/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.pedido;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioListarPedido;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorListarPedido {

	private final ServicioListarPedido servicioListarPedido;

	/**
	 * @param servicioListarPedido
	 */
	public ManejadorListarPedido(ServicioListarPedido servicioListarPedido) {
		this.servicioListarPedido = servicioListarPedido;
	}
	
	public List<ComandoPedido> listar(){
		return servicioListarPedido.listar();
	}
	
	
}
