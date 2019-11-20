/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.repositorio;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.infraestructura.repositoriojpa.ClienteRepositorioJpa;

/**
 * @author jeison.barbosa
 *
 */
@Repository
public class RepositorioClienteDao implements RepositorioCliente{
	
	@Autowired
	private ClienteRepositorioJpa clienteDao;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public ComandoCliente agregar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComandoCliente> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int cedula) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ComandoCliente buscarPorCedula(int cedula) {
		// TODO Auto-generated method stub
		return null;
	}

}
