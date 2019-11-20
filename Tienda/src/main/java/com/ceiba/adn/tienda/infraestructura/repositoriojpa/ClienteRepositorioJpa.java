/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.repositoriojpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.adn.tienda.infraestructura.entidades.ClienteEntidad;


/**
 * @author jeison.barbosa
 *
 */
public interface ClienteRepositorioJpa extends JpaRepository<ClienteEntidad, Integer>{

	ClienteEntidad findByIdentificacion(int cedula);
	void deleteByIdentificacion(int cedula);
}
