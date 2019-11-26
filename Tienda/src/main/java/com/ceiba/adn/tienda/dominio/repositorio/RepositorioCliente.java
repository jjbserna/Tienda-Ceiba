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
	
	ComandoCliente agregar(Cliente cliente);

	List<ComandoCliente> listar();

	void eliminar(int cedula);
	
	ComandoCliente buscarPorCedula(int cedula);
	
	ComandoCliente actualizar(Cliente cliente);
	
	ComandoCliente buscarPorId(int idCliente);
}
