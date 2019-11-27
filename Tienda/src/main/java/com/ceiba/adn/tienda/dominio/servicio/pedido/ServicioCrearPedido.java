/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedido;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPedido {
	private static final String PEDIDO_EXISTE = "El pedido ya existe";
	private static final String EL_CLIENTE_NO_EXISTE = "El cliente no existe";
	private static final String FECHA_INVALIDA_PARA_PEDIDO = "No es posible hacer pedido ni sabado, ni domingo";
	private static final int DIAS_ENTREGA = 5;
	private RepositorioPedido repositorioPedido;
	private RepositorioCliente repositorioCliente;

	/**
	 * @param repositorioPedido
	 */
	public ServicioCrearPedido(RepositorioPedido repositorioPedido, RepositorioCliente repositorioCliente) {
		this.repositorioPedido = repositorioPedido;
		this.repositorioCliente = repositorioCliente;
	}

	public ComandoPedido crear(Pedido pedido) {
		pedido.setFechaPedido(new Date());
		if (ValidarFecha(pedido.getFechaPedido())) {
			throw new ExcepcionVenta(FECHA_INVALIDA_PARA_PEDIDO);
		}
		ComandoPedido comandoPedido = repositorioPedido.buscar(pedido.getNumeroOrden());
		if (comandoPedido == null) {
			ComandoCliente comandoCliente = repositorioCliente.buscarPorCedula(pedido.getClienteId().getIdCliente());
			if (comandoCliente != null) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(comandoCliente.getIdCliente());
				cliente.setIdentificacion(comandoCliente.getIdentificacion());
				cliente.setNombre(comandoCliente.getNombre());
				cliente.setApellido(comandoCliente.getApellido());
				cliente.setFechaNacimiento(comandoCliente.getFechaNacimiento());
				cliente.setCorreo(comandoCliente.getCorreo());
				cliente.setCelular(comandoCliente.getCelular());
				cliente.setCiudad(comandoCliente.getCiudad());
				cliente.setDireccionEntrega(comandoCliente.getDireccionEntrega());
				cliente.setUsuario(comandoCliente.getUsuario());
				Date fechaEntregaNueva = generarFechaEntrega(DIAS_ENTREGA, pedido.getFechaPedido());
				pedido.setFechaEntrega(fechaEntregaNueva);
				pedido.setClienteId(cliente);
				return repositorioPedido.crear(pedido);
			}
			throw new ExcepcionVenta(EL_CLIENTE_NO_EXISTE);
		}
		throw new ExcepcionVenta(PEDIDO_EXISTE);
	}

	public Date generarFechaEntrega(int dias, Date fechaSolicitud) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaSolicitud);
		int i = 1;
		while (i < dias) {
			if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				i++;
				cal.get(Calendar.DAY_OF_WEEK);
			}
			cal.add(Calendar.DATE, 1);
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.add(Calendar.DATE, 1);
		}
		return cal.getTime();
	}

	public boolean ValidarFecha(Date fechaPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPedido);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}
}
