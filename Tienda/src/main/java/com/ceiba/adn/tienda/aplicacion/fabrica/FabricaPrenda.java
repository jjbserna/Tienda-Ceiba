/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class FabricaPrenda {

	public Prenda crearPrenda(ComandoPrenda comandoPrenda) {
		return new Prenda(comandoPrenda.getCodigoPrenda(), comandoPrenda.getDescripcion(), comandoPrenda.getEstilo(),
				comandoPrenda.isEstado(), comandoPrenda.getPrecio(), comandoPrenda.getStock());
	}

}
