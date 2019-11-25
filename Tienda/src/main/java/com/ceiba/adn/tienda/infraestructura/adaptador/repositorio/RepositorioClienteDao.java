/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.infraestructura.entidades.ClienteEntidad;
import com.ceiba.adn.tienda.infraestructura.repositoriojpa.ClienteRepositorioJpa;

/**
 * @author jeison.barbosa
 *
 */
@Repository
public class RepositorioClienteDao implements RepositorioCliente {

	@Autowired
	private ClienteRepositorioJpa clienteDao;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ComandoCliente agregar(Cliente cliente) {
		ClienteEntidad clienteEntidad = modelMapper.map(cliente, ClienteEntidad.class);
		clienteDao.save(clienteEntidad);
		return modelMapper.map(clienteEntidad, ComandoCliente.class);
	}

	@Override
	public List<ComandoCliente> listar() {
		List<ComandoCliente> listComandoCliente = new ArrayList<>();
		List<ClienteEntidad> listClienteEntidad = clienteDao.findAll();
		for (int i = 0; i < listClienteEntidad.size(); i++) {
			listComandoCliente.add(modelMapper.map(listClienteEntidad.get(i), ComandoCliente.class));
		}
		return listComandoCliente;

	}

	@Override
	@Transactional
	public void eliminar(int cedula) {
		clienteDao.deleteByIdentificacion(cedula);
	}

	@Override
	public ComandoCliente buscarPorCedula(int cedula) {
		ClienteEntidad cliente = clienteDao.findByIdentificacion(cedula);
		if (cliente != null) {
			return modelMapper.map(cliente, ComandoCliente.class);
		} else {
			return null;
		}

	}

	@Override
	@Transactional
	public ComandoCliente actualizar(Cliente cliente) {
		ClienteEntidad clienteEntidad = modelMapper.map(cliente, ClienteEntidad.class);
		clienteDao.save(clienteEntidad);
		return modelMapper.map(clienteEntidad, ComandoCliente.class);
	}

	@Override
	public ComandoCliente buscarPorId(int idCliente) {
	ClienteEntidad clienteEntidad=clienteDao.findById(idCliente);
	if(clienteEntidad!=null) {
		return modelMapper.map(clienteEntidad, ComandoCliente.class);
	}
	return null;
		
	}

}
