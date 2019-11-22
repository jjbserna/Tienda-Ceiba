/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.aplicacion.manejador.pedido.ManejadorCrearPedido;


/**
 * @author jeison.barbosa
 *
 */
@RestController
@RequestMapping("/pedido")
public class ControllerPedido {
	private ManejadorCrearPedido manejadorCrearPedido;
	
	

	/**
	 * @param manejadorCrearPedido
	 */
	public ControllerPedido(ManejadorCrearPedido manejadorCrearPedido) {
		this.manejadorCrearPedido = manejadorCrearPedido;
	}



	@PostMapping
	public ComandoPedido crear(@RequestBody ComandoPedido comandoPedido) {
		return manejadorCrearPedido.crear(comandoPedido);
	}
}
