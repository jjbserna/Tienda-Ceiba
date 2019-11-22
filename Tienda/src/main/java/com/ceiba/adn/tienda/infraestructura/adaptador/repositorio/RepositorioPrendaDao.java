 /**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.adaptador.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;
import com.ceiba.adn.tienda.infraestructura.repositoriojpa.PrendaRepositorioJpa;

/**
 * @author jeison.barbosa
 *
 */
@Repository
public class RepositorioPrendaDao implements RepositorioPrenda {

	@Autowired
	private PrendaRepositorioJpa prendaDao;
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * @param prendaDao
	 */
	public RepositorioPrendaDao(PrendaRepositorioJpa prendaDao) {
		this.prendaDao = prendaDao;
	}

	@Override
	@Transactional
	public ComandoPrenda agregar(Prenda prenda) {
		PrendaEntidad prendaEntidad = modelMapper.map(prenda, PrendaEntidad.class);
		prendaDao.save(prendaEntidad);
		return modelMapper.map(prendaEntidad, ComandoPrenda.class);
	}

	@Override
	public List<ComandoPrenda> listar() {
		List<PrendaEntidad> listaPrendaEntidad = prendaDao.findAll();
		List<ComandoPrenda> listaComando = new ArrayList<>();
		for (int i = 0; i < listaPrendaEntidad.size(); i++) {
			listaComando.add(modelMapper.map(listaPrendaEntidad.get(i), ComandoPrenda.class));
		}
		return listaComando;
	}

	@Override
	public ComandoPrenda buscarPorCodigo(int codigoPrenda) {
		PrendaEntidad prendaEntidad = prendaDao.findByCodigoPrenda(codigoPrenda);
		if(prendaEntidad!=null) {
			return modelMapper.map(prendaEntidad, ComandoPrenda.class);
		}
		return null;
	}

	@Override
	@Transactional
	public void eliminar(int codigoPrenda) {
		prendaDao.deleteByCodigoPrenda(codigoPrenda);
	}

	@Override
	@Transactional
	public ComandoPrenda actualizar(Prenda prenda) {
		PrendaEntidad prendaEntidad = modelMapper.map(prenda, PrendaEntidad.class);
		prendaDao.save(prendaEntidad);
		return modelMapper.map(prendaEntidad, ComandoPrenda.class);
		
		
	}
	
	

}
