/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.pedidoprenda.ManejadorCrearPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.pedidoprenda.ManejadorEliminarPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.pedidoprenda.ManejadorListarPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
@RestController
@RequestMapping("/pedidoprenda")
public class ControllerPedidoPrenda {
	private ManejadorCrearPedidoPrenda manejadorCrearPedidoPrenda;
	private ManejadorListarPedidoPrenda manejadorListarPedidoPrenda;
	private ManejadorEliminarPedidoPrenda manejadorEliminarPedidoPrenda;

	/**
	 * @param manejadorCrearPedidoPrenda
	 */
	public ControllerPedidoPrenda(ManejadorCrearPedidoPrenda manejadorCrearPedidoPrenda,
			ManejadorListarPedidoPrenda manejadorListarPedidoPrenda,
			ManejadorEliminarPedidoPrenda manejadorEliminarPedidoPrenda) {
		this.manejadorCrearPedidoPrenda = manejadorCrearPedidoPrenda;
		this.manejadorListarPedidoPrenda = manejadorListarPedidoPrenda;
		this.manejadorEliminarPedidoPrenda=manejadorEliminarPedidoPrenda;
	}

	@PostMapping("/crear")
	public ComandoPedidoPrenda crear(@RequestBody ComandoPedidoPrenda comandoPedidoPrenda) {
		return manejadorCrearPedidoPrenda.crear(comandoPedidoPrenda);
	}

	@GetMapping("/{numeroOrden}")
	public List<ComandoPedidoPrenda> listarPorPedido(@PathVariable int numeroOrden) {
		return manejadorListarPedidoPrenda.listarPorPedido(numeroOrden);

	}

	@DeleteMapping("eliminar/{idPedidoPrenda}")
	public boolean eliminar(@PathVariable int idPedidoPrenda) {
		return manejadorEliminarPedidoPrenda.eliminar(idPedidoPrenda);
	}
}
