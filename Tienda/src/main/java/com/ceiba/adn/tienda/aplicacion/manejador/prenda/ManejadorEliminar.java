/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.prenda;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioEliminar;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorEliminar {

	private final ServicioEliminar servicioEliminar;



	/**
	 * @param servicioEliminar
	 */
	public ManejadorEliminar(ServicioEliminar servicioEliminar) {
		this.servicioEliminar = servicioEliminar;
	}



	public boolean eliminar(int codigoPrenda) {
		return servicioEliminar.eliminar(codigoPrenda);
	}
}
