/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedidoprenda;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.infraestructura.entidades.PedidoEntidad;
import com.ceiba.adn.tienda.infraestructura.entidades.PedidoPrendaEntidad;
import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPedidoPrenda {
	private static final String EL_PEDIDO_PRENDA_YA_EXISTE = "Ya existe el codigo asignado a el pedido-prenda";
	private static final String EL_PEDIDO_O_PRENDA_NO_EXISTE ="El pedido o la prenda no existen";
	private RepositorioPedidoPrenda repositorioPedidoPrenda;
	private RepositorioPedido repositorioPedido;
	private RepositorioPrenda repositorioPrenda;

	/**
	 * @param repositorioPedidoPrenda
	 */
	public ServicioCrearPedidoPrenda(RepositorioPedidoPrenda repositorioPedidoPrenda,
			RepositorioPedido repositorioPedido, RepositorioPrenda repositorioPrenda) {
		this.repositorioPedidoPrenda = repositorioPedidoPrenda;
		this.repositorioPedido = repositorioPedido;
		this.repositorioPrenda = repositorioPrenda;
	}

	public ComandoPedidoPrenda crear(PedidoPrenda pedidoPrenda) {
		ComandoPedidoPrenda comandoPedidoPrenda = repositorioPedidoPrenda.buscarPorId(pedidoPrenda.getIdPedidoPrenda());
		if (comandoPedidoPrenda == null) {
			ComandoPedido comandoPedido = repositorioPedido.buscar(pedidoPrenda.getPedidoId().getIdPedido());
			ComandoPrenda comandoPrenda = repositorioPrenda.buscarPorCodigo(pedidoPrenda.getPrendaId().getIdPrenda());
			if(comandoPedido != null && comandoPrenda != null) {
				PedidoEntidad pedidoEntidad = new PedidoEntidad();
				PrendaEntidad prendaEntidad = new PrendaEntidad();
				pedidoEntidad.setIdPedido(comandoPedido.getIdPedido());
				prendaEntidad.setIdPrenda(comandoPrenda.getIdPrenda());
				pedidoEntidad.setNumeroOrden(comandoPedido.getNumeroOrden());
				pedidoPrenda.setPedidoId(pedidoEntidad);
				pedidoPrenda.setPrendaId(prendaEntidad);
				return repositorioPedidoPrenda.crear(pedidoPrenda);
			}
			throw new ExcepcionVenta(EL_PEDIDO_O_PRENDA_NO_EXISTE);
		}
		throw new ExcepcionVenta(EL_PEDIDO_PRENDA_YA_EXISTE);

	}

}
