/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.repositoriojpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.adn.tienda.infraestructura.entidades.PedidoPrendaEntidad;

/**
 * @author jeison.barbosa
 *
 */
public interface PedidoPrendaRepositorioJpa extends JpaRepository<PedidoPrendaEntidad, Integer> {

	PedidoPrendaEntidad findByIdPedidoPrenda(int idPedidoPrenda);
	void deleteByIdPedidoPrenda (int idPedidoPrenda); 
}
