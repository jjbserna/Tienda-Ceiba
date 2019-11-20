/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;

/**
 * @author jeison.barbosa
 *
 */
public interface RepositorioCliente {
	
ComandoCliente agregar();

List<ComandoCliente> listar();

void eliminar(int cedula);

ComandoCliente buscarPorCedula(int cedula);
}
