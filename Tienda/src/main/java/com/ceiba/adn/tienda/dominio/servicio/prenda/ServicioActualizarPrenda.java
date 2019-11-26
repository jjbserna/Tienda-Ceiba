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
public class ServicioActualizarPrenda {

	private RepositorioPrenda repositorioPrenda;

	public static final String LA_PRENDA_NO_EXISTE="La prenda no existe";
	/**
	 * @param repositorioPrenda
	 */
	public ServicioActualizarPrenda(RepositorioPrenda repositorioPrenda) {
		this.repositorioPrenda = repositorioPrenda;
	}
	
	public ComandoPrenda actualizar(Prenda prenda) {
		ComandoPrenda comandoPrenda=repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda());
		if(comandoPrenda!=null) {
			prenda.setIdPrenda(comandoPrenda.getIdPrenda());
			return repositorioPrenda.actualizar(prenda);
		}
		else {
			throw new ExcepcionVenta(LA_PRENDA_NO_EXISTE);
		}
	}
	
	
}
