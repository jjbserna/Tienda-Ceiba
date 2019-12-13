/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.cliente;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.dominio.modelo.Cliente;
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


	public List<Cliente> listar(){
		return servicioListarCliente.listar();
	}
}
