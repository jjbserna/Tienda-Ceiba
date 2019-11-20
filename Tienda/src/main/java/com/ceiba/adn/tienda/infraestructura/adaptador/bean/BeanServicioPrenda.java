/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioCrear;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioEliminar;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioListar;

/**
 * @author jeison.barbosa
 *
 */
@Configuration
public class BeanServicioPrenda {
	
	@Bean
	public ServicioCrear servicioCrear(RepositorioPrenda repositorioPrenda) {
		return new ServicioCrear(repositorioPrenda);
	}
	
	@Bean
	public ServicioListar servicioListar(RepositorioPrenda repositorioPrenda) {
		return new ServicioListar(repositorioPrenda);
	}
	
	@Bean
	public ServicioEliminar servicioEliminar(RepositorioPrenda repositorioPrenda) {
		return new ServicioEliminar(repositorioPrenda);
	}
}
