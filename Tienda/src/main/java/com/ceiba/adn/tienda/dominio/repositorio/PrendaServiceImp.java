/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

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
	
	
}
