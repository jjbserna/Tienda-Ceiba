/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedido;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioEliminarPedido {

	private RepositorioPedido repositorioPedido;

	/**
	 * @param repositorioPedido
	 */
	public ServicioEliminarPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}

	public boolean eliminar(int numeroPedido) {
		ComandoPedido comandoPedido=repositorioPedido.buscar(numeroPedido);
		if (comandoPedido!=null) {
			repositorioPedido.eliminar(numeroPedido);
			return true;
		}
		return false;
	}
}
