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

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.aplicacion.manejador.ManejadorPrenda;


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
@RequestMapping("/tienda")
public class ControllerPrenda {


private final ManejadorPrenda manejadorPrenda;

	
	/**
 * @param manejadorCategoria
 */
public ControllerPrenda(ManejadorPrenda manejadorCategoria) {
	this.manejadorPrenda = manejadorCategoria;
}

	@PostMapping("/prenda")
	public ComandoPrenda create(@RequestBody ComandoPrenda comandoPrenda) {
		return manejadorPrenda.crear(comandoPrenda);
	}
	
	@GetMapping
	public List<ComandoPrenda> listaGeneral(){
		return manejadorPrenda.listar();
	}
	
	@DeleteMapping("/prenda/{codigoPrenda}")
	public boolean eliminarPrenda(@PathVariable int codigoPrenda) {
		return manejadorPrenda.eliminar(codigoPrenda);
	}
	
}
