/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio.cliente;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
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
		ComandoCliente comandoCliente=repositorioCliente.buscarPorCedula(identificacion);
		if(comandoCliente!=null) {
			repositorioCliente.eliminar(identificacion);
			return true;
		}
		else {
			return false;
		}
	}
}
