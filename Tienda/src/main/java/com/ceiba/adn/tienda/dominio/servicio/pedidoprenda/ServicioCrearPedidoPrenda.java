/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedidoprenda;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPedidoPrenda {
	public static final String EL_PEDIDO_PRENDA_YA_EXISTE = "Ya existe el codigo asignado a el pedido-prenda";
	public static final String EL_PEDIDO_O_PRENDA_NO_EXISTE ="El pedido o la prenda no existen";
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
				Pedido pedido = new Pedido();
				Prenda prenda = new Prenda();
				pedido.setIdPedido(comandoPedido.getIdPedido());
				prenda.setIdPrenda(comandoPrenda.getIdPrenda());
				pedido.setNumeroOrden(comandoPedido.getNumeroOrden());
				pedidoPrenda.setPedidoId(pedido);
				pedidoPrenda.setPrendaId(prenda);
				pedidoPrenda.setValorTotal(esLunes(new Date(), pedidoPrenda.getValorTotal()));
				return repositorioPedidoPrenda.crear(pedidoPrenda);
			}
			throw new ExcepcionVenta(EL_PEDIDO_O_PRENDA_NO_EXISTE);
		}
		throw new ExcepcionVenta(EL_PEDIDO_PRENDA_YA_EXISTE);

	}
	
	public double esLunes(Date fechaSolicitud, double valor) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaSolicitud);
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return valor*2;
		}
		return valor;
	}

}
