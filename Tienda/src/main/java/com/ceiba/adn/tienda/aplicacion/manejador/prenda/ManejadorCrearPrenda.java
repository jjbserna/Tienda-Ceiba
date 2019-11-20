/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.prenda;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.aplicacion.fabrica.FabricaPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioCrearPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorCrearPrenda {

	private final ServicioCrearPrenda servicioCrear;
	private final FabricaPrenda fabricaPrenda;

	/**
	 * @param servicioCrear
	 * @param fabricaPrenda
	 */
	public ManejadorCrearPrenda(ServicioCrearPrenda servicioCrear, FabricaPrenda fabricaPrenda) {
		this.servicioCrear = servicioCrear;
		this.fabricaPrenda = fabricaPrenda;
	}

	public ComandoPrenda crear(ComandoPrenda comandoPrenda) {
		Prenda prenda = this.fabricaPrenda.crearPrenda(comandoPrenda);
		return servicioCrear.agregar(prenda);

	}

}
