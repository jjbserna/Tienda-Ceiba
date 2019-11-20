/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.manejador.cliente.ManejadorCrearCliente;



/**
 * @author jeison.barbosa
 *
 */
@RestController
@RequestMapping("/tienda")
public class ControllerCliente {

	private final ManejadorCrearCliente manejadorCrearCliente;

	/**
	 * @param manejadorCrear
	 */
	public ControllerCliente(ManejadorCrearCliente manejadorCrear) {
		this.manejadorCrearCliente = manejadorCrear;
	}

	@PostMapping("/cliente")
	public ComandoCliente crear(@RequestBody ComandoCliente comandoCliente) {
		return manejadorCrearCliente.agregar(comandoCliente);
	}
}
