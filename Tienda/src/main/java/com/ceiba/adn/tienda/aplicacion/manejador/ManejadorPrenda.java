/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.manejador;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.aplicacion.fabrica.FabricaPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.servicio.ServicioImpPrenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ManejadorPrenda {

	private final ServicioImpPrenda servicioImpPrenda;
	private final FabricaPrenda fabricaPrenda;

	/**
	 * @param servicioImpPrenda
	 * @param fabricaPrenda
	 */
	public ManejadorPrenda(ServicioImpPrenda servicioImpPrenda, FabricaPrenda fabricaPrenda) {
		this.servicioImpPrenda = servicioImpPrenda;
		this.fabricaPrenda = fabricaPrenda;
	}

	public ComandoPrenda crear(ComandoPrenda comandoPrenda) {
		Prenda prenda=this.fabricaPrenda.crearPrenda(comandoPrenda);
		return servicioImpPrenda.agregar(prenda);
		
	}
	
	public boolean eliminar(int codigoPrenda) {
		return servicioImpPrenda.eliminar(codigoPrenda);
	}
	
	public List<ComandoPrenda> listar(){
		return servicioImpPrenda.listar();
	}
}	
	

