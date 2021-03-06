/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.cliente;

import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioEliminarCliente {

	private RepositorioCliente repositorioCliente;


	/**
	 * @param repositorioCliente
	 */
	public ServicioEliminarCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}
	
	
	public boolean eliminar(int identificacion) {
		Cliente comandoCliente=repositorioCliente.buscarPorCedula(identificacion);
		if(comandoCliente!=null) {
			repositorioCliente.eliminar(identificacion);
			return true;
		}
		else {
			return false;
		}
	}
}
