/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.repositoriojpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;


/**
 * @author jeison.barbosa
 *
 */
@Repository
public interface PrendaRepositorioJpa extends JpaRepository<PrendaEntidad, Integer>{

	PrendaEntidad findByCodigoPrenda(int codigoPrenda);
	
	void deleteByCodigoPrenda(int codigoPrenda); 
	
}
