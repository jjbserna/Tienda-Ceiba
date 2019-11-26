/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedidoprenda;

import java.util.ArrayList;
import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioListarPedidoPrenda {

	private RepositorioPedidoPrenda repositorioPedidoPrenda;

	/**
	 * @param repositorioPedidoPrenda
	 */
	public ServicioListarPedidoPrenda(RepositorioPedidoPrenda repositorioPedidoPrenda) {
		this.repositorioPedidoPrenda = repositorioPedidoPrenda;
	}

	public List<ComandoPedidoPrenda> listarPorPedido(int numeroOrden) {
		List<ComandoPedidoPrenda> listaComandoPrenda = repositorioPedidoPrenda.listar();
		List<ComandoPedidoPrenda> listaFiltro = new ArrayList<>();
		for (int i = 0; i < listaComandoPrenda.size(); i++) {
			if (listaComandoPrenda.get(i).getPedidoId().getNumeroOrden() == numeroOrden) {
				listaFiltro.add(listaComandoPrenda.get(i));
			}
		}
		return listaFiltro;

	}

}
