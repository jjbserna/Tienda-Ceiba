/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.cliente;

import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearCliente {

	
	public static final String CLIENTE_EXISTE = "El Cliente ya existe";
	private RepositorioCliente repositorioCliente;

	
	
	/**
	 * @param repositorioCliente
	 */
	public ServicioCrearCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}



	public Cliente agregar(Cliente cliente) {
		Cliente comandoCliente = repositorioCliente.buscarPorCedula(cliente.getIdentificacion());
		if (comandoCliente==null) {
			return repositorioCliente.agregar(cliente);
		}
		throw new ExcepcionVenta(CLIENTE_EXISTE);
	}
	
}
