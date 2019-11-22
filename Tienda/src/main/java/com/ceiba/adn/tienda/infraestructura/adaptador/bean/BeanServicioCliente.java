/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioActualizarCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioCrearCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioEliminarCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioListarCliente;

/**
 * @author jeison.barbosa
 *
 */
@Configuration
public class BeanServicioCliente {

	@Bean
	public ServicioCrearCliente servicioCrearCliente(RepositorioCliente repositorioCliente) {
		return new ServicioCrearCliente(repositorioCliente);
	}
	
	@Bean
	public ServicioListarCliente servicioListarCliente(RepositorioCliente repositorioCliente) {
		return new ServicioListarCliente(repositorioCliente);
	}
	@Bean
	public ServicioEliminarCliente servicioEliminarCliente(RepositorioCliente repositorioCliente) {
		return new ServicioEliminarCliente(repositorioCliente);
	}
	@Bean
	public ServicioActualizarCliente servicioActualizarCliente(RepositorioCliente repositorioCliente) {
		return new ServicioActualizarCliente(repositorioCliente);
	}
}
