/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.bean;

import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.ServicioImpPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Configuration
public class BeanServicioPrenda {
	public ServicioImpPrenda servicioImpPrenda(RepositorioPrenda repositorioPrenda) {
		return new ServicioImpPrenda(repositorioPrenda);
	}
}
