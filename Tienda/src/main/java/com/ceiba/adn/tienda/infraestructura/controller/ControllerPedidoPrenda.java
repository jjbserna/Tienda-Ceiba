/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.pedidoprenda.ManejadorCrearPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
@RestController
@RequestMapping("/pedidoprenda")
public class ControllerPedidoPrenda {
	private ManejadorCrearPedidoPrenda manejadorCrearPedidoPrenda;
	

	/**
	 * @param manejadorCrearPedidoPrenda
	 */
	public ControllerPedidoPrenda(ManejadorCrearPedidoPrenda manejadorCrearPedidoPrenda) {
		this.manejadorCrearPedidoPrenda = manejadorCrearPedidoPrenda;
	}

	@PostMapping("/crear")
	public ComandoPedidoPrenda crear(@RequestBody ComandoPedidoPrenda comandoPedidoPrenda) {
		return manejadorCrearPedidoPrenda.crear(comandoPedidoPrenda);
	}
}
