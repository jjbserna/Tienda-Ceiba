/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.pedido;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.infraestructura.entidades.ClienteEntidad;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPedido {
	private static final String PEDIDO_EXISTE = "El pedido ya existe";
	private static final String EL_CLIENTE_NO_EXISTE = "El cliente no existe";
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
		ComandoPedido comandoPedido = repositorioPedido.buscar(pedido.getNumeroOrden());
		if (comandoPedido == null) {
			ComandoCliente comandoCliente=repositorioCliente.buscarPorCedula(pedido.getClienteId().getIdCliente());
			if (comandoCliente!=null) {
				ClienteEntidad clienteEntidad= new ClienteEntidad();
				clienteEntidad.setIdCliente(comandoCliente.getIdCliente());
				clienteEntidad.setIdentificacion(comandoCliente.getIdentificacion());
				clienteEntidad.setNombre(comandoCliente.getNombre());
				clienteEntidad.setApellido(comandoCliente.getApellido());
				clienteEntidad.setFechaNacimiento(comandoCliente.getFechaNacimiento());
				clienteEntidad.setCorreo(comandoCliente.getCorreo());
				clienteEntidad.setCelular(comandoCliente.getCelular());
				clienteEntidad.setCiudad(comandoCliente.getCiudad());
				clienteEntidad.setDireccionEntrega(comandoCliente.getDireccionEntrega());
				clienteEntidad.setUsuario(comandoCliente.getUsuario());
				clienteEntidad.setDireccionEntrega(comandoCliente.getCorreo());
				pedido.setClienteId(clienteEntidad);
				return repositorioPedido.crear(pedido);
			}
				throw new ExcepcionVenta(EL_CLIENTE_NO_EXISTE);
		}
			throw new ExcepcionVenta(PEDIDO_EXISTE);
	}
}
