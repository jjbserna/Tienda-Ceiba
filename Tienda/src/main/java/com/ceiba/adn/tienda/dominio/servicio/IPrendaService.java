/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio;

import java.util.List;

import com.ceiba.adn.tienda.infraestructura.entidades.Prenda;

/**
 * @author jeison.barbosa
 *
 */
public interface IPrendaService {

	public Prenda addPrenda(Prenda prenda);
	
	public List<Prenda> findAll();
	
	public Prenda findByCodigoPrenda(int codigoPrenda);
	
	public boolean delete(int codigoPrenda);
}
