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

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.aplicacion.manejador.pedido.ManejadorCrearPedido;
import com.ceiba.adn.tienda.aplicacion.manejador.pedido.ManejadorEliminarPedido;
import com.ceiba.adn.tienda.aplicacion.manejador.pedido.ManejadorListarPedido;

/**
 * @author jeison.barbosa
 *
 */
@RestController
@RequestMapping("/pedido")
public class ControllerPedido {
	private ManejadorCrearPedido manejadorCrearPedido;
	private ManejadorEliminarPedido manejadorEliminarPedido;
	private ManejadorListarPedido manejadorListarPedido;

	/**
	 * @param manejadorCrearPedido
	 */
	public ControllerPedido(ManejadorCrearPedido manejadorCrearPedido,
			ManejadorEliminarPedido manejadorEliminarPedido, ManejadorListarPedido manejadorListarPedido) {
		this.manejadorCrearPedido = manejadorCrearPedido;
		this.manejadorEliminarPedido = manejadorEliminarPedido;
		this.manejadorListarPedido=manejadorListarPedido;
	}

	@PostMapping("/crear")
	public ComandoPedido crear(@RequestBody ComandoPedido comandoPedido) {
		return manejadorCrearPedido.crear(comandoPedido);
	}

	@DeleteMapping("/eliminar/{numeroPedido}")
	public boolean eliminar(@PathVariable int numeroPedido) {
		return manejadorEliminarPedido.eliminar(numeroPedido);
	}
	@GetMapping
	public List<ComandoPedido> listarPedido(){
		return manejadorListarPedido.listar();
	}
}
