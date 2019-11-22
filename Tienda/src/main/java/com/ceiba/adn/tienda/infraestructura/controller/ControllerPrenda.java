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

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.prenda.ManejadorActualizarPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.prenda.ManejadorCrearPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.prenda.ManejadorEliminarPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.prenda.ManejadorListarPrenda;

/**
 * @author jeison.barbosa
 *
 */
/**
 * @author Jeison Julian Barbosa Serna<br>
 *         Email: jjbarser@gmail.com<br>
 * 
 * @date 18/11/2019
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/prenda")
public class ControllerPrenda {

	private final ManejadorCrearPrenda manejadorCrear;
	private final ManejadorListarPrenda manejadorListar;
	private final ManejadorEliminarPrenda manejadorEliminar;
	private final ManejadorActualizarPrenda manejadorActualizarPrenda;

	/**
	 * @param manejadorCrear
	 * @param manejadorListar
	 * @param manejadorEliminar
	 */
	public ControllerPrenda(ManejadorCrearPrenda manejadorCrear, ManejadorListarPrenda manejadorListar,
			ManejadorEliminarPrenda manejadorEliminar, ManejadorActualizarPrenda manejadorActualizarPrenda) {
		this.manejadorCrear = manejadorCrear;
		this.manejadorListar = manejadorListar;
		this.manejadorEliminar = manejadorEliminar;
		this.manejadorActualizarPrenda=manejadorActualizarPrenda;
	}

	/**
	 * @param manejadorCategoria
	 */
	@PostMapping("/crear")
	public ComandoPrenda create(@RequestBody ComandoPrenda comandoPrenda) {
		return manejadorCrear.crear(comandoPrenda);
	}

	@GetMapping
	public List<ComandoPrenda> listaGeneral() {
		return manejadorListar.listar();
	}

	@DeleteMapping("/eliminar/{codigoPrenda}")
	public boolean eliminarPrenda(@PathVariable int codigoPrenda) {
		return manejadorEliminar.eliminar(codigoPrenda);
	}
	
	@PutMapping("/actualizar")
	public ComandoPrenda actualizar(@RequestBody ComandoPrenda comandoPrenda) {
		return manejadorActualizarPrenda.actualizar(comandoPrenda);
	}
}
