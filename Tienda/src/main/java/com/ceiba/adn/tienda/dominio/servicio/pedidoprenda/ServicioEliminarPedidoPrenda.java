/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedidoprenda;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioEliminarPedidoPrenda {
	
	private RepositorioPedidoPrenda repositorioPedidoPrenda;

	/**
	 * @param repositorioPedidoPrenda
	 */
	public ServicioEliminarPedidoPrenda(RepositorioPedidoPrenda repositorioPedidoPrenda) {
		this.repositorioPedidoPrenda = repositorioPedidoPrenda;
	}
	
	public boolean eliminar(int idPedidoPrenda) {
		ComandoPedidoPrenda comandoPedidoprenda= repositorioPedidoPrenda.buscarPorId(idPedidoPrenda);
		if(comandoPedidoprenda!=null) {
			repositorioPedidoPrenda.eliminar(idPedidoPrenda);
			return true;
		}
		return false;
	}
	
}
