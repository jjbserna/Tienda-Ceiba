/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;

/**
 * @author jeison.barbosa
 *
 */
public interface RepositorioCliente {
	
	Cliente agregar(Cliente cliente);

	List<Cliente> listar();

	void eliminar(int cedula);
	
	Cliente buscarPorCedula(int cedula);
	
	Cliente actualizar(Cliente cliente);
	
	ComandoCliente buscarPorId(int idCliente);
}
