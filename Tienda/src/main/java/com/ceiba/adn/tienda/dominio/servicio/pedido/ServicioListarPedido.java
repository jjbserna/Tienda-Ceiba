/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedido;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioListarPedido {

	private RepositorioPedido repositorioPedido;

	/**
	 * @param repositorioPedido
	 */
	public ServicioListarPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}

	public List<ComandoPedido> listar() {
		return repositorioPedido.listar();
	}
}
