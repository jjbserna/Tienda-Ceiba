/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioCrearPedido;

/**
 * @author jeison.barbosa
 *
 */
@Configuration
public class BeanServicioPedido {
	@Bean
	public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido) {
		return new ServicioCrearPedido(repositorioPedido);
	}

}
