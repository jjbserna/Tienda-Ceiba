/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedidoPrenda;
import com.ceiba.adn.tienda.infraestructura.entidades.PedidoPrendaEntidad;
import com.ceiba.adn.tienda.infraestructura.repositoriojpa.PedidoPrendaRepositorioJpa;

/**
 * @author jeison.barbosa
 *
 */
@Repository
public class RepositorioPedidoPrendaDao implements RepositorioPedidoPrenda {

	@Autowired
	private PedidoPrendaRepositorioJpa pedidoPrendaDao;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public ComandoPedidoPrenda crear(PedidoPrenda pedidoPrenda) {
		 PedidoPrendaEntidad pedidoPrendaEntidad=modelMapper.map(pedidoPrenda, PedidoPrendaEntidad.class);
		 pedidoPrendaDao.save(pedidoPrendaEntidad);
		 return modelMapper.map(pedidoPrenda, ComandoPedidoPrenda.class);
	}

	@Override
	@Transactional
	public void eliminar(int idPedidoPrenda) {
		pedidoPrendaDao.deleteById(idPedidoPrenda);
	}

	@Override
	public List<ComandoPedidoPrenda> listar() {
		List<PedidoPrendaEntidad> listaPedidoPrendaEntidad=pedidoPrendaDao.findAll();
		List<ComandoPedidoPrenda> listaComandoPedidoPrenda=new ArrayList<ComandoPedidoPrenda>();
		for (int i = 0; i < listaPedidoPrendaEntidad.size(); i++) {
			listaComandoPedidoPrenda.add(modelMapper.map(listaPedidoPrendaEntidad.get(i), ComandoPedidoPrenda.class));
		}
		return listaComandoPedidoPrenda;
	}

	@Override
	public ComandoPedidoPrenda buscarPorId(int idPedidoPrenda) {
		PedidoPrendaEntidad pedidoPrendaEntidad = pedidoPrendaDao.findByIdPedidoPrenda(idPedidoPrenda);
		if (pedidoPrendaEntidad != null) {
			return modelMapper.map(pedidoPrendaEntidad, ComandoPedidoPrenda.class);
		}

		return null;
	}

}
