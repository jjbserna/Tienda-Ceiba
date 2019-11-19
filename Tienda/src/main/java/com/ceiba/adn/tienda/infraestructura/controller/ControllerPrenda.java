/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.tienda.dominio.servicio.IPrendaService;
import com.ceiba.adn.tienda.infraestructura.entidades.Prenda;


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


	@Autowired
	private IPrendaService prendaService;
	
	@PostMapping("/prenda")
	public Prenda create(@RequestBody Prenda prenda) {
		return prendaService.addPrenda(prenda);
	}
	
	@GetMapping
	public List<Prenda> listaGeneral(){
		return prendaService.findAll();
	}
	
	@DeleteMapping("/prenda/{codigoPrenda}")
	public boolean eliminarPrenda(@PathVariable int codigoPrenda) {
		return prendaService.delete(codigoPrenda);
	}
	
}
