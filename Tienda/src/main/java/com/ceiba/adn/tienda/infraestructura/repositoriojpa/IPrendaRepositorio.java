/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.repositoriojpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.adn.tienda.infraestructura.entidades.Prenda;


/**
 * @author jeison.barbosa
 *
 */
public interface IPrendaRepositorio extends JpaRepository<Prenda, Integer>{

	
}
