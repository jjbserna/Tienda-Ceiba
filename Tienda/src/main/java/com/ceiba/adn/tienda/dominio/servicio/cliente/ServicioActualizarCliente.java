/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.cliente;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioActualizarCliente {

	public static final String EL_CLIENTE_NO_EXISTE="El cliente no existe en la base de datos";
	private RepositorioCliente repositorioCliente;

	/**
	 * @param repositorioCliente
	 */
	public ServicioActualizarCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}
	
	
	public ComandoCliente actualizar(Cliente cliente) {
	ComandoCliente clienteActualizar=repositorioCliente.buscarPorCedula(cliente.getIdentificacion());
	if (clienteActualizar!=null) {
		cliente.setIdCliente(clienteActualizar.getIdCliente());
		return repositorioCliente.actualizar(cliente);
	}
	else {
		throw new ExcepcionVenta(EL_CLIENTE_NO_EXISTE);
	}
	}
	
}
