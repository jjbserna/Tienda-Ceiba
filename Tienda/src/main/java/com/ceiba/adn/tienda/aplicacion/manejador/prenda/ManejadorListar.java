/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.prenda;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioListar;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorListar {

	
	private final ServicioListar servicioListar;
	
	
	
	/**
	 * @param servicioListar
	 */
	public ManejadorListar(ServicioListar servicioListar) {
		this.servicioListar = servicioListar;
	}


	/**
	 * 
	 * @return
	 */
	public List<ComandoPrenda> listar(){
		return servicioListar.listar();
	}	
	
}
