/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.prenda;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioEliminarPrenda {

	private RepositorioPrenda repositorioPrenda;

	/**
	 * @param repositorioPrenda
	 */
	public ServicioEliminarPrenda(RepositorioPrenda repositorioPrenda) {
		this.repositorioPrenda = repositorioPrenda;
	}

	public boolean eliminar(int codigoPrenda) {
		ComandoPrenda comandoPrenda = repositorioPrenda.buscarPorCodigo(codigoPrenda);
		if (comandoPrenda != null) {
			repositorioPrenda.eliminar(codigoPrenda);
			return true;
		} else {
			return false;
		}
	}

}
