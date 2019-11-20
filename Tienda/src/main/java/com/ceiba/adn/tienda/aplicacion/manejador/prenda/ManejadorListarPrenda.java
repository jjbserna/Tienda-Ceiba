/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.prenda;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioListarPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorListarPrenda {

	
	private final ServicioListarPrenda servicioListar;
	
	
	
	/**
	 * @param servicioListar
	 */
	public ManejadorListarPrenda(ServicioListarPrenda servicioListar) {
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
