/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedido;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.infraestructura.adaptador.repositorio.RepositorioPedidoDao;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPedido {
	private static final String PEDIDO_EXISTE = "El pedido ya existe";
	private RepositorioPedido repositorioPedido;

	/**
	 * @param repositorioPedido
	 */
	public ServicioCrearPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}
	
	public ComandoPedido crear(Pedido pedido) {
		ComandoPedido comandoPedido=repositorioPedido.buscar(pedido);
		if(comandoPedido==null) {
			return repositorioPedido.crear(pedido);
		}
		throw new ExcepcionVenta(PEDIDO_EXISTE);
	}
}
