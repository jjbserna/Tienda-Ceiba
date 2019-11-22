/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.fabrica.FabricaCliente;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioActualizarCliente;

/**
 * @author jeison.barbosa
 *
 */
 @Component
public class ManejadorActualizarCliente {
	
	private final ServicioActualizarCliente servicioActualizar;
	private FabricaCliente fabricaCliente;
	/**
	 * @param servicioActualizar
	 * @param fabricaCliente
	 */
	public ManejadorActualizarCliente(ServicioActualizarCliente servicioActualizar, FabricaCliente fabricaCliente) {
		this.servicioActualizar = servicioActualizar;
		this.fabricaCliente = fabricaCliente;
	}
	
	public ComandoCliente actualizar(ComandoCliente comandoCliente) {
		Cliente cliente=fabricaCliente.crearCliente(comandoCliente);
		return servicioActualizar.actualizar(cliente);
	}
	
	

}
