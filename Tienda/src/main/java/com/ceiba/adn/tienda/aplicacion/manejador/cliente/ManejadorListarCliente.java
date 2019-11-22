/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.cliente;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioListarCliente;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorListarCliente {

	private final ServicioListarCliente servicioListarCliente;
	
	
	/**
	 * @param servicioListarCliente
	 */
	public ManejadorListarCliente(ServicioListarCliente servicioListarCliente) {
		super();
		this.servicioListarCliente = servicioListarCliente;
	}


	public List<ComandoCliente> listar(){
		return servicioListarCliente.listar();
	}
}
