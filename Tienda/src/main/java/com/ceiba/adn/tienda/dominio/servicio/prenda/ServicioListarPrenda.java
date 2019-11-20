/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.prenda;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioListarPrenda {


	private RepositorioPrenda repositorioPrenda;

	/**
	 * @param repositorioPrenda
	 */
	public ServicioListarPrenda(RepositorioPrenda repositorioPrenda) {
		this.repositorioPrenda = repositorioPrenda;
	}

	public List<ComandoPrenda> listar() {
		return repositorioPrenda.listar();
	}
}
