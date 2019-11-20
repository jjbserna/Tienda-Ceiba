/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.fabrica.FabricaCliente;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioCrearCliente;


/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorCrearCliente {

	private final ServicioCrearCliente servicioCrearCliente;
	private final FabricaCliente fabricaCliente;
	/**
	 * @param servicioCrear
	 * @param fabricaCliente
	 */
	public ManejadorCrearCliente(ServicioCrearCliente servicioCrear, FabricaCliente fabricaCliente) {
		this.servicioCrearCliente = servicioCrear;
		this.fabricaCliente = fabricaCliente;
	}
	
	
	public ComandoCliente agregar(ComandoCliente comandoCliente) {
		Cliente cliente =this.fabricaCliente.crearCliente(comandoCliente);
		return servicioCrearCliente.agregar(cliente);
	}
	
}
