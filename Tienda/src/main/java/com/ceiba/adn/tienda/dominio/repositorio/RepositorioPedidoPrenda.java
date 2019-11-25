/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;

/**
 * @author jeison.barbosa
 *
 */

public interface RepositorioPedidoPrenda {
	ComandoPedidoPrenda crear(PedidoPrenda pedidoPrenda);
	void eliminar(int idPedidoPrenda);
	List<ComandoPedidoPrenda> listar();
	ComandoPedidoPrenda buscarPorId(int idPedidoPrenda);
}
