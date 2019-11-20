/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioCrearPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioEliminarPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioListarPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Configuration
public class BeanServicioPrenda {
	
	@Bean
	public ServicioCrearPrenda servicioCrear(RepositorioPrenda repositorioPrenda) {
		return new ServicioCrearPrenda(repositorioPrenda);
	}
	
	@Bean
	public ServicioListarPrenda servicioListar(RepositorioPrenda repositorioPrenda) {
		return new ServicioListarPrenda(repositorioPrenda);
	}
	
	@Bean
	public ServicioEliminarPrenda servicioEliminar(RepositorioPrenda repositorioPrenda) {
		return new ServicioEliminarPrenda(repositorioPrenda);
	}
}
