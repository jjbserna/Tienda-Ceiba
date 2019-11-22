/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.repositoriojpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.adn.tienda.infraestructura.entidades.PedidoEntidad;

/**
 * @author jeison.barbosa
 *
 */
public interface PedidoRepositorioJpa extends JpaRepository<PedidoEntidad, Integer>{
	
	PedidoEntidad findByNumeroOrden(int numeroOrden);
	void deleteByNumeroOrden(int numeroOrden);
}
