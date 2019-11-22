/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioEliminarCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioListarCliente;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorEliminarCliente {
	
	private final ServicioEliminarCliente servicioEliminarCliente;

	/**
	 * @param servicioEliminarCliente
	 */
	public ManejadorEliminarCliente(ServicioEliminarCliente servicioEliminarCliente) {
		this.servicioEliminarCliente = servicioEliminarCliente;
	}

	public boolean eliminar(int identificacion) {
		return servicioEliminarCliente.eliminar(identificacion);
	}
	
}
