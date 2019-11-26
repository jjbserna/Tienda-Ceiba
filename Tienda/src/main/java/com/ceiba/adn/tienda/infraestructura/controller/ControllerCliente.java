/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.manejador.cliente.ManejadorActualizarCliente;
import com.ceiba.adn.tienda.aplicacion.manejador.cliente.ManejadorCrearCliente;
import com.ceiba.adn.tienda.aplicacion.manejador.cliente.ManejadorEliminarCliente;
import com.ceiba.adn.tienda.aplicacion.manejador.cliente.ManejadorListarCliente;

/**
 * @author jeison.barbosa
 *
 */
@RestController
@RequestMapping("/cliente")
public class ControllerCliente {

	private final ManejadorCrearCliente manejadorCrearCliente;
	private final ManejadorListarCliente manejadorListarCliente;
	private final ManejadorEliminarCliente manejadorEliminarCliente;
	private final ManejadorActualizarCliente manejadorActualizarCliente;

	/**
	 * @param manejadorCrear
	 */
	public ControllerCliente(ManejadorCrearCliente manejadorCrear, ManejadorListarCliente manejadorListarCliente,
			ManejadorEliminarCliente manejadorEliminarCliente, ManejadorActualizarCliente manejadorActualizarCliente) {
		this.manejadorCrearCliente = manejadorCrear;
		this.manejadorListarCliente = manejadorListarCliente;
		this.manejadorEliminarCliente = manejadorEliminarCliente;
		this.manejadorActualizarCliente=manejadorActualizarCliente;
	}

	@PostMapping("/crear")
	public ComandoCliente crear(@RequestBody ComandoCliente comandoCliente) {
		return manejadorCrearCliente.agregar(comandoCliente);
	}

	@GetMapping
	public List<ComandoCliente> listar() {
		return manejadorListarCliente.listar();
	}

	@DeleteMapping("/eliminar/{identificacion}")
	public boolean eliminar(@PathVariable int identificacion) {
		return manejadorEliminarCliente.eliminar(identificacion);
	}
	
	@PutMapping
	public ComandoCliente actualizar(@RequestBody ComandoCliente comandoCliente) {
		return manejadorActualizarCliente.actualizar(comandoCliente);
	}
}
