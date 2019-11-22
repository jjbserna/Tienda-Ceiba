/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador.prenda;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.aplicacion.fabrica.FabricaPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioActualizarPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorActualizarPrenda {

	private final ServicioActualizarPrenda servicioActualizarPrenda;
	private final FabricaPrenda fabricaPrenda;


	
	/**
	 * @param servicioActualizarPrenda
	 * @param fabricaPrenda
	 */
	public ManejadorActualizarPrenda(ServicioActualizarPrenda servicioActualizarPrenda, FabricaPrenda fabricaPrenda) {
		this.servicioActualizarPrenda = servicioActualizarPrenda;
		this.fabricaPrenda = fabricaPrenda;
	}



	public ComandoPrenda actualizar(ComandoPrenda comandoPrenda) {
		Prenda prenda=fabricaPrenda.crearPrenda(comandoPrenda);
		return servicioActualizarPrenda.actualizar(prenda);
	}
	
}
