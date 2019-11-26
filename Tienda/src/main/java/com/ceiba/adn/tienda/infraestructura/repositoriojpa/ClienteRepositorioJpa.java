/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.repositoriojpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.adn.tienda.infraestructura.entidades.ClienteEntidad;


/**
 * @author jeison.barbosa
 *
 */
@Repository
public interface ClienteRepositorioJpa extends JpaRepository<ClienteEntidad, Integer>{

	ClienteEntidad findByIdentificacion(int cedula);
	void deleteByIdentificacion(int cedula);
	ClienteEntidad findById(int idCliente);
}
