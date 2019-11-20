/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.repositoriojpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;


/**
 * @author jeison.barbosa
 *
 */
public interface PrendaRepositorioJpa extends JpaRepository<PrendaEntidad, Integer>{

	PrendaEntidad findByCodigoPrenda(int codigoPrenda);
	
	void deleteByCodigoPrenda(int codigoPrenda); 
	
}
