/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.cliente;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioListarCliente {

	private RepositorioCliente repositorioCliente;

	/**
	 * @param repositorioCliente
	 */
	public ServicioListarCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}
	
	
	public List<Cliente> listar(){
		return repositorioCliente.listar();
	}
	
}
