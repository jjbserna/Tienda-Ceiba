/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.adn.tienda.dominio.servicio.IPrendaService;
import com.ceiba.adn.tienda.infraestructura.entidades.Prenda;
import com.ceiba.adn.tienda.infraestructura.repositoriojpa.IPrendaRepositorio;

/**
 * @author jeison.barbosa
 *
 */
@Service
public class PrendaServiceImp implements IPrendaService{
	
	@Autowired
	private IPrendaRepositorio prendaDao;

	@Override
	@Transactional
	public Prenda addPrenda(Prenda prenda) {
		return prendaDao.save(prenda);
	}

	@Override
	public List<Prenda> findAll() {
		
		return prendaDao.findAll();
	}

	@Override
	public Prenda findByCodigoPrenda(int codigoPrenda) {
		
		return prendaDao.findByCodigoPrenda(codigoPrenda);
	}

	@Override
	@Transactional
	public boolean delete(int codigoPrenda) {
		Prenda prenda=findByCodigoPrenda(codigoPrenda);
		if(prenda!=null) {
			prendaDao.deleteById(prenda.getIdPrenda());
			return true;
		}
		return false;
	}

	

	
	
}
