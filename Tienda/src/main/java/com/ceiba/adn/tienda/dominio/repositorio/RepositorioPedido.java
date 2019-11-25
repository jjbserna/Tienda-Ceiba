/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;

/**
 * @author jeison.barbosa
 *
 */
public interface RepositorioPedido {

	ComandoPedido crear(Pedido pedido);
	List<ComandoPedido> listar();
	void eliminar(int numeroPedido);
	ComandoPedido actualizar(Pedido pedido);
	ComandoPedido buscar(int numeroPedido);
	
	
}
