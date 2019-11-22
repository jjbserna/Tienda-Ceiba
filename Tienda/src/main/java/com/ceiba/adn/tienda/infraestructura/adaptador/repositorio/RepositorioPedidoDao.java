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

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.infraestructura.entidades.PedidoEntidad;
import com.ceiba.adn.tienda.infraestructura.repositoriojpa.PedidoRepositorioJpa;

/**
 * @author jeison.barbosa
 *
 */
@Repository
public class RepositorioPedidoDao implements RepositorioPedido {

	@Autowired
	private PedidoRepositorioJpa pedidoRepositorioJpa;
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public ComandoPedido crear(Pedido pedido) {
		PedidoEntidad pedidoEntidad = modelMapper.map(pedido, PedidoEntidad.class);
		pedidoRepositorioJpa.save(pedidoEntidad);
		return modelMapper.map(pedidoEntidad, ComandoPedido.class);
	}

	@Override
	public List<ComandoPedido> listar() {
		List<PedidoEntidad> listaPedidoEntidad = pedidoRepositorioJpa.findAll();
		List<ComandoPedido> listaComandoPedido = new ArrayList<ComandoPedido>();
		for (int i = 0; i < listaPedidoEntidad.size(); i++) {
			listaComandoPedido.add(modelMapper.map(listaPedidoEntidad.get(i), ComandoPedido.class));
		}
		return listaComandoPedido;

	}

	@Override
	@Transactional
	public void eliminar(int numeroOrden) {
		pedidoRepositorioJpa.deleteByNumeroOrden(numeroOrden);
	}

	@Override
	@Transactional
	public ComandoPedido actualizar(Pedido pedido) {
		PedidoEntidad pedidoEntidad = modelMapper.map(pedido, PedidoEntidad.class);
		pedidoRepositorioJpa.save(pedidoEntidad);
		return modelMapper.map(pedidoEntidad, ComandoPedido.class);
	}

	@Override
	public ComandoPedido buscar(Pedido pedido) {
		PedidoEntidad pedidoEntidad=pedidoRepositorioJpa.findByNumeroOrden(pedido.getNumeroOrden());
		return modelMapper.map(pedidoEntidad, ComandoPedido.class);
	}
	
	

}
