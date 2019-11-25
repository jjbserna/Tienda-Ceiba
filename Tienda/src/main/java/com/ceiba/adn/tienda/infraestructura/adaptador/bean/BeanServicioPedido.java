/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioCrearPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioEliminarPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioListarPedido;

/**
 * @author jeison.barbosa
 *
 */
@Configuration
public class BeanServicioPedido {
	@Bean
	public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido, RepositorioCliente repositorioCliente) {
		return new ServicioCrearPedido(repositorioPedido,repositorioCliente);
	}
	
	@Bean
	public ServicioEliminarPedido servicioEliminarPedido(RepositorioPedido repositorioPedido) {
		return new ServicioEliminarPedido(repositorioPedido);
	}
	
	@Bean
	public ServicioListarPedido servicioListarPedido(RepositorioPedido repositorioPedido) {
		return new ServicioListarPedido(repositorioPedido);
	}
}
