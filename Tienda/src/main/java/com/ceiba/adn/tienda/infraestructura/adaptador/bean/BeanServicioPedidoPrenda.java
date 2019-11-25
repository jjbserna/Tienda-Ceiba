/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.pedidoprenda.ServicioCrearPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Configuration
public class BeanServicioPedidoPrenda {

	
	@Bean
	public ServicioCrearPedidoPrenda servicioCrearPedidoPrenda(RepositorioPedidoPrenda repositorioPedidoPrenda, RepositorioPrenda repositorioPrenda, RepositorioPedido repositorioPedido) {
		return new ServicioCrearPedidoPrenda(repositorioPedidoPrenda, repositorioPedido, repositorioPrenda);
	}
}
