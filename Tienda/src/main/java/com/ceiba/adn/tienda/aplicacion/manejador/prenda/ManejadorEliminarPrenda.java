/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.prenda;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioEliminarPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorEliminarPrenda {

	private final ServicioEliminarPrenda servicioEliminar;



	/**
	 * @param servicioEliminar
	 */
	public ManejadorEliminarPrenda(ServicioEliminarPrenda servicioEliminar) {
		this.servicioEliminar = servicioEliminar;
	}



	public boolean eliminar(int codigoPrenda) {
		return servicioEliminar.eliminar(codigoPrenda);
	}
}
