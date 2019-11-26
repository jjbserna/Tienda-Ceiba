/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.prenda;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPrenda {

	private static final String LA_PRENDA_EXISTE="La prenda ya existe";
	private RepositorioPrenda repositorioPrenda;

	/**
	 * @param repositorioPrenda
	 */
	public ServicioCrearPrenda(RepositorioPrenda repositorioPrenda) {
		this.repositorioPrenda = repositorioPrenda;
	}

	public ComandoPrenda agregar(Prenda prenda) {
		ComandoPrenda comandoPrenda = buscarPorCodigo(prenda.getCodigoPrenda());
		if (comandoPrenda == null) {
			return repositorioPrenda.agregar(prenda);
		} else {
			
			throw new ExcepcionVenta(LA_PRENDA_EXISTE);
		}

	}

	public ComandoPrenda buscarPorCodigo(int codigoPrenda) {
		return repositorioPrenda.buscarPorCodigo(codigoPrenda);
	}

	/**
	 * @return the repositorioPrenda
	 */
	public RepositorioPrenda getRepositorioPrenda() {
		return repositorioPrenda;
	}

}
